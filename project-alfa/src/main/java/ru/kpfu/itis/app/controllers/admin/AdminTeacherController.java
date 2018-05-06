package ru.kpfu.itis.app.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.services.SubjectService;
import ru.kpfu.itis.app.services.TeacherService;
import ru.kpfu.itis.app.utils.AmazonClient;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 24.03.2018
 */
@Controller
@RequestMapping("/admin/teachers")
public class AdminTeacherController {
    private TeacherService teacherService;
    private SubjectService subjectService;
    private AmazonClient amazonClient;

    public AdminTeacherController(TeacherService teacherService, SubjectService subjectService, AmazonClient amazonClient) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.amazonClient = amazonClient;
    }

    @GetMapping("")
    public String getTeachers(@ModelAttribute("model")ModelMap model){
        model.addAttribute("teachers", teacherService.getAll());
        return "admin/entities/teachers";
    }

    @GetMapping("/add")
    public String addTeacherPage(HttpServletRequest req,
                                    @ModelAttribute("model")ModelMap model,
                                    BindingResult errors){
        model.addAttribute("teachers",teacherService.getAll());
        String isSA = req.getParameter("isSuccessfullyAdded");
        if (isSA != null){
            model.addAttribute("isSuccessfullyAdded", Boolean.parseBoolean(isSA));
        }
        model.addAttribute("credentials", amazonClient.getCredentials());
        model.addAttribute("subjects", subjectService.getAll());
        return "admin/entities/teacher_add";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        teacherService.delete(id);
        return "redirect:/admin/teachers";
    }

}
