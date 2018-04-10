package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.InstituteAddingForm;
import ru.kpfu.itis.app.model.Institute;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface InstituteService {
    List<Institute> getInstitutesByUniversityId(Long universityId);
    List<Institute> getAll();
    void add(InstituteAddingForm instituteAddingForm);

    void delete(Long id);
}
