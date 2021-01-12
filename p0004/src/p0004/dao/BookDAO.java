/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0004.dao;

import java.util.Vector;
import p0004.dto.Book;

/**
 *
 * @author USER
 */
public class BookDAO {
    public Vector<Book> getAllBook(){
        String sql = "select b.book_id, b.book_name, b.author, b.publisher, b.published_year \n" +
                    "from book b";
        Vector<Book> books = new Vector<>();
        try {
            Vector<Vector<String>> result = SQLQuery.executeQuery(sql);
//            result.forEach(System.out::println);
            for (Vector<String> book : result) {
                books.add(new Book(book));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
        
    }
    public static void main(String[] args) {
        new BookDAO().getAllBook().forEach(System.out::println);
    }
}
