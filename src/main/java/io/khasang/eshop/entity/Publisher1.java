package io.khasang.eshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publisher1")
public class Publisher1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "number")
    private String number;

    private String registrationCode;

    @OneToMany(mappedBy = "publisher1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorPublisher1> author_publ = new ArrayList<>();

    public Publisher1() {
    }

    public Publisher1(String name, String number, String registrationCode) {
        this.name = name;
        this.number = number;
        this. registrationCode = registrationCode;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public List<AuthorPublisher1> getAuthor_publ() {
        return author_publ;
    }

    public void setAuthor_publ(List<AuthorPublisher1> author_publ) {
        this.author_publ = author_publ;
    }

    public String getRegistrationlCode() {
        return registrationCode;
    }

    public List<AuthorPublisher1> getAuthors() {
        return author_publ;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Publisher1 publisher1 = (Publisher1) o;
        return Objects.equals( name, publisher1.name ) &&
                Objects.equals( number, publisher1.number ) &&
                Objects.equals(registrationCode, publisher1. registrationCode );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, number, registrationCode );
    }

}
