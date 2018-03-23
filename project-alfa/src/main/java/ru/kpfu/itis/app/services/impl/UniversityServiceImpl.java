package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.University;
import ru.kpfu.itis.app.repositories.UniversitiesRepository;
import ru.kpfu.itis.app.services.UniversityService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class UniversityServiceImpl implements UniversityService {

    private UniversitiesRepository universitiesRepository;

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
}
