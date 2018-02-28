package ru.kpfu.itis.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.services.RegistrationKeyRequestService;


/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private RegistrationKeyRequestService registrationKeyRequestService;

    public AdminController(RegistrationKeyRequestService registrationKeyRequestService) {
        this.registrationKeyRequestService = registrationKeyRequestService;
    }

    @GetMapping("/registration-key-requests")
    public String getRegistrationKeyRequestsPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute(registrationKeyRequestService.getUnderConsiderationRequests());
        return "admin/registration-key-requests";
    }
}
