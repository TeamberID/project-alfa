package ru.kpfu.itis.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.services.ManualService;
import ru.kpfu.itis.app.services.SessionService;
import ru.kpfu.itis.app.services.SubjectService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping(value = "/user/manuals")
public class ManualController {

    private SubjectService subjectService;
    private ManualService manualService;
    private SessionService sessionService;

    public ManualController(SubjectService subjectService, ManualService manualService, SessionService sessionService) {
        this.subjectService = subjectService;
        this.manualService = manualService;
        this.sessionService = sessionService;
    }

    @GetMapping("")
    public String getAllManualPage(@ModelAttribute("model")ModelMap modelMap, Authentication authentication) {
        modelMap.addAttribute("exams", sessionService.getUserExams(authentication));
        return "manuals-page";
    }

    @GetMapping("/{examId}")
    public String getManualPage(@ModelAttribute("model")ModelMap modelMap, @PathVariable("examId") Long examId) {
        modelMap.addAttribute("manuals", manualService.getUserManualsByExamId(examId));
        modelMap.addAttribute("subject", subjectService.getSubjectByExamId(examId));
        return "manual-page";
    }
}
