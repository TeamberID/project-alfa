package ru.kpfu.itis.app.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.services.ExamService;
import ru.kpfu.itis.app.services.ManualService;
import ru.kpfu.itis.app.utils.AmazonClient;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/admin/manuals")
public class AdminManualController {

    private ManualService manualService;
    private ExamService examService;
    private AmazonClient amazonClient;

    public AdminManualController(ManualService manualService, ExamService examService, AmazonClient amazonClient) {
        this.manualService = manualService;
        this.examService = examService;
        this.amazonClient = amazonClient;
    }

    @GetMapping("")
    public String manualsPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("manuals", manualService.getAllManuals());
        return "admin/entities/manuals";
    }

    @GetMapping("/add")
    public String manualsAddingPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("exams", examService.getAll());
        model.addAttribute("credentials", amazonClient.getCredentials());
        return "admin/entities/manuals-add";
    }

    @GetMapping("/{id}/delete")
    public String deleteManual(@PathVariable("id") Long manualId) {
        manualService.deleteManualById(manualId);
        return "redirect:/admin/manuals";
    }
}
