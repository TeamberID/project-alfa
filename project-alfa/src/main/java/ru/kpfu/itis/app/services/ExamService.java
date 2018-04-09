package ru.kpfu.itis.app.services;

<<<<<<< HEAD
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

    Exam get(Long id);
=======
import ru.kpfu.itis.app.model.Exam;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface ExamService {
    Exam getExamById(Long id);
>>>>>>> d8b2b29360bfeab196a45bc7f089ab37da3aae70
}
