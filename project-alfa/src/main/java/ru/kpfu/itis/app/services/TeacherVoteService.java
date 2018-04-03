package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TeacherVoteService {
    boolean isUserVoted(Authentication authentication, Long teacherId);
}
