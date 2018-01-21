package io.khasang.eshop.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UpdateTable {
    private JdbcTemplate jdbcTemplate;

    public UpdateTable() {
    }

    public UpdateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String update() {
        try {
            jdbcTemplate.update("update films set name = 'film' where id = ?", 1);
            return "update table";
        } catch (Exception e) {
            return "exception " + e;
        }
    }
}
