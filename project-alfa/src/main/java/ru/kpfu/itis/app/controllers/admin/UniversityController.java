package ru.kpfu.itis.app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.UniversityAddingForm;
import ru.kpfu.itis.app.services.UniversityService;
import ru.kpfu.itis.app.validators.UniversityAddingFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 24.03.2018
 */
@Controller
@RequestMapping("/admin/universities")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversityAddingFormValidator universityAddingFormValidator;

    @InitBinder("universityAddingForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(universityAddingFormValidator);
    }

    @GetMapping("")
    public String getUniversities(@ModelAttribute("model")ModelMap model){
        model.addAttribute("universities", universityService.getAll());
        return "admin/entities/universities";
    }
    @GetMapping("/add")
    public String addUniversityPage(HttpServletRequest req,
                                   @ModelAttribute("model")ModelMap model,
                                   BindingResult errors){
        model.addAttribute("universities",universityService.getAll());
        String isSA = req.getParameter("isSuccessfullyAdded");
        if (isSA != null){
            model.addAttribute("isSuccessfullyAdded",Boolean.parseBoolean(isSA));
        }
        return "admin/entities/university_add";
    }
    @PostMapping("/add")
    public String addUniversity(RedirectAttributes redirectAttributes,
                               @Valid @ModelAttribute("universityAddingForm") UniversityAddingForm universityAddingForm,
                               HttpServletRequest req,
                               @ModelAttribute("model")ModelMap model,
                               BindingResult errors){
        universityService.add(universityAddingForm);
        return "redirect:/admin/universities/add";
    }
    @PostMapping("/{id}/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        universityService.delete(id);
        return "redirect:/admin/universities";
    }

}
