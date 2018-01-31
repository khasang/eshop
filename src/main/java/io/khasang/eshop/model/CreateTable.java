package io.khasang.eshop.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public CreateTable() {
    }

    public String createTableStatus() {
//        try {
//            jdbcTemplate.execute("DROP TABLE IF EXISTS films");
//            jdbcTemplate.execute("CREATE TABLE public.films\n" +
//                    "(\n" +
//                    "    id integer NOT NULL,\n" +
//                    "    name character varying(255) COLLATE pg_catalog.\"default\",\n" +
//                    "    CONSTRAINT id PRIMARY KEY (id)\n" +
//                    ");");
//            return "Table created";
//        } catch (Exception e){
//            return "table created failed" + e;
//        }

//        try {
//            jdbcTemplate.execute("select c.name, c.description \n" +
//                    "from cat c where c.color_id in (select cl.id from color cl where cl.color_name = 'grey')");
//
//            //return "the request was received";
//            return "the request OK!";
//        } catch (Exception e) {
//            return "the request was not received" + e;
//        }

        try{
//            jdbcTemplate.update(
//                    "insert into cat (id, name, description, color_id) values (?, ?, ?, ?)",
//                    9, "Leonard","good cat", 4);

            jdbcTemplate.update("update cat set name = ?, description = ?, color_id = ?  where id = ?",
                    "Kesha", "best cat", 4, 9);

            return "the request OK!";
        } catch (Exception e) {
            return "the request was not received" + e;
        }
    }

    public int createInquiryStatus() {

        try {
            int rowCount = this.jdbcTemplate.queryForObject("select count(*) from cat where color_id = ?", Integer.class, 1);
            return rowCount;
        } catch (Exception e) {
            return 0; //"the request was not received" + e;
        }
    }

//    public List createInquiryStatus1() {
//
//        List<Cat> cats = this.jdbcTemplate.query(
//                "select description, name from cat",
//                new RowMapper<Cat>() {
//                    public Cat mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Cat cat = new Cat();
//                        cat.setName(rs.getString("name"));
//                        cat.setDescription(rs.getString("description"));
//                        return cat;
//                    }
//                });
//        return cats;
//
//    }

}
