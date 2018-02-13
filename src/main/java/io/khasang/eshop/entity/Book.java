package io.khasang.eshop.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

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

    public List<Author> getArrayList() {
        return authors;
    }

    public void setArrayList(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.getArrayList().add(author);
    }

    @Override
    public int hashCode() {
        return 31 * (int) id + (this.name != null ? name.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book book = (Book) obj;
        if (id != book.getId()) return false;
        if (name != null ? !name.equals(book.getName()) : book.name != null) return false;
        return true;
    }
}
