package ru.kpfu.itis.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.app.forms.RegistrationKeyRequestForm;
import ru.kpfu.itis.app.services.RegistrationKeyRequestService;
import ru.kpfu.itis.app.services.UniversityService;
import ru.kpfu.itis.app.utils.AmazonClient;
import ru.kpfu.itis.app.validators.RegistrationKeyRequestFormValidator;

import javax.validation.Valid;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
public class RegistrationKeyRequestController {

    private RegistrationKeyRequestFormValidator registrationKeyRequestFormValidator;
    private RegistrationKeyRequestService registrationKeyRequestService;
    private UniversityService universityService;
    private AmazonClient amazonClient;

    public RegistrationKeyRequestController(RegistrationKeyRequestFormValidator registrationKeyRequestFormValidator, RegistrationKeyRequestService registrationKeyRequestService, UniversityService universityService, AmazonClient amazonClient) {
        this.registrationKeyRequestFormValidator = registrationKeyRequestFormValidator;
        this.registrationKeyRequestService = registrationKeyRequestService;
        this.universityService = universityService;
        this.amazonClient = amazonClient;
    }

    @InitBinder("keyRequestForm")
    public void initKeyRequestValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(registrationKeyRequestFormValidator);
    }

    @GetMapping("/registration-key-request")
    public String getRegistrationKeyRequestPage(@ModelAttribute("model")ModelMap modelMap) {
        modelMap.addAttribute("credentials", amazonClient.getCredentials());
        modelMap.addAttribute("universities", universityService.getAllUniversities());
        return "registration-key-request";
    }

    @PostMapping("/registration-key-request")
    public void submitKeyRequest(@Valid @ModelAttribute("keyRequestForm")RegistrationKeyRequestForm keyRequestForm) {
        registrationKeyRequestService.saveKeyRequest(keyRequestForm);
    }

    @GetMapping("/registration-key-request-success")
    public String successKeyRequest(){
        return "registration-key-request-success";
    }
}
