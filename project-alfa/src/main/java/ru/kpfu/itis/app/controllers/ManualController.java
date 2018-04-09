package ru.kpfu.itis.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.services.SubjectService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping(value = "/user/manuals")
public class ManualController {

    private SubjectService subjectService;

    public ManualController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("")
    public String getAllManualPage(@ModelAttribute("model")ModelMap modelMap, Authentication authentication) {
        modelMap.addAttribute("subjects", subjectService.getUserSubjects(authentication));
        return "manuals-page";
    }

    @GetMapping("/{subjectId}")
    public String getManualPage(@ModelAttribute("model")ModelMap modelMap, @PathVariable("subjectId") Long subjectId) {
        modelMap.addAttribute("subject", subjectService.getSubjectById(subjectId));
        return "manual-page";
    }
}
