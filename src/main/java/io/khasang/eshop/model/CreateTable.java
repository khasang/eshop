package io.khasang.eshop.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateTable {

    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {
    }

    public String createTableStatus() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS films");
            jdbcTemplate.execute("CREATE TABLE public.films\n" +
                    "(\n" +
                    "  id integer NOT NULL,\n" +
                    "  name character varying(255),\n" +
                    "  CONSTRAINT films_pkey PRIMARY KEY (id)\n" +
                    ");");
            return "Table created";
        } catch (Exception e) {
            return "table creation failed" + e;
        }

//        try {
//            jdbcTemplate.update(
//                    "insert into employees (employees_id, name, functions) values (?, ?, ?)",
//                    18, "admin", "admin");
//
//            jdbcTemplate.update("update employees set employees_id = ?, name = ?, functions = ? where employees_id = ?",
//                    18, "admin", "admin");
//
//            return "the request add admin OK!";
//        } catch (Exception e) {
//            return "the request was not received" + e;
//        }
    }

}


