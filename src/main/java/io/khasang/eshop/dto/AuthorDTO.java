package io.khasang.eshop.dto;

import java.util.Objects;

public class AuthorDTO {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return id == authorDTO.id &&
                Objects.equals(name, authorDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
