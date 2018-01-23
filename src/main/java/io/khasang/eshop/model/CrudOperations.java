package io.khasang.eshop.model;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;

@Transactional
public class CrudOperations {

    private JdbcTemplate jdbcTemplate;

    public CrudOperations(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CrudOperations() {
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
            return "Table's created!";
        } catch (Exception e) {
            return "Table creation is failed!" + e;
        }
    }

    public String read(int id) {
        String sql = "SELECT * FROM films WHERE id = ?";

        try {
            Film film = jdbcTemplate.queryForObject(sql, new FilmMapper(), id);
            System.out.println(film.getId() + " " + film.getName());
            return "The data has been read! " + film.getId() + " " + film.getName();
        } catch (Exception e) {
            return "Data reading is failed!" + e;
        }
    }

    public String update() {
        String sql = "INSERT INTO films (id, name) VALUES(?,?)";
        try {
            jdbcTemplate.update(sql, 1, "Scream");
            return "The data has been inserted successfully";
        } catch (Exception e) {
            return "Data insertion is failed!" + e;
        }
    }

    public String delete() {
        String sql = "DELETE FROM films WHERE id = ?";
        try {
            jdbcTemplate.update(sql, 1);
            return "The data has been deleted successfully";
        } catch (Exception e) {
            return "Data removal is failed!" + e;
        }
    }

    private static final class FilmMapper implements RowMapper<Film> {

        @Override
        public Film mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Film film = new Film();
            film.setId(resultSet.getInt("id"));
            film.setName(resultSet.getString("name"));
            return film;
        }
    }

}
