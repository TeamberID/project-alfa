package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.UniversityAddingForm;
import ru.kpfu.itis.app.model.Institute;
import ru.kpfu.itis.app.model.University;
import ru.kpfu.itis.app.repositories.InstitutesRepository;
import ru.kpfu.itis.app.repositories.UniversitiesRepository;
import ru.kpfu.itis.app.services.InstituteService;
import ru.kpfu.itis.app.services.UniversityService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class UniversityServiceImpl implements UniversityService {

    private UniversitiesRepository universitiesRepository;

    @Autowired
    private InstitutesRepository institutesRepository;

    public UniversityServiceImpl(UniversitiesRepository universitiesRepository) {
        this.universitiesRepository = universitiesRepository;
    }

    @Override
    public List<University> getAllUniversities() {
        List<University> universities = universitiesRepository.findAll();
        return universities.subList(1, universities.size());
    }

    @Override
    public List<University> getAll() {
        return universitiesRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        University university = universitiesRepository.findOne(id);
            List<Institute> institutes = university.getInstitutes();
            institutesRepository.delete(institutes);
            universitiesRepository.delete(id);
    }

    @Override
    public void add(UniversityAddingForm universityAddingForm) {
        universitiesRepository.save(University.builder()
                .name(universityAddingForm.getName())
                .build());
    }
}
