package com.awesome.thesis.controller.betreuende;

import com.awesome.thesis.logic.domain.model.profil.Profil;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_BETREUENDE")
@RequestMapping("/betreuende")
public class BetreuendeController {
    @GetMapping("profil")
    public String getProfil(Model model) {
        model.addAttribute("profil", new Profil(""));
        return "betreuendeProfil";
    }
}
