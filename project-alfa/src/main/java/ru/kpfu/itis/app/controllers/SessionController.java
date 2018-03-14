package ru.kpfu.itis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.model.Session;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.SessionsRepository;
import ru.kpfu.itis.app.repositories.UserDataRepository;
import ru.kpfu.itis.app.services.AuthenticationService;

import java.time.LocalDateTime;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 14.03.2018
 */
@Controller
@RequestMapping("/user/session")
public class SessionController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private SessionsRepository sessionsRepository;

    @GetMapping("")
    public String getRegistrationKeyRequestsPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        UserData userData = authenticationService.getUserByAuthentication(authentication);
        User user = userData.getUser();
        byte semesterNumber = convertCourseIntoSemesterNumber(user.getCourse());
        Session usersSession = sessionsRepository.findOneBySemesterNumberAndInstitute(semesterNumber, user.getInstitute());
        if (usersSession != null){
            model.addAttribute("exams", usersSession.getExams());
        }else{
            model.addAttribute("exams", null);
        }
        return "session";
    }

    private byte convertCourseIntoSemesterNumber(byte course) {
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
