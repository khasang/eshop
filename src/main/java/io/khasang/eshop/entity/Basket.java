package io.khasang.eshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //Пользователь
    @Column(name = "users")
    private String user;
    private String goods;

    private BigDecimal price;
    private int quantity;
    @Version
    private int version;

    public Basket(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean equals(Basket product) {
        return getGoods().equals(product.getGoods()) && getUser().equals(product.getUser());
    }
}
