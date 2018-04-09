package ru.kpfu.itis.app.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.app.dto.TeacherScoreDto;
import ru.kpfu.itis.app.forms.TeacherVoteFrom;
import ru.kpfu.itis.app.services.TeacherService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/teacher")
public class TeacherVoteFromController {

    private TeacherService teacherService;

    public TeacherVoteFromController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/vote")
    public TeacherScoreDto vote(@RequestBody TeacherVoteFrom teacherVoteFrom, Authentication authentication) {
        teacherService.updateTeacherRating(teacherVoteFrom, authentication);
        return teacherService.getTeacherScoreDtoByTeacherId(teacherVoteFrom.getTeacherId());
    }
}
