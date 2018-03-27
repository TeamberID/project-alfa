package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.dto.ExamPostCommentDto;
import ru.kpfu.itis.app.forms.ExamPostCommentAddingForm;
import ru.kpfu.itis.app.model.ExamPostComment;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface ExamPostCommentService {
    void addExamPostComment(Authentication authentication, ExamPostCommentAddingForm form);

    List<ExamPostCommentDto> getAllDtoByExamPostId(Long examPostId);
    List<ExamPostComment> getAllByExamPostId(Long examPostId);
}
