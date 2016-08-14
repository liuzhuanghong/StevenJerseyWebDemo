package com.steven.jersey.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图书实体类
 * Created by liuzhuanghong on 16/8/13.
 */
@XmlRootElement
public class Book {

    /**
     * 图书ID
     * */
    private Long bookId;
    /**
     * 图书名称
     * */
    private String bookName;
    /**
     * 图书出版商
     * */
    private String publisher;


    public Book() {
        super();
    }

    public Book(final Long bookId) {
        this.bookId = bookId;
    }

    public Book(final String bookName) {
        this.bookName = bookName;
    }

    public Book(final Long bookId, final String bookName) {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Book(final Long bookId, final String bookName, String publisher) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisher = publisher;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return bookId + ":" + bookName + ":" + publisher;
    }
}
