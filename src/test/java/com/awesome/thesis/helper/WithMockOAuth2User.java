package com.awesome.thesis.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * Class from Propra-Team.
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithOAuth2UserSecurityContextFactory.class)
public @interface WithMockOAuth2User {
  /**
   * Method from Propra-Team.
   */
  int id() default 666666;
  
  /**
   * Method from Propra-Team.
   */
  String login() default "username";
  
  /**
   * Method from Propra-Team.
   */
  String[] roles() default {"USER"};
  
  /**
   * Method from Propra-Team.
   */
  String[] authorities() default {};
  
  /**
   * Method from Propra-Team.
   */
  String clientRegistrationId() default "github";
}
