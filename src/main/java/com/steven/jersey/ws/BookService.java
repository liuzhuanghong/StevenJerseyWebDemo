package com.steven.jersey.ws;

import com.steven.jersey.domain.Book;
import com.steven.jersey.domain.Books;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * 学习如何使用Jersey创建WebService，所以直接返回结果了
 * Created by liuzhuanghong on 16/8/13.
 */
@Path("books")
public class BookService {

    private static final Books books = new Books();
    static {
        List<Book> lstBook = new ArrayList<Book>();
        lstBook.add(new Book(1L, "Java", "中国出版社"));
        lstBook.add(new Book(2L, "WebService", "深圳出版社"));
        books.setBookList(lstBook);

    }


    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Books getBooks() {
        return books;
    }

    @Path("{bookId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Book getBookByPath(@PathParam("bookId") final Long bookId) {
        Book result = null;
        List<Book> lstBooks = books.getBookList();
        for(Book book : lstBooks){
            if(book.getBookId() == bookId){
                return book;
            }
        }
        return new Book(-1L, "");
    }

    @Path("/book")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Book getBookByQuery(@QueryParam("id") final Long bookId) {
        Book result = null;
        List<Book> lstBooks = books.getBookList();
        for(Book book : lstBooks){
            if(book.getBookId() == bookId){
                return book;
            }
        }
        return new Book(-1L, "");
    }


    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    public Book addBook(final Book book) {

        if(book == null){
            return new Book(-1L, "");
        }
        List<Book> lstBooks = books.getBookList();
        lstBooks.add(book);
        return book;
    }

    /*
    @Path("{bookId:[0-9]*}")
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    public Book updateBook(@PathParam("bookId") final Long bookId, final Book book){
        System.out.println("call updateBook:[bookId:" + bookId + "]");
        return book;
    }
    */
    //@Path("{bookId:[0-9]*}")
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    public String updateBook(Book book){
        System.out.println("call update:[" + book + "]");
        return "update";
    }

    @Path("/{bookId:[0-9]*}")
    @DELETE
    public String deleteBook(@PathParam("bookId") final Long bookId) {
        System.out.println("call deleteBook:[bookId:" + bookId + "]");
        return "delete success!";
    }

}
