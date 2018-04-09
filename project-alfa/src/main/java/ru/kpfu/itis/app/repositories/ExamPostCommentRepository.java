package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.ExamPostComment;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface ExamPostCommentRepository extends JpaRepository<ExamPostComment, Long> {
    List<ExamPostComment> findAllByExamPostId(Long examPostId);
}
