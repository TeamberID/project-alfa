package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Subject;
import ru.kpfu.itis.app.model.Tutor;
import ru.kpfu.itis.app.repositories.TutorRepository;
import ru.kpfu.itis.app.services.TutorService;

import java.util.List;

/**
 * Created by Timur Iderisov on 22.04.2018.
 */
@Service
public class TutorServiceImpl  implements TutorService{

    private TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository){
        this.tutorRepository = tutorRepository;
    }

    @Override
    public void delete(Long id) {
        tutorRepository.delete(id);
    }

    @Override
    public List<Tutor> getAll() {
        return tutorRepository.findAll();
    }

    @Override
    public List<Tutor> getTutorsBySubject(Subject subject) {
        return tutorRepository.findAllBySubjects(subject);
    }
}
