package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.model.Exam;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface ExamService {
    Exam getExamById(Long id);
}
