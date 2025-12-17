package com.awesome.thesis.controller.betreuende;

import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import com.awesome.thesis.logic.domain.model.profil.Profil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_BETREUENDE")
@RequestMapping("/betreuende")
public class BetreuendeController {
    @Autowired
    ProfilEditor editor;

    @GetMapping("profil")
    public String getProfil(Model model, OAuth2AuthenticationToken auth) {
        Integer id = auth.getPrincipal().getAttribute("id");
        model.addAttribute("profil", editor.get(id));
        return "betreuendeProfil";
    }
}
