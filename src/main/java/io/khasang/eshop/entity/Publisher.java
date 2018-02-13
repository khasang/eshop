package io.khasang.eshop.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(name = "number")
    private String number;

    private String registrationCode;

    @ManyToMany(mappedBy = "publishers")
    private List<Author> authors = new ArrayList<>();

    public Publisher() {
    }

    public Publisher(String name, String number, String registrationCode) {
        this.name = name;
        this.number = number;
        this.registrationCode = registrationCode;
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

    public String getRegisnrationCode() {
        return registrationCode;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Publisher publisher = (Publisher) o;
        return Objects.equals( name, publisher.name ) &&
                Objects.equals( number, publisher.number ) &&
                Objects.equals( registrationCode, publisher.registrationCode );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, number, registrationCode );
    }
}
