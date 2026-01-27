package com.awesome.thesis.controller.betreuende;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.awesome.thesis.configurations.AppUserService;
import com.awesome.thesis.configurations.MethodSecurityConfig;
import com.awesome.thesis.configurations.SecurityConfig;
import com.awesome.thesis.helper.WithMockOAuth2User;
import com.awesome.thesis.logic.application.exceptions.ProfilLockingException;
import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import com.awesome.thesis.logic.domain.model.profil.Profil;
import com.awesome.thesis.logic.domain.model.profil.ProfilKontakt;
import com.awesome.thesis.logic.domain.model.profil.ProfilKontaktart;
import com.awesome.thesis.logic.domain.model.profil.ProfilLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@Import({SecurityConfig.class, MethodSecurityConfig.class, AppUserService.class})
@WebMvcTest(BetreuendeProfilEditController.class)
class BetreuendeProfilEditControllerTest {
  @Autowired
  MockMvc mockMvc;
  
  @MockitoBean
  ProfilEditor editor;
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("get auf /betreuende/profilEdit funktioniert")
  void get_profilEdit() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(get("/betreuende/profilEdit"))
        .andExpect(model().attribute("profil", profil))
        .andExpect(view().name("betreuende/profilEdit"))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User()
  @DisplayName("get auf /betreuende/profilEdit funktioniert nicht ohne Rechte")
  void get_profilEdit_withoutRights() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(get("/betreuende/profilEdit"))
        .andExpect(status().isForbidden());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Name ändern funktioniert")
  void post_Name() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit")
            .param("name", "test")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Name ändern funktioniert")
  void post_Name_BackEnd() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit")
        .param("name", "test")
        .with(csrf()));
    verify(editor).editName(1, "test");
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Name ändern kann mit Fehlern umgehen")
  void post_Name_Binding() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit")
            .param("name", "")
            .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("betreuende/profilEdit"));
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post ProfilKontakt löschen")
  void post_deleteKontakt() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/deleteKontakt")
            .param("label", "test")
            .param("wert", "test@icloud.com")
            .param("kontaktart", ProfilKontaktart.EMAIL.toString())
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post ProfilKontakt löschen")
  void post_deleteKontakt_BackEnd() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/deleteKontakt")
        .param("label", "test")
        .param("wert", "test@icloud.com")
        .param("kontaktart", ProfilKontaktart.EMAIL.toString())
        .with(csrf()));
    verify(editor).removeKontakt(1,
        new ProfilKontakt("test", "test@icloud.com", ProfilKontaktart.EMAIL));
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Email hinzufügen funktioniert")
  void post_addEmail() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
            .param("type", "email")
            .param("label", "test")
            .param("wert", "test@icloud.com")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Email hinzufügen funktioniert")
  void post_addEmail_BackEnd() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
        .param("type", "email")
        .param("label", "test")
        .param("wert", "test@icloud.com")
        .with(csrf()));
    verify(editor).addEmail(1, "test", "test@icloud.com");
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Email hinzufügen funktioniert nicht bei fehlender Email")
  void post_addEmail_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
            .param("type", "email")
            .param("label", "test")
            .param("wert", "")
            .with(csrf()))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Email hinzufügen funktioniert nicht bei fehlender Email")
  void post_addEmail_BackEnd_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
        .param("type", "email")
        .param("label", "test")
        .param("wert", "")
        .with(csrf()));
    verify(editor, never()).addEmail(anyInt(), any(), any());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Email hinzufügen funktioniert nicht bei falscher Email")
  void post_addEmail_keineEmail() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
            .param("type", "email")
            .param("label", "test")
            .param("wert", "test")
            .with(csrf()))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Email hinzufügen funktioniert nicht bei falscher Email")
  void post_addEmail_BackEnd_keineEmail() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
        .param("type", "email")
        .param("label", "test")
        .param("wert", "test")
        .with(csrf()));
    verify(editor, never()).addEmail(anyInt(), any(), any());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Tel hinzufügen funktioniert")
  void post_addTel() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
            .param("type", "tel")
            .param("label", "test")
            .param("wert", "+4921159817524")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Tel hinzufügen funktioniert")
  void post_addTel_BackEnd() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
        .param("type", "tel")
        .param("label", "test")
        .param("wert", "+4921159817524")
        .with(csrf()));
    verify(editor).addTel(1, "test", "+4921159817524");
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Tel hinzufügen funktioniert nicht bei fehlender Nummer")
  void post_addTel_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
            .param("type", "tel")
            .param("label", "test")
            .param("wert", "")
            .with(csrf()))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Tel hinzufügen funktioniert nicht bei fehlender Nummer")
  void post_addTel_BackEnd_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
        .param("type", "tel")
        .param("label", "test")
        .param("wert", "")
        .with(csrf()));
    verify(editor, never()).addTel(anyInt(), any(), any());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Tel hinzufügen funktioniert nicht bei falscher Nummer")
  void post_addTel_keineTel() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
            .param("type", "tel")
            .param("label", "test")
            .param("wert", "test")
            .with(csrf()))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Tel hinzufügen funktioniert nicht bei falscher Nummer")
  void post_addTel_BackEnd_keineTel() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addKontakt")
        .param("type", "tel")
        .param("label", "test")
        .param("wert", "test")
        .with(csrf()));
    verify(editor, never()).addTel(anyInt(), any(), any());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post add Fachgebiet funktioniert")
  void post_addFachgebiet() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addFachgebiet")
            .param("fachgebiet", "test")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post add Fachgebiet funktioniert")
  void post_addFachgebiet_backEnd() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addFachgebiet")
        .param("fachgebiet", "test")
        .with(csrf()));
    verify(editor).addFachgebiet(1, "test");
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Fachgebiet hinzufügen funktioniert nicht bei leerem String")
  void post_addFachgebiet_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addFachgebiet")
            .param("fachgebiet", "")
            .with(csrf()))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Fachgebiet hinzufügen funktioniert nicht bei leerem String BackEnd")
  void post_addFachgebiet_BackEnd_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addFachgebiet")
        .param("fachgebiet", "")
        .with(csrf()));
    verify(editor, never()).addFachgebiet(anyInt(), any());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Fachgebiet löschen")
  void post_deleteFachgebiet() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/removeFachgebiet")
            .param("fachgebiet", "test")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Fachgebiet löschen")
  void post_deleteFachgebiet_BackEnd() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/removeFachgebiet")
        .param("fachgebiet", "test")
        .with(csrf()));
    verify(editor).removeFachgebiet(1, "test");
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post add Link funktioniert")
  void post_addLink() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addLink")
            .param("url", "https://google.com")
            .param("urlBeschreibung", "test")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post add Link funktioniert")
  void post_addLink_backEnd() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addLink")
        .param("url", "https://google.com")
        .param("urlBeschreibung", "test")
        .with(csrf()));
    verify(editor).addLink(1, "https://google.com", "test");
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Link hinzufügen funktioniert nicht bei leerer Url")
  void post_addLink_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addLink")
            .param("url", "")
            .param("urlBeschreibung", "test")
            .with(csrf()))
        .andExpect(status().isOk());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Link hinzufügen funktioniert nicht bei leerer Url BackEnd")
  void post_addLink_BackEnd_keinWert() throws Exception {
    Profil profil = new Profil(1, "test");
    when(editor.get(anyInt())).thenReturn(profil);
    mockMvc.perform(post("/betreuende/profilEdit/addLink")
        .param("url", "")
        .param("urlBeschreibung", "test")
        .with(csrf()));
    verify(editor, never()).addLink(anyInt(), any(), any());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Link löschen")
  void post_deleteLink() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/deleteLink")
            .param("url", "https://google.com")
            .param("text", "test")
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("post Link löschen")
  void post_deleteLink_BackEnd() throws Exception {
    mockMvc.perform(post("/betreuende/profilEdit/deleteLink")
        .param("url", "https://google.com")
        .param("text", "test")
        .with(csrf()));
    verify(editor).removeLink(1, new ProfilLink("https://google.com", "test"));
  }
  
  @Test
  @WithMockOAuth2User(roles = {"BETREUENDE"}, id = 1)
  @DisplayName("Error Handler works")
  void errorHandler() throws Exception {
    when(editor.get(anyInt())).thenThrow(new ProfilLockingException("test"));
    mockMvc.perform(get("/betreuende/profilEdit"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("errorMessage", "test"))
        .andExpect(view().name("betreuende/locking"));
  }
}