package io.khasang.eshop.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DeleteRecord {
    private JdbcTemplate jdbcTemplate;

    public DeleteRecord() {
    }

    public DeleteRecord(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String deleteRecord() {
        try {
            jdbcTemplate.update("DELETE from films where id = ?", 1);
            return "Delete record";
        } catch (Exception e) {
            return "exception" + e;
        }
    }
}
