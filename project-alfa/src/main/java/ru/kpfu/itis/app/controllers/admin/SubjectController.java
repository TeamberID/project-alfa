package ru.kpfu.itis.app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.SubjectAddingForm;
import ru.kpfu.itis.app.repositories.SubjectRepository;
import ru.kpfu.itis.app.services.SubjectService;
import ru.kpfu.itis.app.validators.SubjectAddingFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 09.04.2018
 */
@Controller
@RequestMapping("/admin/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private SubjectAddingFormValidator subjectAddingFormValidator;

    @InitBinder("subjectAddingForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(subjectAddingFormValidator);
    }

    @GetMapping("")
    public String getSubjects(@ModelAttribute("model")ModelMap model){
        model.addAttribute("subjects", subjectService.getAll());
        return "admin/entities/subjects";
    }
    @GetMapping("/add")
    public String addSubjectPage(HttpServletRequest req,
                                   @ModelAttribute("model")ModelMap model,
                                   BindingResult errors){

        return "admin/entities/subject_add";
    }
    @PostMapping("/add")
    public String addSubject(RedirectAttributes redirectAttributes,
                               @Valid @ModelAttribute("subjectAddingForm") SubjectAddingForm subjectAddingForm,
                               HttpServletRequest req,
                               @ModelAttribute("model")ModelMap model,
                               BindingResult errors){
        subjectService.add(subjectAddingForm);
        return "redirect:/admin/subjects/add";
    }
    @PostMapping("/{id}/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        subjectService.delete(id);
        return "redirect:/admin/subjects";
    }

}
