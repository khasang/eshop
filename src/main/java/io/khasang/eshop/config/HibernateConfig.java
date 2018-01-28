package io.khasang.eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:hibernate.properties")
public class HibernateConfig {
    //Что бы в данном классе можно было взаимодействовать с hibernate.properties
    @Autowired
    private Environment environment;

    //Устанавливаем настройки из файла util для hibernate
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("hibernate.driver"));
        dataSource.setUrl(environment.getRequiredProperty("hibernate.url"));
        dataSource.setUsername(environment.getRequiredProperty("hibernate.user"));
        dataSource.setPassword(environment.getRequiredProperty("hibernate.password"));
        return dataSource;
    }

    //Отвечает за соединение на уровне Java кода, эшкуэль, критерия, потом переводит кода в понятный для БД язык
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("io.khasang.eshop.entity");
        sessionFactory.setHibernateProperties(properties());
        return sessionFactory;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2dll.auto", environment.getRequiredProperty("hibernate.hbm2dll.auto"));
        properties.put("show_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }
}
