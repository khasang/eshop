package io.khasang.eshop.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component()
@Qualifier("gaf")
public class Dog implements PetInterface {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public Dog() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
