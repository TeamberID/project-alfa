package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.SessionAddingForm;
import ru.kpfu.itis.app.model.Institute;
import ru.kpfu.itis.app.model.Session;
import ru.kpfu.itis.app.repositories.ExamsRepository;
import ru.kpfu.itis.app.repositories.SessionsRepository;
import ru.kpfu.itis.app.services.SessionService;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionsRepository sessionsRepository;

    @Autowired
    private ExamsRepository examsRepository;

    @Override
    public List<Session> getAll() {
        return sessionsRepository.findAll();
    }

    @Override
    public void add(SessionAddingForm sessionAddingForm) {
        sessionsRepository.save(Session.builder()
                .semesterNumber(sessionAddingForm.getSemester_number())
                .institute(Institute.builder()
                        .id(sessionAddingForm.getInstitute_id())
                        .build())
                .build());
    }

    @Override
    public void delete(Long id) {
        Session session = sessionsRepository.findOne(id);
        examsRepository.delete(session.getExams());
        sessionsRepository.delete(id);
    }

    @Override
    public Session get(Long id) {
        return sessionsRepository.findOne(id);
    }
}
