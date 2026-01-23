package com.awesome.thesis.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.awesome.thesis.configurations.AppUserService;
import com.awesome.thesis.configurations.MethodSecurityConfig;
import com.awesome.thesis.configurations.SecurityConfig;
import com.awesome.thesis.helper.WithMockOAuth2User;
import com.awesome.thesis.logic.application.service.fachgebiete.FachgebieteEditor;
import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import com.awesome.thesis.logic.application.service.themen.ThemaEditor;
import com.awesome.thesis.logic.application.service.voraussetzungen.VoraussetzungenEditor;
import com.awesome.thesis.logic.domain.model.profil.Profil;
import com.awesome.thesis.logic.domain.model.themen.Thema;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@Import({SecurityConfig.class, MethodSecurityConfig.class, AppUserService.class})
@WebMvcTest(MatchingController.class)
class MatchingControllerTest {
  @Autowired
  MockMvc mockMvc;
  
  @MockitoBean
  ProfilEditor profilEditor;
  
  @MockitoBean
  FachgebieteEditor fachgebieteEditor;
  
  @MockitoBean
  VoraussetzungenEditor vorEditor;
  
  @MockitoBean
  ThemaEditor themaEditor;
  
  @Test
  @WithMockOAuth2User()
  @DisplayName("get auf /matching ohne Parametern ist ok")
  void test_getMatching() throws Exception {
    //Act + Assert
    mockMvc.perform(get("/matching"))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User()
  @DisplayName("get auf /matching mit Parametern ist ok")
  void test_getMatchingParam() throws Exception {
    //Act + Assert
    mockMvc.perform(get("/matching")
            .param("interessen", "eingabe1")
            .param("voraussetzungen", "eingabe2"))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User()
  @DisplayName("get auf /matching gibt richtige View zurück")
  void test_getMatchingView() throws Exception {
    //Act + Assert
    mockMvc.perform(get("/matching"))
        .andExpect(view().name("matching"));
  }
  
  @Test
  @WithMockOAuth2User()
  @DisplayName("get auf /matching has right model")
  void test_getMatchingModel() throws Exception {
    //Arrange
    Set<String> interessen = Set.of("eingabe1");
    Set<String> voraussetzungen = Set.of("eingabe2");
    List<Profil> p = List.of(new Profil(1, "test"));
    when(profilEditor.getMatching(interessen)).thenReturn(p);
    List<Thema> t = List.of(new Thema("test", 1));
    when(themaEditor.sortRang(voraussetzungen, interessen)).thenReturn(t);
    Set<String> f = Set.of("fachgebiet");
    when(fachgebieteEditor.getAll()).thenReturn(f);
    Set<String> v = Set.of("voraussetzung");
    when(vorEditor.getAllString()).thenReturn(v);
    
    //Act + Assert
    mockMvc.perform(get("/matching")
            .param("interessen", "eingabe1")
            .param("voraussetzungen", "eingabe2"))
        .andExpect(model().attribute("fachgebiete", f))
        .andExpect(model().attribute("voraussetzungen", v))
        .andExpect(model().attribute("profile", p))
        .andExpect(model().attribute("themenListe", t))
        .andExpect(model().attribute("interessen", Set.of("eingabe1")))
        .andExpect(model().attribute("eingabeVoraussetzungen", Set.of("eingabe2")));
  }
}