package io.khasang.eshop.dto;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDTO {
    private long id;
    private String name;
    private String description;
    private String isdn;
    private List<AuthorDTO> authorList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public List<BookDTO> getBookDTOList(List<Book> bookList) {
        List<BookDTO> bookListDTOS = new ArrayList<>();
        for (Book book : bookList) {
            List<AuthorDTO> authorListDTOS = new ArrayList<>();
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            bookDTO.setDescription(book.getDescription());
            bookDTO.setIsdn(book.getIsdn());

            for (Author author : book.getAuthorList()) {
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(author.getId());
                authorDTO.setName(author.getName());
                authorListDTOS.add(authorDTO);
            }
            bookDTO.setAuthorList(authorListDTOS);
            bookListDTOS.add(bookDTO);
        }
        return bookListDTOS;
    }

    public BookDTO getBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setIsdn(book.getIsdn());
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        for (Author author : book.getAuthorList()) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(author.getId());
            authorDTO.setName(author.getName());
            authorDTOS.add(authorDTO);
        }
        bookDTO.setAuthorList(authorDTOS);
        return bookDTO;
    }

    public void setAuthorList(List<AuthorDTO> authorList) {
        this.authorList = authorList;
    }
}
