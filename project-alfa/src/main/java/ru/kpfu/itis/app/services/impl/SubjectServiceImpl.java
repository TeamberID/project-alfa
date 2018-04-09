package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.model.Subject;
import ru.kpfu.itis.app.repositories.SubjectRepository;
import ru.kpfu.itis.app.services.SessionService;
import ru.kpfu.itis.app.services.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class SubjectServiceImpl implements SubjectService {

    private SessionService sessionService;
    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SessionService sessionService, SubjectRepository subjectRepository) {
        this.sessionService = sessionService;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getUserSubjects(Authentication authentication) {
        return sessionService.getUserExams(authentication).stream()
                .map(Exam::getSubject).collect(Collectors.toList());
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findOne(id);
    }
}
