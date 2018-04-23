package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
public interface ExamPostReportService {
    boolean hasUserReported(Authentication authentication, Long postId);

    void registerReport(Authentication authentication, Long postId);
}
