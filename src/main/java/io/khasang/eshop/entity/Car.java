package io.khasang.eshop.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
@Column(columnDefinition = "DATE")
    private LocalDate year;
    private String model;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Cat> catList = new ArrayList<>();
}
