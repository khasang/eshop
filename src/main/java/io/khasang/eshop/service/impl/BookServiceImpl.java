package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.BookDao;
import io.khasang.eshop.dto.BookDTO;
import io.khasang.eshop.entity.Book;
import io.khasang.eshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookDTO bookDTO;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookDTO.getBookDTOList(bookDao.getList());
    }

    @Override
    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Book addBook(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book deleteBook(long id) {
        return bookDao.delete(getBookById(id));
    }

    @Override
    public Book updateBook(Book book) {
        return bookDao.update(book);
    }
}
