package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.dto.TeacherCommentDto;
import ru.kpfu.itis.app.forms.TeacherCommentAddForm;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TeacherCommentService {
    void addTeacherComment(TeacherCommentAddForm teacherCommentAddForm, Authentication authentication);

    List<TeacherCommentDto> getAllTeacherCommentDtoByTeacherId(Long teacherId);
}
