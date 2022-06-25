package br.senai.config;

import br.senai.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/usuario/**").permitAll()
                .antMatchers("/endereco/**").permitAll()
                .antMatchers("/bootstrap-5.0.0/**").permitAll()
                .antMatchers("/fontawesome-5.15.3/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/cliente/list", true)
                .permitAll()
                .and()
                .rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(usuarioService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}