package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.TeacherAddingForm;
import ru.kpfu.itis.app.model.Teacher;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 24.03.2018
 */
public interface TeacherService {
    List<Teacher> getAll();

    void add(TeacherAddingForm teacherAddingForm);

    void delete(Long id);
}
