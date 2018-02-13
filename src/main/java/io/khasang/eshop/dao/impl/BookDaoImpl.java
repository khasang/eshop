package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BookDao;
import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import org.hibernate.Session;

public class BookDaoImpl extends BasicDaoImpl<Book> implements BookDao {
    public BookDaoImpl(Class<Book> entityClass) {
        super(entityClass);
    }

    public void add() {
        Session session1 = session.openSession();
        session1.beginTransaction();

        Book book = new Book("Психология");
        Author author1 = new Author("Ерик Берн");
        Author author2 = new Author("Зигмунд Фрейд");
        book.addAuthor(author1);
        book.addAuthor(author2);
        session1.save(book);
        session1.getTransaction().commit();
        session1.close();
    }
}
