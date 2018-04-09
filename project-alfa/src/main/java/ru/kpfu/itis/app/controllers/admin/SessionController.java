package ru.kpfu.itis.app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.SessionAddingForm;
import ru.kpfu.itis.app.repositories.SubjectRepository;
import ru.kpfu.itis.app.services.SessionService;
import ru.kpfu.itis.app.services.TeacherService;
import ru.kpfu.itis.app.services.UniversityService;
import ru.kpfu.itis.app.validators.SessionAddingFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
@Controller
@RequestMapping("/admin/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectRepository subjectRepository;


    @Autowired
    private SessionAddingFormValidator sessionAddingFormValidator;

    @InitBinder("sessionAddingForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(sessionAddingFormValidator);
    }

    @GetMapping("")
    public String getSessions(@ModelAttribute("model")ModelMap model){
        model.addAttribute("sessions", sessionService.getAll());
        return "admin/entities/sessions";
    }
    @GetMapping("/add")
    public String addSessionPage(HttpServletRequest req,
                                   @ModelAttribute("model")ModelMap model,
                                   BindingResult errors){
        String isSA = req.getParameter("isSuccessfullyAdded");
        if (isSA != null){
            model.addAttribute("isSuccessfullyAdded",Boolean.parseBoolean(isSA));
        }
        model.addAttribute("universities",universityService.getAll());
        return "admin/entities/session_add";
    }
    @PostMapping("/add")
    public String addSession(RedirectAttributes redirectAttributes,
                               @Valid @ModelAttribute("sessionAddingForm") SessionAddingForm sessionAddingForm,
                               HttpServletRequest req,
                               @ModelAttribute("model")ModelMap model,
                               BindingResult errors){
        System.out.println(sessionAddingForm.toString());
        sessionService.add(sessionAddingForm);
        return "redirect:/admin/sessions/add";
    }
    @PostMapping("/{id}/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        sessionService.delete(id);
        return "redirect:/admin/sessions";
    }
    @GetMapping("/{id}")
    public String getSession(@PathVariable("id")Long id,
                                 @ModelAttribute("model")ModelMap model,
                                 BindingResult errors){
        model.addAttribute("session",sessionService.get(id));
        model.addAttribute("subjects",subjectRepository.findAll());
        model.addAttribute("teachers",teacherService.getAll());
        return "admin/entities/session";
    }
}
