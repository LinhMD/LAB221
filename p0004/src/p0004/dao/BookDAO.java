/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0004.dao;

import java.util.Vector;

import p0004.dto.Book;
import p0004.error.BookInvalidException;

/**
 *
 * @author USER
 */
public class BookDAO {
    public Vector<Book> getAllBook() throws BookInvalidException, Exception{
        String sql = "select b.book_id, b.book_name, b.author, b.publisher, b.published_year, b.for_rent \n" +
                    "from book b";
        Vector<Book> books = new Vector<>();
        Vector<Vector<String>> result = SQLQuery.executeQuery(sql);
        for (Vector<String> book : result) books.add(new Book(book));
        return books;
    }

    public Book findBookByID(String id) throws BookInvalidException, Exception{
        if(id == null) return null;
        String sql = "select b.book_id, b.book_name, b.author, b.publisher, b.published_year, b.for_rent \n" +
                "from book b\n" +
                "where b.book_id = ?";
        Vector<Vector<String>> result = SQLQuery.executeQuery(sql, id);
        return new Book(result.get(0));
        
    }
    public Book findBookByName(String name) throws BookInvalidException, Exception{
        if(name == null) return null;
        String sql = "select b.book_id, b.book_name, b.author, b.publisher, b.published_year, b.for_rent \n" +
                "from book b\n" +
                "where b.book_name like ?";
        Vector<Vector<String>> result = SQLQuery.executeQuery(sql, "%" + name + "%");
        return new Book(result.get(0));
        
    }

    public boolean insertBook(Book book) throws  Exception{
        if(book == null) return false;
        String sql = "insert into book (book_id, book_name, author, publisher, published_year, for_rent)\n" +
                        "values (?, ?, ?, ?, ?, ?)";
        
        return SQLQuery.executeNonQuery(sql,
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getPublishedYear(),
                    book.isForRent());
        
    }
    public boolean updateBook(Book book) throws Exception{
        if(book == null) return false;
        String sql = "update book\n" +
                        "set book_name = ?, author = ?, publisher = ?, published_year = ?, for_rent = ?\n" +
                        "where book_id = ?";
        
        return SQLQuery.executeNonQuery(sql,
                    book.getName(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getPublishedYear(),
                    book.isForRent(),
                    book.getId());
        
    }

    public boolean deleteBookByID(String id) throws Exception{
        String sql = "delete from book\n" +
                    "where book_id = ?";
        return SQLQuery.executeNonQuery(sql, id);
        
    }
    
   

 
}
