package com.awesome.thesis.controller;

import com.awesome.thesis.logic.application.service.fachgebiete.FachgebieteEditor;
import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller für Betreuendenansicht.
 */
@Controller
public class ProfilController {
  @Autowired
  ProfilEditor profilEditor;
  
  @Autowired
  FachgebieteEditor fachgebieteEditor;
  
  /**
   * Methode zum Get-Mapping für die Betreuenden Auflistung- und Filter-Funktion.
   *
   * @param interessen Set von String interessen zum Filtern
   * @param model      {@link Model}
   * @return gibt profile.html zurück
   */
  @GetMapping("/betreuende")
  public String getProfile(@RequestParam(required = false) Set<String> interessen, Model model) {
    model.addAttribute("interessen", interessen);
    model.addAttribute("fachgebiete", fachgebieteEditor.getAll());
    model.addAttribute("profile", profilEditor.getFitting(interessen));
    return "profiles/profile";
  }
  
  @GetMapping("/betreuende/{id}")
  public String getProfil(@PathVariable int id, Model model) {
    model.addAttribute("profil", profilEditor.get(id));
    return "profiles/profil";
  }
  
  /**
   * ExceptionHandling für nicht existierende Profil-Id.
   *
   * @param e {@link IllegalArgumentException}
   * @return {@link ModelAndView} fügt eine
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ModelAndView getProfil(IllegalArgumentException e) {
    ModelAndView mav = new ModelAndView("profiles/profilError");
    mav.addObject("errorMessage", e.getMessage());
    return mav;
  }
}
