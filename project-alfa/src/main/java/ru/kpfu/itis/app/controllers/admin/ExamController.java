package ru.kpfu.itis.app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.ExamAddingForm;
import ru.kpfu.itis.app.services.ExamService;
import ru.kpfu.itis.app.validators.ExamAddingFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
@Controller
@RequestMapping("/admin/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @Autowired
    private ExamAddingFormValidator examAddingFormValidator;

    @InitBinder("examAddingForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(examAddingFormValidator);
    }

    @PostMapping("/add")
    public String addExam(RedirectAttributes redirectAttributes,
                             @Valid @ModelAttribute("examAddingForm") ExamAddingForm examAddingForm,
                             HttpServletRequest req,
                             @ModelAttribute("model")ModelMap model,
                             BindingResult errors){
        examService.add(examAddingForm);
        return "redirect:/admin/sessions/"+examAddingForm.getSession_id();
    }
    @PostMapping("/{id}/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable("id")Long id){
        Long sessionId = examService.get(id).getSession().getId();
        examService.delete(id);
        return "redirect:/admin/sessions/"+sessionId;
    }
}
