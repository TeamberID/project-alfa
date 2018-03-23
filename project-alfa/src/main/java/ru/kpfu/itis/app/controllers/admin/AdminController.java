package ru.kpfu.itis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.UniversitiesRepository;
import ru.kpfu.itis.app.services.InstituteService;
import ru.kpfu.itis.app.services.RegistrationKeyRequestService;
import ru.kpfu.itis.app.services.UniversityService;
import ru.kpfu.itis.app.services.UserDatasService;


/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private RegistrationKeyRequestService registrationKeyRequestService;

    @Autowired
    private UserDatasService userDatasService;

    @Autowired
    private InstituteService instituteService;

    @Autowired
    private UniversityService universityService;

    public AdminController(RegistrationKeyRequestService registrationKeyRequestService) {
        this.registrationKeyRequestService = registrationKeyRequestService;
    }

    @GetMapping("/registration-key-requests")
    public String getRegistrationKeyRequestsPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("requests", registrationKeyRequestService.getUnderConsiderationRequests());
        return "admin/registration-key-requests";
    }
    @GetMapping("/users")
    public String getUsers(@ModelAttribute("model")ModelMap model){
        model.addAttribute("users",userDatasService.getAll());
        return "admin/entities/users";
    }
    @GetMapping("/institutes")
    public String getIntitutes(@ModelAttribute("model")ModelMap model){
        model.addAttribute("institutes", instituteService.getAll());
        return "admin/entities/institutes";
    }
    @GetMapping("/universities")
    public String getUniversities(@ModelAttribute("model")ModelMap model){
        model.addAttribute("universities", universityService.getAll());
        return "admin/entities/universities";
    }
}
