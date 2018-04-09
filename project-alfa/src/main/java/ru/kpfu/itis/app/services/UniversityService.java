package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.UniversityAddingForm;
import ru.kpfu.itis.app.model.University;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface UniversityService {
    List<University> getAllUniversities();
    List<University> getAll();

    void delete(Long id);

    void add(UniversityAddingForm universityAddingForm);
}
