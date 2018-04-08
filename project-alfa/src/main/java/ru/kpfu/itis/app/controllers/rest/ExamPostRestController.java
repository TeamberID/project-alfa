package ru.kpfu.itis.app.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.dto.ExamPostDto;
import ru.kpfu.itis.app.forms.ExamPostAddingForm;
import ru.kpfu.itis.app.services.ExamPostService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/exam-post")
public class ExamPostRestController {

    private ExamPostService examPostService;

    public ExamPostRestController(ExamPostService examPostService) {
        this.examPostService = examPostService;
    }

    @PostMapping("")
    public List<ExamPostDto> addNewExamPost(@ModelAttribute @Valid ExamPostAddingForm form, Authentication authentication) {


        return examPostService.getAllDtoByExamId(form.getExamId());
    }
}
