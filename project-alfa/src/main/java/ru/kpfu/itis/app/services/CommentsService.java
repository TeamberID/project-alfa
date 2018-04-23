package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.model.Comment;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
public interface CommentsService {
    void delete(Long id);
    void incReportsNumber(Long id);
    Comment get(Long id);
}
