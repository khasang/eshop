package io.khasang.eshop.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// POJO
@Component("catInterface")
@Scope("prototype")
public class Cat implements CatInterface {
    private String name;

    public Cat() {
    }

//    public Cat(String name) {
//
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @PostConstruct
    public void setName(){
        name = "Jack";
    }
    @PreDestroy
    public void clean(){
        name = "Кот";
    }
}


