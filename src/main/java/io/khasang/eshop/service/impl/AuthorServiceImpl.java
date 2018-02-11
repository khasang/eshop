package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.AuthorDao;
import io.khasang.eshop.entity.Author;
import io.khasang.eshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDao authorDao;

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getList();
    }

    @Override
    public Author addAuthor(Author author) {
        return authorDao.add(author);
    }

    @Override
    public Author getAuthorById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorDao.update(author);
    }

    @Override
    public Author deleteAuthor(long id) {
        return authorDao.delete(getAuthorById(id));
    }
}
