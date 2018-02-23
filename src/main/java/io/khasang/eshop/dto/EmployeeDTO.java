package io.khasang.eshop.dto;

import java.util.Objects;

public class EmployeeDTO {
    private long id;

    private String name;
    private String functions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(functions, that.functions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, functions);
    }

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

    public String getFunctions() {

        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }
}
