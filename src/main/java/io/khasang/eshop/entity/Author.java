package io.khasang.eshop.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "author")
public  class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String registrationName;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Publisher> publishers = new ArrayList<>();

    public Author() {
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

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public Author(String registrationName) {
        this.registrationName = registrationName;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void addPublisher(Publisher publisher) {
        publishers.add( publisher );
        publisher.getAuthors().add(this);
    }

    public void removePublisher(Publisher publisher) {
        publishers.remove( publisher );
        publisher.getAuthors().remove( this );
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Author author = (Author) o;
        return Objects.equals( registrationName, author.registrationName );
    }

    @Override
    public int hashCode() {
        return Objects.hash( registrationName );
    }
}
