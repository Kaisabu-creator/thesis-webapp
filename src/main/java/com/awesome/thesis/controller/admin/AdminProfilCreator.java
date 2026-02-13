package com.awesome.thesis.controller.admin;

import com.awesome.thesis.controller.dto.ProfilCreateDto;
import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import com.awesome.thesis.logic.application.service.themen.ThemaEditor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller für /admin Seite.
 */
@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminProfilCreator {
  @Autowired
  ProfilEditor editor;
  
  @Autowired
  ThemaEditor themaEditor;
  
  /**
   * Methode für Get-Mapping auf /admin.
   *
   * @param model {@link Model}
   * @return gibt profileAdmin.html zurück
   */
  @GetMapping()
  public String admin(Model model) {
    model.addAttribute("profil", new ProfilCreateDto(null, ""));
    model.addAttribute("profile", editor.getAll());
    return "admin/profileAdmin";
  }
  
  /**
   * Methode Post-Mapping um ein neues Profil hinzuzufügen.
   *
   * @param profil        {@link ProfilCreateDto} zur Validierung
   * @param bindingResult {@link BindingResult}
   * @param model         {@link Model}
   * @return redirect auf /admin und bei Fehler gibt profileAdmin.html
   */
  @PostMapping("createBetreuende")
  public String createProfile(@Valid @ModelAttribute("profil") ProfilCreateDto profil,
                              BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("profile", editor.getAll());
      return "admin/profileAdmin";
    }
    editor.create(profil.id(), profil.name());
    return "redirect:/admin";
  }
  
  /**
   * Methode löscht ein Profil und die zugehörigen Themen.
   *
   * @param id Profil Id
   * @return Redirect auf /admin
   */
  @PostMapping("/betreuendeDelete")
  public String deleteBetreuende(int id) {
    themaEditor.deleteThemaFromProfil(id);
    editor.delete(id);
    return "redirect:/admin";
  }
}
