package io.khasang.eshop.service;

import io.khasang.eshop.entity.Author;

import java.util.List;

public interface AuthorService {
    /**
     * method for receiving all authors from DB
     *
     * @return List of all authors
     */
    List<Author> getAllAuthors();

    /**
     * method for add author
     *
     * @param author = new author
     * @return created author
     */
    Author addAuthor(Author author);

    /**
     * method for receiving specify author by id
     *
     * @param id = author's id
     * @return author by id
     */
    Author getAuthorById(long id);

    /**
     * method for update author
     *
     * @param author = updated author
     * @return updated author
     */
    Author updateAuthor(Author author);

    /**
     * method for delete specify author by id
     *
     * @param id = author's id
     * @return author by id
     */
    Author deleteAuthor(long id);
}
