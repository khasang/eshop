package io.khasang.eshop.config.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


//spring-dispatcher-servlet.xml аналах настройки в xml файла
@Configuration
//Вкл. WebMVS Spring FrameWork
@EnableWebMvc
//Указываем где в каком каталоге будет осуществляться поиск компонентов нашего проекта
@ComponentScan({"io.khasang.eshop"})
public class WebConfig extends WebMvcConfigurerAdapter {

    //Передаем метод в облако бинов, настраиваем WebMvc
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html; charset=utf-8");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
    }
}
