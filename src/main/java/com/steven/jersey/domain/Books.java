package com.steven.jersey.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by liuzhuanghong on 16/8/14.
 */
@XmlRootElement(name = "books")
public class Books {
    private List<Book> bookList;

    /**
     * <p>Constructor for Books.</p>
     */
    public Books() {
        super();
    }

    /**
     * <p>Constructor for Books.</p>
     *
     * @param bookList a {@link java.util.List} object.
     */
    public Books(final List<Book> bookList) {
        super();
        this.bookList = bookList;
    }

    /**
     * <p>Getter for the field <code>bookList</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @XmlElement(name = "book")
    @XmlElementWrapper
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * <p>Setter for the field <code>bookList</code>.</p>
     *
     * @param bookList a {@link java.util.List} object.
     */
    public void setBookList(final List<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{" + bookList + "}";
    }


    /*
    public Book addBook(Book book){
        if(book != null) {
            bookList.add(book);
            return book;
        }
        return new Book(-1L, "");
    }

    public Book findBook(Long bookId){

        for(Book item : bookList){
            if(item.getBookId() == bookId){
                return item;
            }
        }
        return new Book(-1L, "");
    }
    */
}
