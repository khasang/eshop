package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.Author1Dao;
import io.khasang.eshop.entity.Author1;
import io.khasang.eshop.service.Author1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("author1Service")
public class Author1ServiceImpl implements Author1Service {
    @Autowired
    private Author1Dao author1Dao;

    @Override
    public List<Author1> getAllAuthors() {
        return author1Dao.getList();
    }

    @Override
    public Author1 getById(long id) {
        return author1Dao.getById(id);
    }

    @Override
    public Author1 addAuthor(Author1 author1) {
        return author1Dao.add(author1);
    }

    @Override
    public Author1 deleteAuthor(long id) {
        return author1Dao.delete(getById(id));
    }

    @Override
    public Author1 updateAuthor(Author1 author1) {
        return author1Dao.update(author1);
    }

    @Override
    public Author1 patchAuthor(Author1 author1) {
        return author1Dao.update(author1);
    }
}
