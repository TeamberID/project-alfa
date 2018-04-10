package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.model.Subject;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface SubjectService {
    List<Subject> getUserSubjects(Authentication authentication);

    Subject getSubjectById(Long id);

    Subject getSubjectByExamId(Long examId);
}
