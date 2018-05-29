package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

public interface ReportService {
    int NUMBER_OF_REPORTS_TO_DELETE = 10;

    String reportCommentResponse(Long id, Authentication authentication);

    String reportPostResponse(Long id, Authentication authentication);
}
