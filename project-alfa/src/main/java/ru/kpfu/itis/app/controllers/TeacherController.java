package ru.kpfu.itis.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.services.CriteriaService;
import ru.kpfu.itis.app.services.TeacherService;
import ru.kpfu.itis.app.services.TeacherVoteService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping(value = "/user/teachers")
public class TeacherController {

    private TeacherService teacherService;
    private TeacherVoteService teacherVoteService;
    private CriteriaService criteriaService;

    public TeacherController(TeacherService teacherService, TeacherVoteService teacherVoteService, CriteriaService criteriaService) {
        this.teacherService = teacherService;
        this.teacherVoteService = teacherVoteService;
        this.criteriaService = criteriaService;
    }

    @GetMapping("")
    public String getTeachersPage(@ModelAttribute("model")ModelMap model, Authentication authentication) {
        model.addAttribute("teachers", teacherService.getUserTeachersDto(authentication));
        return "teachers-page";
    }

    @GetMapping("/{id}")
    public String getTeacherPage(@ModelAttribute("model") ModelMap model, @PathVariable("id") Long teacherId, Authentication authentication) {
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        model.addAttribute("isVoted", teacherVoteService.isUserVoted(authentication, teacherId));
        model.addAttribute("criteria", criteriaService.getAllCriteria());
        return "teacher-page";
    }
}
