package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.repositories.ExamsRepository;
import ru.kpfu.itis.app.services.ExamService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class ExamServiceImpl implements ExamService {

    private ExamsRepository examRepository;

    public ExamServiceImpl(ExamsRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findOne(id);
    }
}
