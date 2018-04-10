package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.InstituteAddingForm;
import ru.kpfu.itis.app.model.Institute;
import ru.kpfu.itis.app.model.University;
import ru.kpfu.itis.app.repositories.InstitutesRepository;
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

    @Override
    public List<Institute> getAll() {
        return institutesRepository.findAll();
    }

    @Override
    public void add(InstituteAddingForm instituteAddingForm) {
        System.out.println(instituteAddingForm);
        institutesRepository.save(Institute.builder()
                .name(instituteAddingForm.getName())
                .university(University.builder()
                        .id(instituteAddingForm.getUniversityId())
                        .build())
                .build());

    }

    @Override
    public void delete(Long id) {
        institutesRepository.delete(id);
    }


}
