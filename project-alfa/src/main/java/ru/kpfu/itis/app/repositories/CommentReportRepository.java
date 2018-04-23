package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.CommentReport;

import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
public interface CommentReportRepository extends JpaRepository<CommentReport,Long> {
    Optional<CommentReport> findByCommentIdAndUserId(Long commentId, Long userId);
}
