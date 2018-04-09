package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.SubjectAddingForm;
import ru.kpfu.itis.app.model.*;
import ru.kpfu.itis.app.repositories.SubjectRepository;
import ru.kpfu.itis.app.services.SubjectService;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 09.04.2018
 */
@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectsRepository;

    @Override
    public List<Subject> getAll() {
        return subjectsRepository.findAll();
    }

    @Override
    public void add(SubjectAddingForm subjectAddingForm) {
        subjectsRepository.save(Subject.builder()
                .name(subjectAddingForm.getName())
                .build());
    }

    @Override
    public void delete(Long id) {
        subjectsRepository.delete(id);
    }
}
