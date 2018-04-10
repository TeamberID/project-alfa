package ru.kpfu.itis.app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.InstituteAddingForm;
import ru.kpfu.itis.app.forms.UserEditForm;
import ru.kpfu.itis.app.services.InstituteService;
import ru.kpfu.itis.app.services.UniversityService;
import ru.kpfu.itis.app.validators.InstituteAddingFormValidator;
import ru.kpfu.itis.app.validators.UserEditFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 23.03.2018
 */
@Controller
@RequestMapping("/admin/institutes")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private InstituteAddingFormValidator instituteAddingFormValidator;

    @InitBinder("instituteAddingForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(instituteAddingFormValidator);
    }

    @GetMapping("")
    public String getInstitutes(@ModelAttribute("model")ModelMap model){
        model.addAttribute("institutes", instituteService.getAll());
        return "admin/entities/institutes";
    }
    @GetMapping("/add")
    public String addInstitutePage(HttpServletRequest req,
                              @ModelAttribute("model")ModelMap model,
                              BindingResult errors){
        model.addAttribute("universities",universityService.getAll());
        String isSA = req.getParameter("isSuccessfullyAdded");
        if (isSA != null){
            model.addAttribute("isSuccessfullyAdded",Boolean.parseBoolean(isSA));
        }
        return "admin/entities/institute_add";
    }
    @PostMapping("/add")
    public String addInstitute(RedirectAttributes redirectAttributes,
                              @Valid @ModelAttribute("instituteAddingForm") InstituteAddingForm instituteAddingForm,
                              HttpServletRequest req,
                              @ModelAttribute("model")ModelMap model,
                              BindingResult errors){
        boolean flag =false;
        /*try {
            instituteService.add(instituteAddingForm);
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }finally{
            redirectAttributes.addAttribute("isSuccessfullyAdded",flag);
        }*/
        instituteService.add(instituteAddingForm);
        return "redirect:/admin/institutes/add";
    }
    @PostMapping("/{id}/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        instituteService.delete(id);
        return "redirect:/admin/institutes";
    }

}
