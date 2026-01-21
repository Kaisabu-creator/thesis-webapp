package com.awesome.thesis.controller.advice;

import com.awesome.thesis.controller.dto.NavbarButtonDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller Advice für alle Controller.
 */
@ControllerAdvice()
public class RollenControllerAdvice {
  /**
   * Methode die navButton vom Typ {@link NavbarButtonDto} zur Personalisierung hinzufügt.
   *
   * @param token {@link Authentication}
   * @return {@link NavbarButtonDto}
   */
  @ModelAttribute("navButton")
  public NavbarButtonDto getNavButton(Authentication token) {
    if (!(token instanceof OAuth2AuthenticationToken auth)) {
      return new NavbarButtonDto("Anmelden", "/login");
    }
    boolean isAdmin = auth.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    if (isAdmin) {
      return new NavbarButtonDto("Admin", "/admin");
    }
    boolean isBetreuende = auth.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals("ROLE_BETREUENDE"));
    String name = auth.getPrincipal().getAttribute("login");
    if (isBetreuende) {
      return new NavbarButtonDto(name, "/betreuende/profilEdit");
    }
    return new NavbarButtonDto("Abmelden", "/logout");
  }
}
