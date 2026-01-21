package com.awesome.thesis.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Method Security Configuration.
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {
}
