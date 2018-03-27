package ru.kpfu.itis.app.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.dto.ExamPostCommentDto;
import ru.kpfu.itis.app.forms.ExamPostCommentAddingForm;
import ru.kpfu.itis.app.services.ExamPostCommentService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/exam-post-comment")
public class ExamPostCommentRestController {

    private ExamPostCommentService examPostCommentService;

    public ExamPostCommentRestController(ExamPostCommentService examPostCommentService) {
        this.examPostCommentService = examPostCommentService;
    }

    @PostMapping("")
    public List<ExamPostCommentDto> addNewExamPostComment(@ModelAttribute ExamPostCommentAddingForm form, Authentication authentication) {
        examPostCommentService.addExamPostComment(authentication, form);
        return examPostCommentService.getAllDtoByExamPostId(form.getExamPostId());
    }
}
