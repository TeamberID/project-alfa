package ru.kpfu.itis.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.services.*;
import ru.kpfu.itis.app.utils.AmazonClient;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 14.03.2018
 */
@Controller
@RequestMapping("/user/session")

public class UserSessionController {

    private SessionService sessionService;
    private ExamService examService;
    private ExamPostService examPostService;
    private ManualService manualService;
    private TutorService tutorService;
    private AmazonClient amazonClient;

    public UserSessionController(TutorService tutorService, SessionService sessionService, ExamService examService, ExamPostService examPostService, ManualService manualService, AmazonClient amazonClient) {
        this.sessionService = sessionService;
        this.examService = examService;
        this.examPostService = examPostService;
        this.manualService = manualService;
        this.tutorService = tutorService;
        this.amazonClient = amazonClient;
    }

    @GetMapping("")
    public String getSessionPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        List<Exam> exams = sessionService.getUserExams(authentication);
        model.addAttribute("exams", exams);
        return "session";
    }

    @GetMapping("/exam/{id}")
    public String getExamPage(@ModelAttribute("model") ModelMap model, @PathVariable("id") Long examId) {
        Exam exam = examService.getExamById(examId);
        model.addAttribute("exam", exam);
        model.addAttribute("tutors",tutorService.getTutorsBySubject(exam.getSubject()));
        model.addAttribute("manuals", manualService.getUserManualsByExamIdAndCount(examId, 5));
        model.addAttribute("credentials", amazonClient.getCredentials());
        return "exam-page";
    }

    @GetMapping("/exam-post/{id}")
    public String getExamPostPage(@ModelAttribute("model") ModelMap model, @PathVariable("id") Long examPostId) {
        model.addAttribute("post", examPostService.getExamPost(examPostId));
        return "exam-post-page";
    }
}
