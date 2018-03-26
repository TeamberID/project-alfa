package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.model.Exam;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface SessionService {
    List<Exam> getUserExams(Authentication authentication);
}
