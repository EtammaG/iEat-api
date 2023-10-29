package com.etammag.ieat.config;

import com.etammag.ieat.filter.JwtAuthTokenFilter;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "employeeDetailsService")
    private UserDetailsService employeeDetailsService;

    @Resource(name = "adminDetailsService")
    private UserDetailsService adminDetailsService;

    @Resource(name = "userVerCodeDetailsService")
    private UserDetailsService userVerCodeDetailsService;

    @Resource
    private JwtAuthTokenFilter jwtAuthTokenFilter;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider adminDaoAuthProvider() {
        DaoAuthenticationProvider adminDaoAuthProvider = new DaoAuthenticationProvider();
        adminDaoAuthProvider.setUserDetailsService(adminDetailsService);
        adminDaoAuthProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return adminDaoAuthProvider;
    }

    @Bean
    public DaoAuthenticationProvider employeeDaoAuthProvider() {
        DaoAuthenticationProvider employeeDaoAuthProvider = new DaoAuthenticationProvider();
        employeeDaoAuthProvider.setUserDetailsService(employeeDetailsService);
        employeeDaoAuthProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return employeeDaoAuthProvider;
    }

    @Bean
    public DaoAuthenticationProvider userVerCodeDaoAuthProvider() {
        DaoAuthenticationProvider userVerCodeDaoAuthProvider = new DaoAuthenticationProvider();
        userVerCodeDaoAuthProvider.setUserDetailsService(userVerCodeDetailsService);
        userVerCodeDaoAuthProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return userVerCodeDaoAuthProvider;
    }

    @Bean
    public AuthenticationManager adminAuthManager() {
        return new ProviderManager(adminDaoAuthProvider());
    }

    @Bean
    public AuthenticationManager employeeAuthManager() {
        return new ProviderManager(employeeDaoAuthProvider());
    }

    @Bean
    @Primary
    public AuthenticationManager userVerCodeAuthManager() {
        return new ProviderManager(userVerCodeDaoAuthProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().sameOrigin();
        //运行前端在同域名下嵌入网页

        http
                .authorizeRequests()
                .antMatchers("/resource/**").permitAll()

                .antMatchers(
                        "/swagger-ui.html"
                ).hasAnyAuthority("ADMIN")

        ;

        http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}