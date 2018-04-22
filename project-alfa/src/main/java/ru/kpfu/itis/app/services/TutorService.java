package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.model.Subject;
import ru.kpfu.itis.app.model.Tutor;

import java.util.List;

/**
 * Created by Timur Iderisov on 22.04.2018.
 */
public interface TutorService {

    void delete(Long id);

    List<Tutor> getAll();

    List<Tutor> getTutorsBySubject(Subject subject);

}
