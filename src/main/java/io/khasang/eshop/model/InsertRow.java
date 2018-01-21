package io.khasang.eshop.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class InsertRow {
    private JdbcTemplate jdbcTemplate;

    public InsertRow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public InsertRow() {
    }

    public String insertRow() {
        try {
            jdbcTemplate.update("insert into cat values (4, 'Victory', 'cat4', 2);");
            return "insert completed";

        } catch (Exception e) {
            return "exception " + e;
        }
    }
}
