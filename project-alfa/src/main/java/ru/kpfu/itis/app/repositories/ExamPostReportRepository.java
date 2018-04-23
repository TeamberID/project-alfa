package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.ExamPostReport;

import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
public interface ExamPostReportRepository extends JpaRepository<ExamPostReport,Long>{
    Optional<ExamPostReport> findByPostIdAndUserId(Long postId, Long userId);
}
