package io.khasang.eshop.config;

import io.khasang.eshop.model.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Cat cat() {
        return new Cat("Murzik");
    }

}
