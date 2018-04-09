package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.SubjectAddingForm;
import ru.kpfu.itis.app.model.Subject;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 09.04.2018
 */
public interface SubjectService {
    List<Subject> getAll();

    void add(SubjectAddingForm examAddingForm);

    void delete(Long id);
}
