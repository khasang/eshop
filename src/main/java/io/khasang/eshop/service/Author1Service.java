package io.khasang.eshop.service;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Author1;

import java.util.List;

public interface Author1Service {
    /**
     * method for receiving all authors from DB
     *
     * @return List of all authors
     **/

    // void getAllAuthors
    List<Author1> getAllAuthors();

    /**
     * method for receiving specify author by id
     *
     * @param id = authors id
     * @return author by id
     **/
    Author1 getById(long id);

    /**
     * method for add author
     *
     * @param author1 = new author
     * @return created author
     **/
    Author1 addAuthor(Author1 author1);

    /**
     * method for delete specify author by id
     *
     * @param id = authors id
     * @return author by id
     **/
    Author1 deleteAuthor(long id);

    /**
     * method for update specify author by id
     *
     * @param author1 = new author
     * @return updated author
     **/
    Author1 updateAuthor(Author1 author1);

    Author1 patchAuthor(Author1 author1);
}
