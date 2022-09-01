package com.example.projectsem2.auth.config;

import com.example.projectsem2.auth.jwt.AuthEntryPointJwt;
import com.example.projectsem2.auth.jwt.AuthTokenFilter;
import com.example.projectsem2.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


//        http.requestMatchers().antMatchers("/test").and().authorizeRequests();
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/api/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/lib/**").permitAll();
        http.authorizeRequests().antMatchers("/mail/**").permitAll();
        http.authorizeRequests().antMatchers("**/css/**").permitAll();
        http.authorizeRequests().antMatchers("/js/**").permitAll();
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        http.authorizeRequests().antMatchers("/fonts/**").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/home").permitAll();
        http.authorizeRequests().antMatchers("/uploads/**").permitAll();
        http.authorizeRequests().antMatchers("/api/**").permitAll();
        http.authorizeRequests().antMatchers("/img/**").permitAll();
        http.authorizeRequests().antMatchers("/static/**").permitAll();
        http.authorizeRequests().antMatchers("/admin/css/**").permitAll();
        http.authorizeRequests().antMatchers("/admin/js/**").permitAll();
        http.authorizeRequests().antMatchers("/admin/fonts/**").permitAll();

        http.authorizeRequests()
                .antMatchers("/login", "/logout", "/register", "/process_register", "/process_login")
                .permitAll();

        // bug - phai co tk - va nhieu hon 1 role thi moi dung dc
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");
        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll();


        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

//        http.addFilterBefore(authenticationJwtTokenFilter(),
//                UsernamePasswordAuthenticationFilter.class);

//        http.requestMatchers().antMatchers("/test").and().authorizeRequests();

        http.authorizeRequests().antMatchers("/admin/css/**").permitAll();
        http.authorizeRequests().antMatchers("/admin/js/**").permitAll();
        http.authorizeRequests().antMatchers("/admin/fonts/**").permitAll();
        http.authorizeRequests()
                .antMatchers("/login", "/logout", "/register", "/process_register", "/process_login")
                .permitAll();

        // bug - phai co tk - va nhieu hon 1 role thi moi dung dc
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");

//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/404");
//
//        // has 1 role - not work -> has many role -> work --> phai nhu nay ms chay :vv
//        http.authorizeRequests().antMatchers("/admin").access("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGE')");
        http.authorizeRequests().antMatchers("/api/test/all").access("hasAuthority('ROLE_USER')");
        http.authorizeRequests().antMatchers("/api/test/mod").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/api/test/admin").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/test").access("hasAuthority('ROLE_USER')");

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                j_spring_security_check
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}
