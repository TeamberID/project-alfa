package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.ExamPost;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 01.03.2018
 */
public interface ExamPostsRepository extends JpaRepository<ExamPost,Long> {
    List<ExamPost> findByExamId(Long examId);
}
