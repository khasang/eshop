package io.khasang.eshop.config;

import io.khasang.eshop.model.Cat;
import io.khasang.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.activation.DataSource;

@Configuration
@PropertySource(value = "classpath:util.properties")
public class AppConfig {

    //Что бы в данном классе можно было взаимодействовать с util.prperties
    @Autowired
    private Environment environment;

    //Устанавливаем настройки из вайла util
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.user"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    //Чрез данный медот создаем объект, через который будем общаться с сервером
    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public Cat cat() {
        return new Cat("Murzik");
    }

    //Добавляем класс с нашими запросами в облако Beans, передав ему объект для связи с сервером
    @Bean
    public CreateTable createTable(){
        return new CreateTable(jdbcTemplate());
    }
}
