package com.steven.jersey.ws;

import com.steven.jersey.domain.Book;
import com.steven.jersey.domain.Books;
import com.sun.net.httpserver.HttpServer;
import junit.framework.Assert;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by liuzhuanghong on 16/8/14.
 */
public class BookServiceTest {

    public static final String BASE_URI = "http://localhost:8088/jersey-web-demo/webapi/";
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        final ClientConfig cc = new ClientConfig();
        final Client client = ClientBuilder.newClient(cc);
        target = client.target(BookServiceTest.BASE_URI).path("books");

    }

    @Test
    public void testGetAllBookService() {
        System.out.println("Start to testGetAllBookService...");
        final Invocation.Builder invocationBuilder = target.request();
        final Books result = invocationBuilder.get(Books.class);
        System.out.println(result.getBookList());
        Assert.assertTrue(true);
        System.out.println("Finish to testGetAllBookService");
    }

    @Test
    public void testQueryParamById(){
        System.out.println("Start to testQueryParamById...");
        final WebTarget queryTarget = target.path("/book").queryParam("id", Integer.valueOf(1));
        final Invocation.Builder invocationBuilder = queryTarget.request(MediaType.APPLICATION_XML_TYPE);
        final Response response = invocationBuilder.get();
        final Book result = response.readEntity(Book.class);
        System.out.println(result);
        System.out.println("Finish to testQueryParamById");
    }

    @Test
    public void testQueryByPathID(){
        System.out.println("Start to testQueryByPathID...");
        final WebTarget pathTarget = target.path("/1");
        final Invocation.Builder invocationBuilder = pathTarget.request(MediaType.APPLICATION_XML_TYPE);
        final Book result = invocationBuilder.get(Book.class);
        System.out.println(result);
        System.out.println("Finish to testQueryByPathID");
    }

    @Test
    public void testAddBook(){
        System.out.println("Start to testAddBook...");
        final Book newBook = new Book(3L, "Mysql", "坂田出版社");
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_XML_TYPE);
        final Book savedBook = target.request(MediaType.APPLICATION_XML_TYPE).post(bookEntity, Book.class);
        System.out.println(savedBook);
        System.out.println("Finish to testAddBook");
    }

    @Test
    public void testDelete(){
        System.out.println("Start to testDelete...");
        final WebTarget deleteTarget = target.path("/1");
        final Invocation.Builder invocationBuilder = deleteTarget.request();
        final String result = invocationBuilder.delete(String.class);
        System.out.println("Finish to testDelete");
    }


    @Test
    public void testPut(){
        System.out.println("Start to testPut...");
        MediaType contentTypeMediaType = MediaType.APPLICATION_XML_TYPE;
        MediaType acceptMediaType = MediaType.TEXT_PLAIN_TYPE;
        Book newBook = new Book(5L, "Redis", "MTS出版社");
        final Entity<Book> bookEntity = Entity.entity(newBook, contentTypeMediaType);
        final String lastUpdate = target.request(acceptMediaType).put(bookEntity, String.class);
        System.out.println(lastUpdate);
        System.out.println("Finish to testPut");
    }

}

