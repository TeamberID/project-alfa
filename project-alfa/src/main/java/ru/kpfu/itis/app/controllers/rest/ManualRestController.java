package ru.kpfu.itis.app.controllers.rest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.forms.ManualAddingForm;
import ru.kpfu.itis.app.services.ManualService;

import javax.validation.Valid;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
public class ManualRestController {

    private ManualService manualService;

    public ManualRestController(ManualService manualService) {
        this.manualService = manualService;
    }

    @PostMapping("/admin/manuals/add")
    public void addNewManual(@Valid @ModelAttribute ManualAddingForm form) {
        manualService.saveManual(form);
    }
}
