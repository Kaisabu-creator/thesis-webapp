package com.awesome.thesis.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration.
 */
@Configuration
public class SecurityConfig {
  /**
   * Bean für Security Configuration.
   *
   * @param chainBuilder   {@link HttpSecurity}
   * @param appUserService {@link AppUserService}
   * @return {@link SecurityFilterChain}
   * @throws Exception von chainBuilder
   */
  @Bean
  public SecurityFilterChain configure(HttpSecurity chainBuilder, AppUserService appUserService)
      throws Exception {
    chainBuilder.authorizeHttpRequests(
            configurer -> configurer
                .requestMatchers("/", "/bootstrap.min.css", "/bootstrap.min.js")
                .permitAll()
                .anyRequest()
                .authenticated()
        )
        .oauth2Login(Customizer.withDefaults());
    return chainBuilder.build();
  }
}
