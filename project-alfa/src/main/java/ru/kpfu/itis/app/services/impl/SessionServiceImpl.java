package ru.kpfu.itis.app.services.impl;

<<<<<<< HEAD
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
=======
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.model.Session;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.SessionsRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.SessionService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class SessionServiceImpl implements SessionService {
    private AuthenticationService authenticationService;
    private SessionsRepository sessionRepository;

    public SessionServiceImpl(AuthenticationService authenticationService, SessionsRepository sessionRepository) {
        this.authenticationService = authenticationService;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Exam> getUserExams(Authentication authentication) {
        UserData userData = authenticationService.getUserByAuthentication(authentication);
        User user = userData.getUser();
        byte semesterNumber = convertCourseToSemesterNumber(user.getCourse());
        Session session = sessionRepository.findOneBySemesterNumberAndInstitute(
                semesterNumber, user.getInstitute()
        );
        return session != null ? session.getExams() : null;
    }

    private byte convertCourseToSemesterNumber(byte course) {
        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.getMonth().getValue() < 2 && currentDate.getMonth().getValue() > 8) {
            // 1st half-year
            return (byte) ((course - 1) * 2 + 1);
        } else {
            //2nd half-year
            return (byte) ((course - 1) * 2 + 2);
        }
>>>>>>> d8b2b29360bfeab196a45bc7f089ab37da3aae70
    }
}
