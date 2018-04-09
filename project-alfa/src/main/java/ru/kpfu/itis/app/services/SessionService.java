package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.SessionAddingForm;
import ru.kpfu.itis.app.model.Session;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
public interface SessionService {
    List<Session> getAll();

    void add(SessionAddingForm sessionAddingForm);

    void delete(Long id);
    Session get(Long id);
}
