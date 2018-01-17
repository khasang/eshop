package io.khasang.eshop.model;

import org.springframework.stereotype.Component;

//POJO
@Component("catInterface")
public class Cat implements CatInterface {

    private String name;

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
