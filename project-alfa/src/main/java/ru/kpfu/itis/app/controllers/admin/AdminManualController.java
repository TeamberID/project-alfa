package ru.kpfu.itis.app.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.ManualAddingForm;
import ru.kpfu.itis.app.services.ExamService;
import ru.kpfu.itis.app.services.ManualService;
import ru.kpfu.itis.app.validators.ManualAddingFromValidator;

import javax.validation.Valid;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/admin/manuals")
public class AdminManualController {

    private ManualService manualService;
    private ExamService examService;
    private ManualAddingFromValidator manualAddingFromValidator;

    public AdminManualController(ManualService manualService, ExamService examService, ManualAddingFromValidator manualAddingFromValidator) {
        this.manualService = manualService;
        this.examService = examService;
        this.manualAddingFromValidator = manualAddingFromValidator;
    }

    @InitBinder("manualAddingForm")
    public void initManualAddingFromValidator(WebDataBinder binder) {
        binder.addValidators(manualAddingFromValidator);
    }

    @GetMapping("")
    public String manualsPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("manuals", manualService.getAllManuals());
        return "admin/entities/manuals";
    }

    @GetMapping("/add")
    public String manualsAddingPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("exams", examService.getAll());
        return "admin/entities/manuals-add";
    }

    @PostMapping("/add")
    public String saveManual(@Valid @ModelAttribute("manualAddingForm") ManualAddingForm form,
                             BindingResult errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/admin/manuals/add";
        }
        manualService.saveManual(form);
        return "redirect:/admin/manuals/add";
    }

    @GetMapping("/{id}/delete")
    public String deleteManual(@PathVariable("id") Long manualId) {
        manualService.deleteManualById(manualId);
        return "redirect:/admin/manuals";
    }
}
