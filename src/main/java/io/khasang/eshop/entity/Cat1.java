package io.khasang.eshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "cat1")
        public class Cat1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "cat1", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private CatWoman1 catWoman1;

    public Cat1() {
    }

    public Cat1(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CatWoman1 getCatWoman1() {
        return catWoman1;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCatWoman1(CatWoman1 catWoman1) {
        this.catWoman1 = catWoman1;
    }

    public void addCatWoman1(CatWoman1 catWoman1) {
        catWoman1.setCat1( this );
        this.catWoman1 = catWoman1;
    }

    public void removeCatWoman1atWoman1() {
        if (catWoman1 != null ) {
            catWoman1.setCat1( null );
            this.catWoman1 = null;
        }
    }
}
