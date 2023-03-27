package com.softuni.instaSeller.config;

import com.softuni.instaSeller.model.enums.Authority;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.
            authorizeHttpRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .requestMatchers("/", "/auth/login", "/auth/register", "/auth/login-error").anonymous()
            .requestMatchers("/home").authenticated()
            .anyRequest().authenticated()
            .and()
          // configure login with HTML form
            .formLogin()
            .loginPage("/auth/login")
            .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
            .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
            .defaultSuccessUrl("/home")
            .failureForwardUrl("/auth/login-error")
            .and().logout()
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new ApplicationUserDetailsService(userRepository);
  }

}
