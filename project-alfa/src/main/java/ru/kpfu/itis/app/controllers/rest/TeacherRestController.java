package ru.kpfu.itis.app.controllers.rest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.forms.TeacherAddingForm;
import ru.kpfu.itis.app.services.TeacherService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
public class TeacherRestController {

    private TeacherService teacherService;

    public TeacherRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/admin/teachers")
    public void addTeacher(@ModelAttribute TeacherAddingForm form) {
        teacherService.add(form);
    }
}
