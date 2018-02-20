package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.BookDao;
import io.khasang.eshop.entity.Book;
import io.khasang.eshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    public BookDao bookDao;

    @Override
    public Book addBook(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getBook(long id){
        return bookDao.getById(id);
    }

    @Override
    public Book deleteBook(long id) {
        return bookDao.delete(getBook(id));
    }

}
