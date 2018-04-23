package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.controllers.rest.ReportController;
import ru.kpfu.itis.app.model.Comment;
import ru.kpfu.itis.app.model.status.CommentStatus;
import ru.kpfu.itis.app.repositories.CommentsRepository;
import ru.kpfu.itis.app.services.CommentsService;

import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 22.04.2018
 */
@Service
public class CommentsServiceImpl implements CommentsService{

    private CommentsRepository commentsRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentsRepository.findOne(id);
        comment.setStatus(CommentStatus.DELETED);
        commentsRepository.save(comment);
    }

    @Override
    public void incReportsNumber(Long id) {
        Comment comment = commentsRepository.findOne(id);
        comment.setReports(comment.getReports()+1);
        if (comment.getReports() >= ReportController.NUMBER_OF_REPORTS_TO_DELETE){
            delete(comment.getId());
        }
        commentsRepository.save(comment);
    }

    @Override
    public Comment get(Long id) {
        return commentsRepository.findOne(id);
    }
}
