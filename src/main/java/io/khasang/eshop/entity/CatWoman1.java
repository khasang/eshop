package io.khasang.eshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "catwoman1")
public class CatWoman1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String color;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat1_id")
    private Cat1 cat1;

    public CatWoman1() {
    }

    public CatWoman1(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat1 getCat1() {
        return cat1;
    }

    public Long getId(int i) {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCat1(Cat1 cat1) {
        this.cat1 = cat1;
    }

}
