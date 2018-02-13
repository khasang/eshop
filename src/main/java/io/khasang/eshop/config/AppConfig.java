package io.khasang.eshop.config;

import io.khasang.eshop.dao.*;
import io.khasang.eshop.dao.impl.*;
import io.khasang.eshop.entity.*;
import io.khasang.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value="classpath:util.properties")
@PropertySource(value ="classpath:auth.properties")

public class AppConfig {
    @Autowired
    private Environment environment;  //считывание параметров из ф-ла util.properties

    @Bean // настройки по пдключению к базе
    public DriverManagerDataSource dataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
    dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
    dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.user"));
    dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.pass"));
    return dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());
        jdbcDao.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcDao.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcDao;
    }

    @Bean  // основной класс, который работает с запросами внутри базы данных - теперь можем обращаться к базе данных
    public JdbcTemplate JdbcTemplate(){
           JdbcTemplate jdbcTemplate = new JdbcTemplate();
           jdbcTemplate.setDataSource(dataSource());
           return jdbcTemplate;
    }

    @Bean
    public CatDao catDao(){
        return new CatDaoImpl(Cat.class);
    }

    @Bean
    public BookDao bookDao(){
        return new BookDaoIml(Book.class);
    }

    @Bean
    public Cat1Dao cat1Dao(){
        return new Cat1DaoImpl(Cat1.class);
    }

    @Bean
    public AuthorDao authorDao(){
        return new AuthorDaoImpl(Author.class);
    }

    @Bean
    public CreateTable createTable(){
        return new CreateTable(JdbcTemplate());

    }

}
