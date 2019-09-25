
CREATE SEQUENCE public.book_id_seq;

CREATE TABLE public.Book (
                id INTEGER NOT NULL DEFAULT nextval('public.book_id_seq'),
                bookName VARCHAR NOT NULL,
                bookAuthor VARCHAR NOT NULL,
                bookPictureUrl VARCHAR NOT NULL,
                bookDescription VARCHAR NOT NULL,
                bookLabel VARCHAR NOT NULL,
                allBookReserved BOOLEAN NOT NULL,
                CONSTRAINT book_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.book_id_seq OWNED BY public.Book.id;

CREATE SEQUENCE public.library_id_seq;

CREATE TABLE public.Library (
                id INTEGER NOT NULL DEFAULT nextval('public.library_id_seq'),
                libraryName VARCHAR NOT NULL,
                CONSTRAINT library_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.library_id_seq OWNED BY public.Library.id;

CREATE TABLE public.LibraryCatalog (
                library_id INTEGER NOT NULL,
                book_id INTEGER NOT NULL,
                bookIteration INTEGER NOT NULL,
                CONSTRAINT librarycatalog_pk PRIMARY KEY (library_id, book_id)
);


CREATE SEQUENCE public.libraryuser_id_seq;

CREATE TABLE public.LibraryUser (
                id INTEGER NOT NULL DEFAULT nextval('public.libraryuser_id_seq'),
                userFirstName VARCHAR NOT NULL,
                userName VARCHAR NOT NULL,
                userPassword VARCHAR NOT NULL,
                userEmail VARCHAR NOT NULL,
                CONSTRAINT libraryuser_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.libraryuser_id_seq OWNED BY public.LibraryUser.id;

CREATE UNIQUE INDEX libraryuser_idx
 ON public.LibraryUser
 ( userEmail );

CREATE SEQUENCE public.bookreservation_id_seq;

CREATE TABLE public.BookReservation (
                id INTEGER NOT NULL DEFAULT nextval('public.bookreservation_id_seq'),
                beginOfReservationDate VARCHAR NOT NULL,
                endOfReservationDate VARCHAR NOT NULL,
                extensionOfReservation BOOLEAN NOT NULL,
                bookBack BOOLEAN NOT NULL,
                user_id INTEGER NOT NULL,
                book_id INTEGER NOT NULL,
                library_id INTEGER NOT NULL,
                CONSTRAINT bookreservation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.bookreservation_id_seq OWNED BY public.BookReservation.id;

ALTER TABLE public.BookReservation ADD CONSTRAINT book_bookreservation_fk
FOREIGN KEY (book_id)
REFERENCES public.Book (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.LibraryCatalog ADD CONSTRAINT book_librarycatalog_fk
FOREIGN KEY (book_id)
REFERENCES public.Book (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.BookReservation ADD CONSTRAINT library_bookreservation_fk
FOREIGN KEY (library_id)
REFERENCES public.Library (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.LibraryCatalog ADD CONSTRAINT library_librarycatalog_fk
FOREIGN KEY (library_id)
REFERENCES public.Library (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.BookReservation ADD CONSTRAINT user_bookreservation_fk
FOREIGN KEY (user_id)
REFERENCES public.LibraryUser (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;