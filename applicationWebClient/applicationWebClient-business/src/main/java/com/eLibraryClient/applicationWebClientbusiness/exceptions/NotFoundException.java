package com.eLibraryClient.applicationWebClientbusiness.exceptions;

/**
 * Exception for type 'Data Not Found'
 */
public class NotFoundException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param pMessage -
     */
    public NotFoundException(String pMessage) {
        super(pMessage);
    }

    /**
     * Constructeur.
     *
     * @param pCause -
     */
    public NotFoundException(Throwable pCause) {
        super(pCause);
    }

    /**
     * Constructeur.
     *
     * @param pMessage -
     * @param pCause -
     */
    public NotFoundException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}
