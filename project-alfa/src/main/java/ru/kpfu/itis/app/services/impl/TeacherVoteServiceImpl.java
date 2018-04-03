package ru.kpfu.itis.app.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.TeacherVote;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.repositories.TeacherVoteRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.TeacherVoteService;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class TeacherVoteServiceImpl implements TeacherVoteService {

    private AuthenticationService authenticationService;
    private TeacherVoteRepository teacherVoteRepository;

    public TeacherVoteServiceImpl(AuthenticationService authenticationService, TeacherVoteRepository teacherVoteRepository) {
        this.authenticationService = authenticationService;
        this.teacherVoteRepository = teacherVoteRepository;
    }

    @Override
    public boolean isUserVoted(Authentication authentication, Long teacherId) {
        User user = authenticationService.getUserByAuthentication(authentication).getUser();
        Optional<TeacherVote> teacherVoteOptional = teacherVoteRepository.findByTeacherIdAndUserId(teacherId, user.getId());
        return teacherVoteOptional.isPresent();
    }
}
