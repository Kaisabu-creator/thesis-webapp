package com.awesome.thesis.controller.betreuende;

import com.awesome.thesis.configurations.AppUserService;
import com.awesome.thesis.configurations.MethodSecurityConfig;
import com.awesome.thesis.configurations.SecurityConfig;
import com.awesome.thesis.helper.WithMockOAuth2User;
import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import({SecurityConfig.class, MethodSecurityConfig.class, AppUserService.class})
@WebMvcTest(BetreuendeController.class)
class BetreuendeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProfilEditor editor;

    @Test
    @WithMockOAuth2User(roles = {"BETREUENDE"})
    @DisplayName("Ein Nutzer ohne Betreuende rechte kann kein get aufrufen")
    void get_betreuendeProfil() throws Exception {
        mockMvc.perform(get("/betreuende/profil"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2User()
    @DisplayName("Ein Nutzer ohne Betreuende rechte kann kein get aufrufen")
    void get_betreuendeProfil_withoutRights() throws Exception {
        mockMvc.perform(get("/betreuende/profil"))
                .andExpect(status().isForbidden());
    }
}