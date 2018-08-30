package org.softuni.secondtech.config;

import org.softuni.secondtech.enums.Roles;
import org.softuni.secondtech.handlers.AuthenticationHandler;
import org.softuni.secondtech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final AuthenticationHandler handler;

    @Autowired
    public SecurityConfiguration(UserService userService, AuthenticationHandler handler) {
        this.userService = userService;
        this.handler = handler;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();

        repository.setSessionAttributeName("_csrf");
        repository.setHeaderName("X-CSRF-TOKEN");

        return repository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf()
                    .csrfTokenRepository(csrfTokenRepository())
                .and()
                .authorizeRequests()
                    .antMatchers("/", "/login", "/register",
                            "/laptops/all", "/laptops/details/**",
                            "/smartphones/all", "/smartphones/details/**",
                            "/tablets/all", "/tablets/details/**")
                        .permitAll()
                    .antMatchers("/css/**", "/js/**", "/images/**")
                        .permitAll()
                    .antMatchers("/orders/all", "/comments/all")
                        .hasAnyAuthority(Roles.MODERATOR.name(), Roles.ADMIN.name())
                    .antMatchers("/admin/**")
                        .hasAuthority(Roles.ADMIN.name())
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .successHandler(this.handler)
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .rememberMe()
                    .rememberMeParameter("rememberMe")
                    .key(UUID.randomUUID().toString())
                    .userDetailsService(this.userService)
                    .rememberMeCookieName("remember")
                    .tokenValiditySeconds(1200)
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/error/unauthorized");
    }
}
