package io.khasang.eshop.DTO;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class BookDTO {
    private long id;
    private int version;
    private String name;
    private List<AuthorDTO> authorsList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookDTO getBook(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setVersion(book.getVersion());
        bookDTO.setAuthorsSet(getAuthorDTOList(book.getAuthorsList()));

        return bookDTO;
    }

    public List<BookDTO> getBookDTOList(List<Book> bookList) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book : bookList) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            bookDTO.setVersion(book.getVersion());
            bookDTO.setAuthorsSet(getAuthorDTOList(book.getAuthorsList()));

            bookDTOS.add(bookDTO);
        }

        return bookDTOS;
    }

    private List<AuthorDTO> getAuthorDTOList(List<Author> authorList) {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        for (Author author : authorList) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(author.getId());
            authorDTO.setName(author.getName());
            authorDTO.setVersion(getVersion());

            authorDTOS.add(authorDTO);
        }
        return authorDTOS;
    }

    public void setAuthorsSet(List<AuthorDTO> authorsList) {
        this.authorsList = authorsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id &&
                version == bookDTO.version &&
                Objects.equals(name, bookDTO.name) &&
                Objects.equals(authorsList, bookDTO.authorsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name, authorsList);
    }

    @Override
    public String toString() {
        return "Book name: " + this.name + ", author: " + Arrays.toString(this.authorsList.toArray());
    }
}
