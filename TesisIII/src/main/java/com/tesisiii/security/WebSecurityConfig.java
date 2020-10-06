package com.tesisiii.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    DataSource datasource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(datasource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("SELECT login,password,status "
                        + "FROM usuario "
                        + "WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT login,profile "
                        + "FROM usuario "
                        + "WHERE login = ?");
        
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/admin").hasAnyAuthority("ADMIN")
            .antMatchers("/pm").hasAnyAuthority("PM")
            .antMatchers("/user").hasAnyAuthority("USER")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/default", true)
            .and()
            .logout().clearAuthentication(true)
                .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .logoutSuccessUrl("/login") .permitAll();
    }
    @Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
