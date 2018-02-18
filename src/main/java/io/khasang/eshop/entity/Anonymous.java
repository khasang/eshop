package io.khasang.eshop.entity;

import javax.persistence.*;


@Entity
@Table(name = "anonymous")
public class Anonymous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anonymous_id")
    private long id;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}


