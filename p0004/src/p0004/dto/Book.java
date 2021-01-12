/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0004.dto;

import java.util.Calendar;
import java.util.Vector;
import p0004.error.BookInvalidException;

/**
 *
 * @author USER
 */
public class Book {
    public static String ID_FORMAT = "\\w{1,10}";
    public static String NAME_FORMAT = ".{1,50}";
    public static String AUTHOR_FORMAT = "[a-zA-Z\\s\\.]{1,50}";
    public static String PUBLISHER_FORMAT = "[a-zA-Z\\s\\.]{1,50}";
    public static String PUBLISHED_YEAR_FORMAT = "\\d{0,4}";
    
    private String id;
    private String name;
    private String author;
    private String publisher;
    private int publishedYear;

    public Book() {
    }

    public Book(String id, String name, String author, String publisher, String publishedYear) throws BookInvalidException{
        BookInvalidException ex = new BookInvalidException("create book failed");
        
        //id check
        if(id == null || id.isEmpty())
            ex.setIdError("Book ID can not be empty!!!");
        else if(!id.matches(ID_FORMAT))
            ex.setIdError("Book ID invalid!!!");
        else
            this.id = id;
        //name check
        if(name == null || name.isEmpty())
            ex.setNameError("Book name can not be empty!!!");
        else if(!name.matches(NAME_FORMAT))
            ex.setNameError("Book name invalid!!!");
        else
            this.name = name;
        
        //author check
        if(author == null || author.isEmpty())
            ex.setAuthorError("Author name can not be empty!!!");
        else if(!author.matches(AUTHOR_FORMAT))
            ex.setAuthorError("Author name invalid!!!");
        else 
            this.author = author;
        
        //publisher check
        if(publisher == null || publisher.isEmpty())
            ex.setPublisherError("Book publisher can not be empty!!!");
        else if(!publisher.matches(PUBLISHER_FORMAT))
            ex.setPublisherError("Publisher invalid!!!");
        else
            this.publisher = publisher;
        
        //published year check
        if(publishedYear == null || publishedYear.isEmpty())
            ex.setPublisherYearError("Book's published year can not be empty!!!");
        else if(!publishedYear.matches(PUBLISHED_YEAR_FORMAT))
            ex.setPublisherYearError("Book's published year invalid!!!");
        else{
            try{
                int year = Integer.parseInt(publishedYear);
                if(year - Calendar.getInstance().get(Calendar.YEAR) > 0)
                    ex.setPublisherYearError("Book published year must lower than " + Calendar.getInstance().get(Calendar.YEAR) + "!!!");
                else
                    this.publishedYear = year;
            }catch(NumberFormatException ignore){
                ex.setPublisherYearError("Book's published year invalid!!!");
            }
        }
        
        if(ex.isError())
            throw ex;
    }
    
    public Book(Vector<String> data) throws BookInvalidException{
        this(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setId(String id) throws BookInvalidException {
        BookInvalidException ex = new BookInvalidException();
        
        //id check
        if(id == null || id.isEmpty())
            ex.setIdError("Book ID can not be empty!!!");
        else if(!id.matches(ID_FORMAT))
            ex.setIdError("Book ID invalid!!!");
        else
            this.id = id;
        if(ex.isError()) throw ex;
    }

    public void setName(String name) throws BookInvalidException {
        BookInvalidException ex = new BookInvalidException();
        //name check
        if(name == null || name.isEmpty())
            ex.setNameError("Book name can not be empty!!!");
        else if(!name.matches(NAME_FORMAT))
            ex.setNameError("Book name invalid!!!");
        else
            this.name = name;
        if(ex.isError()) throw ex;
    }

    public void setAuthor(String author) throws BookInvalidException {
        BookInvalidException ex = new BookInvalidException();
        if(author == null || author.isEmpty())
            ex.setAuthorError("Author name can not be empty!!!");
        else if(!author.matches(AUTHOR_FORMAT))
            ex.setAuthorError("Author name invalid!!!");
        else 
            this.author = author;
        if(ex.isError()) throw ex;
    }

    public void setPublisher(String publisher) throws BookInvalidException {
        BookInvalidException ex = new BookInvalidException();
        //publisher check
        if(publisher == null || publisher.isEmpty())
            ex.setPublisherError("Book publisher can not be empty!!!");
        else if(!publisher.matches(PUBLISHER_FORMAT))
            ex.setPublisherError("Publisher invalid!!!");
        else
            this.publisher = publisher;
        if(ex.isError()) throw ex;
    }

    public void setPublishedYear(String publishedYear) throws BookInvalidException {
        BookInvalidException ex = new BookInvalidException();
        //published year check
        if(publishedYear == null || publishedYear.isEmpty())
            ex.setPublisherYearError("Book's published year can not be empty!!!");
        else if(!publishedYear.matches(PUBLISHED_YEAR_FORMAT))
            ex.setPublisherYearError("Book's published year invalid!!!");
        else{
            try{
                int year = Integer.parseInt(publishedYear);
                if(year - Calendar.getInstance().get(Calendar.YEAR) > 0)
                    ex.setPublisherYearError("Book published year must lower than " + Calendar.getInstance().get(Calendar.YEAR) + "!!!");
                else
                    this.publishedYear = year;
            }catch(NumberFormatException ignore){
                ex.setPublisherYearError("Book's published year invalid!!!");
            }
        }
        
        if(ex.isError()) throw ex;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", author=" + author + ", publisher=" + publisher + ", publishedYear=" + publishedYear + '}';
    }

    

    
    
    public static void main(String[] args) {
        try {
            Vector<String> vector = new Vector<String>();
            vector.add("12345678");
            vector.add("name a");
            vector.add("an  C.M  ame ");
            vector.add("pna me");
            vector.add("2019");
            System.out.println(new Book("12345678", "name a", "an  C.M  ame ", "pna me", "2019").toString());
            System.out.println(new Book(vector).toString());
        } catch (BookInvalidException ex) {
            ex.printStackTrace();
            System.out.println(ex.toString());
        }
    }
    
}
