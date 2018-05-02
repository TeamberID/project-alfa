package ru.kpfu.itis.app.controllers.admin;

/**
 * Created by Timur Iderisov on 02.05.2018.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.TutorAddingForm;
import ru.kpfu.itis.app.services.SubjectService;
import ru.kpfu.itis.app.services.TutorService;
import ru.kpfu.itis.app.validators.TutorAddingFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/tutors")
public class TutorController {

    private TutorService tutorService;
    private TutorAddingFormValidator tutorAddingFormValidator;
    private SubjectService subjectService;

    public TutorController(TutorService tutorService, TutorAddingFormValidator tutorAddingFormValidator, SubjectService subjectService) {
        this.tutorService = tutorService;
        this.tutorAddingFormValidator = tutorAddingFormValidator;
        this.subjectService = subjectService;
    }

    @InitBinder("tutorAddingForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(tutorAddingFormValidator);
    }


    @GetMapping("")
    public String getTutors(@ModelAttribute("model")ModelMap model){
        model.addAttribute("tutors", tutorService.getAll());
        return "admin/entities/tutors";
    }

    @GetMapping("/add")
    public String addTutorPage(HttpServletRequest req,
                                 @ModelAttribute("model")ModelMap model,
                                 BindingResult errors){
        model.addAttribute("subjects", subjectService.getAll());
        return "admin/entities/tutor_add";
    }

    @PostMapping("/add")
    public String addTeacher(RedirectAttributes redirectAttributes,
                             @Valid @ModelAttribute("tutorAddingForm") TutorAddingForm tutorAddingForm,
                             HttpServletRequest req,
                             @ModelAttribute("model")ModelMap model,
                             BindingResult errors){
        tutorService.add(tutorAddingForm);
        return "redirect:/admin/tutors/add";
    }


    @PostMapping("/{id}/delete")
    public String deleteTutor(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        tutorService.delete(id);
        return "redirect:/admin/tutors";
    }

}
