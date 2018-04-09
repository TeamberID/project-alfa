package ru.kpfu.itis.app.services;


import ru.kpfu.itis.app.forms.ExamAddingForm;
import ru.kpfu.itis.app.model.Exam;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
public interface ExamService {
    List<Exam> getAll();

    void add(ExamAddingForm examAddingForm);

    void delete(Long id);
	
	 Exam getExamById(Long id);

    Exam get(Long id);
}
