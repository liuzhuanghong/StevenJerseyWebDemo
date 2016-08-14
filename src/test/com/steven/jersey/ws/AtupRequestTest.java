package com.steven.jersey.ws;

import com.steven.jersey.com.steven.jersey.util.ws.AtupRequest;
import com.steven.jersey.domain.Books;
import org.junit.Test;

/**
 * Created by liuzhuanghong on 16/8/15.
 */
public class AtupRequestTest {

    public static final String BASE_URI = "http://localhost:8088/jersey-web-demo/webapi/";
    @Test
    public void testGet() {
        AtupRequest<String, Books> atupGet = new AtupRequest();
        Books books = atupGet.rest(AtupRequest.GET, AtupRequestTest.BASE_URI + "books", Books.class);
        System.out.println(books);

        AtupRequest<String, String> atupDelete = new AtupRequest();
        String msg = atupDelete.rest(AtupRequest.DELETE, AtupRequestTest.BASE_URI + "books/1", String.class);
        System.out.println(msg);

    }
}
