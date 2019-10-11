package com.eLibraryBatch.batchMailbusiness.Impl;

import com.eLibraryBatch.batchMailproxies.MicroserviceBDDProxy;
import com.eLibraryModel.beans.BookReservationBean;
import com.eLibraryModel.beans.BookUserWaitingReservationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class EmailService {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private MicroserviceBDDProxy microserviceBDDProxy;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * For send email to people who didn't bring back books
     */
    @PostConstruct
    public void sendEmailToLateBookReservation() {
        String to = "";
        String subject = "";
        String text = "";

        List<BookReservationBean> lateBookReservation =
                microserviceBDDProxy.getBookReservationLate();

        // send email for each reservation
        for (int i = 0; i < lateBookReservation.size(); i++) {
            to = lateBookReservation.get(i).getLibraryUser().getUserEmail();
            subject = "Rappel, date de fin de réservation dépassé !";
            text = "Bonjour " + lateBookReservation.get(i).getLibraryUser().getUserFirstName() + "," +
                    "\nLa date de retour maximale pour le livre: " + lateBookReservation.get(i).getBook().getBookName() +
                    " de " + lateBookReservation.get(i).getBook().getBookAuthor() +
                    " était le: " + lateBookReservation.get(i).getEndOfReservationDate() + " !" +
                    "\nMerci de ramener le livre au plus tôt dans la bibliothèque: " +
                    lateBookReservation.get(i).getLibrary().getLibraryName() + " !";

            //send mail
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("nicobod31@gmail.com"); //only for development time
//            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            logger.info("****************************************************************************************");
            logger.info("Rappel pour ramener un livre envoyé a: " + lateBookReservation.get(i).getLibraryUser().getUserEmail());
            logger.info("****************************************************************************************");
    }
        if (lateBookReservation.isEmpty()) {
            logger.info("****************************************************************************************");
            logger.info("Il n'y a aucun email de rappel afin de ramener un livre a envoyer.");
            logger.info("****************************************************************************************");
        }
        // delete user on waiting list if he didn't reserve book since 48h
        this.deleteWaitReservationIfUserDontReservebookSince48HoursAfterMailsend();
        // sendEmailWhenBookBackAndUserOnWaitingList if needeed
        this.sendEmailWhenBookBackAndUserOnWaitingList();

    }

    /**
     * For send email when book back if there is user on waiting list
     */
    public void sendEmailWhenBookBackAndUserOnWaitingList() {
        List<Integer> listBookBackTodayByBookId = new ArrayList<>();
        String to = "";
        String subject = "";
        String text = "";
        int mailSendtoUser = 0;

        // today book back check
        List<BookReservationBean> bookReservationEndedTodayList = microserviceBDDProxy.getBookreservationEndedToday();
        if (!bookReservationEndedTodayList.isEmpty()) {
            for (BookReservationBean bookReservation : bookReservationEndedTodayList) {listBookBackTodayByBookId.add(bookReservation.getBookId());
            }
        }
        // wait reservation in progress check
        List<BookUserWaitingReservationBean> bookUserWaitingReservationListInProgress = microserviceBDDProxy.getBookUserWaitingReservation();
        // if bookId on waiting Reservation List in progress is present on book back today list
        // -> automatic mail send -> only this user can reserve book for 48h
        for (int i = 0; i < bookUserWaitingReservationListInProgress.size(); i++) {
            mailSendtoUser = 0;
            for (int j = 0; j < listBookBackTodayByBookId.size(); j++) {
                if (bookUserWaitingReservationListInProgress.get(i).getBookId() == listBookBackTodayByBookId.get(j)) {
                    //sendEmail in order of stand
                    if (bookUserWaitingReservationListInProgress.get(i).getStandOnWaitingList() == 1) {
                        String userOnWaitingEmail =
                                microserviceBDDProxy.getUserEmailByUserId(bookUserWaitingReservationListInProgress.get(i).getLibraryUserId());

                        //check if mail was already send one day before
                        Date emailAlreadySend = bookUserWaitingReservationListInProgress.get(i).getMailSendDate();
                        if (emailAlreadySend != null) {
                            mailSendtoUser = 1;
                        }
                        if (mailSendtoUser < 1) {
                            //sending Email
                            to = userOnWaitingEmail;
                            subject = "Livre disponible !";
                            text = "Bonjour " + userOnWaitingEmail + "," +
                                    "\nLe livre: " + bookUserWaitingReservationListInProgress.get(i).getBook().getBookName() +
                                    " de l'auteur: " + bookUserWaitingReservationListInProgress.get(i).getBook().getBookAuthor() +
                                    " est disponible, vous avez 2 jours pour l'emprunter." +
                                    "\nAprés ce délai votre place sur la liste d'attente sera supprimé." +
                                    "\nMerci";

                            SimpleMailMessage message = new SimpleMailMessage();
                            message.setTo("nicobod31@gmail.com"); //only for development time
                            // message.setTo(to);
                            message.setSubject(subject);
                            message.setText(text);
                            emailSender.send(message);
                            logger.info("****************************************************************************************");
                            logger.info("Reservation possible envoyé à: " + userOnWaitingEmail);
                            logger.info("****************************************************************************************");
                            //remove
                            listBookBackTodayByBookId.remove(j); // one book back is on one mail (one reservation possible)
                            mailSendtoUser++; // only one mail must be sending by user
                            //update waiting reservation on bdd
                            Date todayDatedate = new Date();
                            bookUserWaitingReservationListInProgress.get(i).setMailSend(true);
                            bookUserWaitingReservationListInProgress.get(i).setMailSendDate(todayDatedate);
                            microserviceBDDProxy.updateWaitReservation(bookUserWaitingReservationListInProgress.get(i));
                        }
                    }
                }
            }
        }
    }

    /**
     * For delete wait reservation have not reserve book (delay is 48h)
     */
    public void deleteWaitReservationIfUserDontReservebookSince48HoursAfterMailsend() {
        Date todayDate = new Date();
        Date userReceiveMaildate;
        List<BookUserWaitingReservationBean> bookUserWaitingReservationListOnlyUserHaveReceiveMail = new ArrayList<>();
        // waiting list in progress check
        List<BookUserWaitingReservationBean> bookUserWaitingReservationListInProgress = microserviceBDDProxy.getBookUserWaitingReservation();

        // keep only user who receive mail
        for (int i = 0; i < bookUserWaitingReservationListInProgress.size(); i++) {
            if (bookUserWaitingReservationListInProgress.get(i).isMailSend()) {
                bookUserWaitingReservationListOnlyUserHaveReceiveMail.add(bookUserWaitingReservationListInProgress.get(i));
            }
        }

//        // ***** Only for developpementTime: add 2 days to todayDate  ******
//        Calendar calDevelop = Calendar.getInstance();
//        calDevelop.setTime(todayDate);
//        calDevelop.add(Calendar.DATE,2); // here can set number of day:
//        // nothing -> book is 48h reserved
//        // 1 -> mail have been sending yesterday (system not re-send email) -> book is 24h reserved
//        // 2 -> user waiting reservation is deleted -> book was not reserved
//        todayDate = calDevelop.getTime();
//        // ******* Only for developpementTime: add 2 days to todayDate *****

        //for each user who receive mail (reservation is possible for 48h)
        for (int i = 0; i < bookUserWaitingReservationListOnlyUserHaveReceiveMail.size(); i++) {
            userReceiveMaildate = bookUserWaitingReservationListOnlyUserHaveReceiveMail.get(i).getMailSendDate();
            //add 2 days (48h)
            Calendar cal = Calendar.getInstance();
            cal.setTime(userReceiveMaildate);
            cal.add(Calendar.DATE,2);
            Date userReceiveMaildatePlusTwoDays = cal.getTime();
            // if this date is after today -> delete user on waiting list (he didn't reserve book when he can (48h))
            if (todayDate.after(userReceiveMaildatePlusTwoDays)) {
                ResponseEntity responseEntity =
                        microserviceBDDProxy.deleteUserWaitingReservation(bookUserWaitingReservationListOnlyUserHaveReceiveMail.get(i));
                if (responseEntity.getStatusCodeValue() == 202) {
                    String userOnWaitingEmail =
                            microserviceBDDProxy.getUserEmailByUserId(bookUserWaitingReservationListOnlyUserHaveReceiveMail.get(i).getLibraryUserId());
                    logger.info("****************************************************************************************");
                    logger.info("48h écoulé, l'utilisateur " + userOnWaitingEmail
                            + " à été supprimer de la liste d'attente pour le livre: "
                            + bookUserWaitingReservationListOnlyUserHaveReceiveMail.get(i).getBook().getBookName() + ".");
                    logger.info("****************************************************************************************");
                }
            }
        }
    }
}
