package com.awesome.thesis.controller.admin;

import com.awesome.thesis.controller.dto.ThemaInfoDTO;
import com.awesome.thesis.controller.dto.ThemaLinkDTO;
import com.awesome.thesis.logic.application.service.themen.ThemaEditor;
import com.awesome.thesis.logic.domain.model.themen.Thema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ThemaEditorController {

    @Autowired
    ThemaEditor editor;

    @GetMapping("/editThema/{id}")
    public String editThema(@PathVariable("id")String id, Model model) {
        Thema thema = editor.getThema(id);
        ThemaInfoDTO themaInfoDTO = new ThemaInfoDTO(thema.getTitel(), thema.getBeschreibung());
        ThemaLinkDTO themaLinkDTO = new ThemaLinkDTO(thema.getLinks());
        model.addAttribute("themaLinkDTO", themaLinkDTO);
        model.addAttribute("themaInfoDTO", themaInfoDTO);
        model.addAttribute("thema", thema);
        return "admin/themaEdit";
    }
}
