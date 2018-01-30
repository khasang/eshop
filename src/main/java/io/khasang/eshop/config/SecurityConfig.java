package io.khasang.eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Указываем что это конфигурационный класс, при старте подкружается в спинг контекст
@Configuration
//Вкл. WebSecurity
@EnableWebSecurity
//WebConfig Security - его настройки
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    //Настрока HttpSecurity, настройки авторизации, по каким путям кто будет иметь доступ
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and().csrf().disable().formLogin().defaultSuccessUrl("/admin", false);
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER')")
                .and().formLogin().defaultSuccessUrl("/user", false);
    }
    //Добавление пользователей, не используется в проектах.
//    @Override
//    public void configure(AuthenticationManagerBuilder auth)throws Exception{
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//    }

    //8 byte SHA1
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
