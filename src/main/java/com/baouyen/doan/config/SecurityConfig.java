package com.baouyen.doan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register", "/register/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/partner/**").hasAuthority("PARTNER")
                .antMatchers("/user/**").hasAuthority("USER")

                .antMatchers(HttpMethod.POST, "/admin/campaigns/search").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/games/search").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/partners/search").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/games").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/partner/campaigns/search").hasAuthority("PARTNER")
                .antMatchers(HttpMethod.POST, "/partner/campaigns").hasAuthority("PARTNER")
                .antMatchers(HttpMethod.POST, "partner/campaigns/**/vouchers/create").hasAuthority("PARTNER")
                .antMatchers(HttpMethod.POST, "partner/**/edit").hasAuthority("PARTNER")

                .antMatchers(HttpMethod.POST, "/partner/vouchers").hasAuthority("PARTNER")
                .antMatchers(HttpMethod.POST, "/partner/vouchers/search").hasAuthority("PARTNER")

                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/register") // ignore CSRF protection for POST requests to /register
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

