package io.khasang.eshop.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/** Этот класс помечен как @Transactional, что означает откат всех
 * записей к предыдущему значению, если любая из операций в этом методе завершится неудачей(все RunTimeException),
 * а также повторно бросит оригинальное исключение.
 */
@Transactional
public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable(){
    }

    public String createTableCats(){
        try{
            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
            jdbcTemplate.execute("CREATE TABLE cats\n" +
                    "   (" +
                    "   id integer NOT NULL,\n" +
                    "   name character varying(255),\n" +
                    "   description character varying(255),\n" +
                    "   color_id integer,\n" +
                    "   CONSTRAINT cat_pkey PRIMARY KEY (id))");
            return "Table Cats Create";
        } catch (Exception e){
            return "Create Table Cats Failed";
        }
    }

    public String createTableColors(){
        try{
            jdbcTemplate.execute("DROP TABLE IF EXISTS colors");
            jdbcTemplate.execute("CREATE TABLE colors\n" +
                    "    (" +
                    "    id integer NOT NULL,\n" +
                    "    name character varying(255),\n" +
                    "    CONSTRAINT color_pkey PRIMARY KEY (id))");
            return "Table Colors Create";
        } catch (Exception e){
            return "Create Table Colors Failed";
        }
    }

    public String addCatsInTable(){
        try{
            jdbcTemplate.execute("INSERT INTO cats VALUES (1, 'Barsik', 'angry cat', 1)");
            jdbcTemplate.execute("INSERT INTO cats VALUES (2, 'Murzik', 'goot cat', 2)");
            jdbcTemplate.execute("INSERT INTO cats VALUES (3, 'Kapapap', 'cool cat', 3)");
            jdbcTemplate.execute("INSERT INTO cats VALUES (4, 'Sonya', 'very angry cat', 3)");
            return "Add Cats";
        }catch (Exception e){
            return "Failed Add Cats in Table";
        }
    }

    public String addColorsInTable(){
        try{
            jdbcTemplate.execute("INSERT INTO colors VALUES (1, 'white')");
            jdbcTemplate.execute("INSERT INTO colors VALUES (2, 'red')");
            jdbcTemplate.execute("INSERT INTO colors VALUES (3, 'grey')");
            return "Add Colors";
        }catch (Exception e){
            return "Failed Add Colors in Table";
        }
    }

    public String updateCats(){
        try{
            jdbcTemplate.execute("UPDATE cats SET name = 'Kapa', color_id = 2 " +
                    "WHERE name = 'Kapapap'");
            return "Cats Update";
        } catch(Exception e){
            return "Cats Not Update";
        }
    }

    public String deletCats(){
        try{
            jdbcTemplate.execute("DELETE FROM cats WHERE name = 'Sonya'");
            return "Cats Delete";
        } catch(Exception e){
            return "Cats Not Delete";
        }
    }

    public String selectCats(){
        try {
            jdbcTemplate.execute("SELECT * FROM cats c WHERE c.color_id in" +
                    "(SELECT cl.id FROM colors cl WHERE name = 'red')");
            return "Select Complete";
        } catch(Exception e){
            return "Select NoT Complete";
        }
    }

    public String selectCats1(){
        try {
            jdbcTemplate.execute("SELECT name, description, CASE WHEN name = 'Kapa'" +
                    " THEN 'my cat' else '' END FROM cats");
            return "Select Complete";
        } catch(Exception e){
            return "Select NoT Complete";
        }
    }
}
