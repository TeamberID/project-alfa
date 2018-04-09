package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.SessionAddingForm;
import ru.kpfu.itis.app.model.Session;
import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.model.Exam;


import java.util.List;

/**
<<<<<<< HEAD
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
public interface SessionService {
    List<Session> getAll();

    void add(SessionAddingForm sessionAddingForm); 
	List<Exam> getUserExams(Authentication authentication);

    void delete(Long id);
    Session get(Long id);

}
