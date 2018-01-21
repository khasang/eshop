package io.khasang.eshop.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.RowSet;

@Transactional
public class SelectRecord {
    private JdbcTemplate jdbcTemplate;

    public SelectRecord(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SelectRecord() {
    }

    public String selectRecordStatus() {
        try {
            jdbcTemplate.execute("SELECT * FROM CAT");
            return "select completed";
        } catch (Exception e) {
            return "exception " + e;
        }
    }
}
