-- Ajout de l'attribut bookBackDate dans la table BookReservation
ALTER TABLE public.BookReservation 
ADD COLUMN bookBackDate VARCHAR;

-- Ajout de l'attribut waitReservationFull dans la table Book
ALTER TABLE public.Book
ADD COLUMN waitReservationFull BOOLEAN NOT NULL;

-- Creation de la table BookUserWaitingReservation
CREATE SEQUENCE public.bookuserwaitingreservation_id_seq;

CREATE TABLE public.BookUserWaitingReservation (
                id INTEGER NOT NULL DEFAULT nextval('public.bookuserwaitingreservation_id_seq'),
                book_id INTEGER NOT NULL,
                libraryUser_id INTEGER NOT NULL,
                waitReservationDate VARCHAR NOT NULL,
                closedDateBack VARCHAR,
                standOnWaitingList INTEGER,
                mailSend BOOLEAN,
                mailSendDate TIMESTAMP,
                CONSTRAINT bookuserwaitingreservation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.bookuserwaitingreservation_id_seq OWNED BY public.BookUserWaitingReservation.id;

-- ajout des foreign keys
ALTER TABLE public.BookUserWaitingReservation ADD CONSTRAINT book_userreservation_fk
FOREIGN KEY (book_id)
REFERENCES public.Book (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.BookUserWaitingReservation ADD CONSTRAINT libraryuser_bookuserwaitingreservation_fk
FOREIGN KEY (libraryUser_id)
REFERENCES public.LibraryUser (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
