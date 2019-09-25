package com.eLibraryBatch.batchMailbusiness.Impl;

import com.eLibraryBatch.batchMailproxies.MicroserviceBDDProxy;
import com.eLibraryModel.beans.BookReservationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


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
            logger.info("Rappel envoye a: " + lateBookReservation.get(i).getLibraryUser().getUserEmail());
            logger.info("****************************************************************************************");
    }
        if (lateBookReservation.isEmpty()) {
            logger.info("****************************************************************************************");
            logger.info("Il n'y a aucun email de rappel a envoyer.");
            logger.info("****************************************************************************************");
        }
    }
}
