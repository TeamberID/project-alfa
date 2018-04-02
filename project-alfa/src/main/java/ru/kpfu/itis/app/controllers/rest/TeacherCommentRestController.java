package ru.kpfu.itis.app.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.dto.TeacherCommentDto;
import ru.kpfu.itis.app.forms.TeacherCommentAddForm;
import ru.kpfu.itis.app.services.TeacherCommentService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/teacher-comment")
public class TeacherCommentRestController {

    private TeacherCommentService teacherCommentService;

    public TeacherCommentRestController(TeacherCommentService teacherCommentService) {
        this.teacherCommentService = teacherCommentService;
    }

    @PostMapping("")
    public List<TeacherCommentDto> addTeacherComment(@ModelAttribute TeacherCommentAddForm teacherCommentAddForm, Authentication authentication) {
        teacherCommentService.addTeacherComment(teacherCommentAddForm, authentication);
        return teacherCommentService.getAllTeacherCommentDtoByTeacherId(teacherCommentAddForm.getTeacherId());
    }
}
