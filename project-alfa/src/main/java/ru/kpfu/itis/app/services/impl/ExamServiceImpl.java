package ru.kpfu.itis.app.services.impl;


import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.ExamAddingForm;
import ru.kpfu.itis.app.model.*;
import ru.kpfu.itis.app.repositories.ExamsRepository;
import ru.kpfu.itis.app.services.ExamService;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
@Service
public class ExamServiceImpl implements ExamService{

   private ExamsRepository examRepository;

    public ExamServiceImpl(ExamsRepository examRepository) {
        this.examRepository = examRepository;
    }
    @Override
    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    @Override
    public void add(ExamAddingForm examAddingForm) {
        System.out.println(examAddingForm);
        examRepository.save(Exam.builder()
                .semesterNumber(examAddingForm.getSemester_number())
                .institute(Institute.builder()
                        .id(examAddingForm.getInstitute_id())
                        .build())
                .session(Session.builder()
                        .id(examAddingForm.getSession_id())
                        .build())
                .teacher(Teacher.builder()
                        .id(examAddingForm.getTeacher_id())
                        .build())
                .subject(Subject.builder()
                        .id(examAddingForm.getSubject_id())
                        .build())
                .build());
    }
	 @Override
    public Exam getExamById(Long id) {
        return examRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        examRepository.delete(id);
    }

    @Override
    public Exam get(Long id) {
        return examRepository.findOne(id);
    }
}
