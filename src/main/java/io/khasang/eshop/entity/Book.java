package io.khasang.eshop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    private String name;
    private String description;
    private String isdn;

    @ManyToMany
    private List<Author> authorList = new ArrayList<>();


//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<Author> authorList = new ArrayList<>();
//
    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
