package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.model.Session;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.InstitutesRepository;
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

    @Autowired
    private InstitutesRepository instituteRepository;

    public SessionServiceImpl(AuthenticationService authenticationService, SessionsRepository sessionRepository) {
        this.authenticationService = authenticationService;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Exam> getUserExams(Authentication authentication) {
        UserData userData = authenticationService.getUserByAuthentication(authentication);
        User user = userData.getUser();
        byte semesterNumber = convertCourseToSemesterNumber((byte)2);
        Session session = sessionRepository.findOneBySemesterNumberAndInstitute(
                semesterNumber, instituteRepository.findOne(4L)
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
    }
}
