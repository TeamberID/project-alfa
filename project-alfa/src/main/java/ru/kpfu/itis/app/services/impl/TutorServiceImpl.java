package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.TutorAddingForm;
import ru.kpfu.itis.app.model.Subject;
import ru.kpfu.itis.app.model.Tutor;
import ru.kpfu.itis.app.repositories.SubjectRepository;
import ru.kpfu.itis.app.repositories.TutorRepository;
import ru.kpfu.itis.app.services.TutorService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Timur Iderisov on 22.04.2018.
 */
@Service
public class TutorServiceImpl implements TutorService {

    private TutorRepository tutorRepository;
    private SubjectRepository subjectRepository;

    public TutorServiceImpl(TutorRepository tutorRepository, SubjectRepository subjectRepository) {
        this.tutorRepository = tutorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void delete(Long id) {

        Tutor t = tutorRepository.findOne(id);

        List<Subject> subjects = subjectRepository.getAllByTutors(t);

        for (Subject s : subjects) {
            if (s.getTutors().contains(t)) {
                s.getTutors().remove(t);
            }
        }

        tutorRepository.delete(id);
    }

    @Override
    public List<Tutor> getAll() {
        return tutorRepository.findAll();
    }


    @Override
    public void add(TutorAddingForm tutorAddingForm) {

        List<Subject> tutorSubjects = tutorAddingForm.getSubjectsId().stream()
                .map(subjectRepository::findOne)
                .collect(Collectors.toList());

        System.out.println(tutorSubjects.get(0).getName());

        Tutor t = Tutor.builder()
                .name(tutorAddingForm.getName())
                .subjects(tutorSubjects)
                .contacts(tutorAddingForm.getContacts())
                .build();

        for (Subject s : tutorSubjects) {
            s.getTutors().add(t);
        }

        tutorRepository.save(t);
    }

    @Override
    public List<Tutor> getTutorsBySubject(Subject subject) {
        List<Tutor> tutors = tutorRepository.findAllBySubjects(subject);
        if (tutors.isEmpty()) {
            return null;
        }
        return tutors;
    }
}
