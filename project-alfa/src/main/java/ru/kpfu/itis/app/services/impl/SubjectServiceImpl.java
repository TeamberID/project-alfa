package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.SubjectAddingForm;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.model.Subject;
import ru.kpfu.itis.app.repositories.SubjectRepository;
import ru.kpfu.itis.app.services.ExamService;
import ru.kpfu.itis.app.services.SessionService;
import ru.kpfu.itis.app.services.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SessionService sessionService;
    private SubjectRepository subjectRepository;
    private ExamService examService;

    public SubjectServiceImpl(SessionService sessionService, SubjectRepository subjectRepository, ExamService examService) {
        this.sessionService = sessionService;
        this.subjectRepository = subjectRepository;
        this.examService = examService;
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

    @Override
    public Subject getSubjectByExamId(Long examId) {
        return examService.getExamById(examId).getSubject();
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void add(SubjectAddingForm subjectAddingForm) {
        subjectRepository.save(Subject.builder()
                .name(subjectAddingForm.getName())
                .build());
    }

    @Override
    public void delete(Long id) {
        subjectRepository.delete(id);
    }

}
