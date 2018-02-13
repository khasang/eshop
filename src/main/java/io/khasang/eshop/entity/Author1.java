package io.khasang.eshop.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "author1")
public class Author1 implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String registrationName;

    @OneToMany(mappedBy = "author1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorPublisher1> publishers1 = new ArrayList<>();

    public Author1() {
    }

    public Author1(String registrationName) {
        this.registrationName = registrationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public List<AuthorPublisher1> getPublishers1() {
        return publishers1;
    }

    public void setPublishers1(List<AuthorPublisher1> publishers1) {
        this.publishers1 = publishers1;
    }

    public void addPublisher1(Publisher1 publisher1) {
        AuthorPublisher1 authorPublisher1 = new AuthorPublisher1 ( this, publisher1 );
        publishers1.add( authorPublisher1 );
        publisher1.getAuthors().add( authorPublisher1 );
    }

    public void removePublisher1(Publisher1 publisher1) {
        AuthorPublisher1 authorPublisher1 = new AuthorPublisher1 ( this, publisher1 );
        publisher1.getAuthors().remove( authorPublisher1 );
        publishers1.remove(authorPublisher1);
        authorPublisher1.setAuthor1( null );
        authorPublisher1.setPublisher1( null );
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Author1 author1 = (Author1) o;
        return Objects.equals( registrationName, author1.registrationName );
    }

    @Override
    public int hashCode() {
        return Objects.hash( registrationName );
    }
}
