package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.dto.ExamPostDto;
import ru.kpfu.itis.app.forms.ExamPostAddingForm;
import ru.kpfu.itis.app.model.ExamPost;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface ExamPostService {
    void addExamPost(Authentication authentication, ExamPostAddingForm form);
    List<ExamPost> getAllByExamId(Long examId);
    List<ExamPostDto> getAllDtoByExamId(Long examId);

}
