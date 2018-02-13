package io.khasang.eshop.service;

import io.khasang.eshop.entity.Author;

import java.util.List;

public interface AuthorService {
    /**
     * method for receiving all authors from DB
     *
     * @return List of all authors
     **/

    // void getAllAuthors
    List<Author> getAllAuthors();

    /**
     * method for receiving specify author by id
     *
     * @param id = authors id
     * @return author by id
     **/
    Author getById(long id);

    /**
     * method for add author
     *
     * @param author = new author
     * @return created author
     **/
    Author addAuthor(Author author);

    /**
     * method for delete specify author by id
     *
     * @param id = authors id
     * @return author by id
     **/
    Author deleteAuthor(long id);

    /**
     * method for update specify author by id
     *
     * @param author = new author
     * @return updated author
     **/
    Author updateAuthor(Author author);

    Author patchAuthor(Author author);
}
