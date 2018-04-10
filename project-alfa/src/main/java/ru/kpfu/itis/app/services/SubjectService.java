package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.forms.SubjectAddingForm;
import ru.kpfu.itis.app.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getUserSubjects(Authentication authentication);

    Subject getSubjectById(Long id);

    Subject getSubjectByExamId(Long examId);

    List<Subject> getAll();

    void add(SubjectAddingForm examAddingForm);

    void delete(Long id);
}
