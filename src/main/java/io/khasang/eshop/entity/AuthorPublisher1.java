package io.khasang.eshop.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "authorpublisher1")
public class AuthorPublisher1{
    @Id
    @ManyToOne
    private Author1 author1;

    @Id
    @ManyToOne
    private Publisher1 publisher1;

    public AuthorPublisher1() {
    }

    public AuthorPublisher1(Author1 author1, Publisher1 publisher1) {
        this.author1 = author1;
        this.publisher1 = publisher1;
    }

    public Author1 getAuthor1() {
        return author1;
    }

    public void setAuthor1(Author1 author1) {
        this.author1 = author1;
    }

    public Publisher1 getPublisher1() {
        return publisher1;
    }

    public void setPublisher1(Publisher1 publisher1) {
        this.publisher1 = publisher1;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        AuthorPublisher1 that = (AuthorPublisher1) o;
        return Objects.equals( author1, that.author1 ) &&
                Objects.equals( publisher1, that.publisher1 );
    }

    @Override
    public int hashCode() {
        return Objects.hash( author1, publisher1 );
    }

}
