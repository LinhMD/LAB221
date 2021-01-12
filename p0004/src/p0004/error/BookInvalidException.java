package p0004.error;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class BookInvalidException extends Exception{
    private String idError = null;
    private String nameError = null;
    private String authorError = null;
    private String publisherError = null;
    private String publisherYearError = null;
    private boolean isError = false;

    public BookInvalidException(String mess) {
        super(mess);
    }

   

    public boolean isError() {
        return isError;
    }
    
    public BookInvalidException() {
    }

    public String getIdError() {
        return idError == null? "" : idError + "\n";
    }

    public String getNameError() {
        return nameError == null? "" : nameError + "\n";
    }

    public String getAuthorError() {
        return authorError == null? "" : authorError + "\n";
    }

    public String getPublisherError() {
        return publisherError == null? "" : publisherError + "\n";
    }

    public String getPublisherYearError() {
        return publisherYearError == null? "" : publisherYearError + "\n";
    }

    public void setIdError(String idError) {
        this.isError = true;
        this.idError = idError;
    }

    public void setNameError(String nameError) {
        this.isError = true;
        this.nameError = nameError;
    }

    public void setAuthorError(String authorError) {
        this.authorError = authorError;
        this.isError = true;
    }

    public void setPublisherError(String publisherError) {
        this.publisherError = publisherError;
        this.isError = true;
    }

    public void setPublisherYearError(String publisherYearError) {
        this.publisherYearError = publisherYearError;
        this.isError = true;
    }

    @Override
    public String toString() {
        return getIdError() + getNameError() + getAuthorError() + getPublisherError() + getPublisherYearError();
    }
    
    
    
}
