package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Institute;
import ru.kpfu.itis.app.repositories.InstitutesRepository;
import ru.kpfu.itis.app.repositories.UniversitiesRepository;
import ru.kpfu.itis.app.services.InstituteService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class InstituteServiceImpl implements InstituteService {

    private InstitutesRepository institutesRepository;

    @Autowired
    public InstituteServiceImpl(InstitutesRepository institutesRepository) {
        this.institutesRepository = institutesRepository;
    }

    @Override
    public List<Institute> getInstitutesByUniversityId(Long universityId) {
        return institutesRepository.findByUniversityId(universityId);
    }
}
