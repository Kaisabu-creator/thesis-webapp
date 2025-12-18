package com.awesome.thesis.controller.advice;

import com.awesome.thesis.controller.StartController;
import com.awesome.thesis.controller.dto.NavbarButtonDTO;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = {StartController.class})
public class RollenControllerAdvice {
    @ModelAttribute("navButton")
    public NavbarButtonDTO OAuth2AuthenticationToken(OAuth2AuthenticationToken token) {
        if (token == null) {
            return new NavbarButtonDTO("Anmelden", "/login");
        }
        boolean isAdmin = token.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return new NavbarButtonDTO("Admin", "/admin");
        }
        boolean isBetreuende = token.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_BETREUENDE"));
        String name = token.getPrincipal().getAttribute("login");
        if (isBetreuende) {
            return new NavbarButtonDTO(name, "/betreuende");
        }
        return new NavbarButtonDTO(name, "#");
    }
}
