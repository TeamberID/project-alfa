package ru.kpfu.itis.app.services.impl;


import ru.kpfu.itis.app.forms.TeacherAddingForm;
import ru.kpfu.itis.app.model.Teacher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.dto.TeacherDto;
import ru.kpfu.itis.app.dto.TeacherScoreDto;
import ru.kpfu.itis.app.forms.TeacherVoteFrom;
import ru.kpfu.itis.app.forms.UserCriteriaVote;
import ru.kpfu.itis.app.model.*;
import ru.kpfu.itis.app.repositories.TeacherVoteRepository;
import ru.kpfu.itis.app.repositories.TeachersRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.SessionService;
import ru.kpfu.itis.app.services.TeacherService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    private SessionService sessionService;
    private TeachersRepository teacherRepository;
    private AuthenticationService authenticationService;
    private TeacherVoteRepository teacherVoteRepository;

    public TeacherServiceImpl(SessionService sessionService, TeachersRepository teacherRepository,
                              AuthenticationService authenticationService, TeacherVoteRepository teacherVoteRepository) {
        this.sessionService = sessionService;
        this.teacherRepository = teacherRepository;
        this.authenticationService = authenticationService;
        this.teacherVoteRepository = teacherVoteRepository;
    }
	@Override
    public void delete(Long id) {
        teacherRepository.delete(id);
	}
    @Override
    public List<TeacherDto> getUserTeachersDto(Authentication authentication) {
        return sessionService.getUserExams(authentication).stream()
                .map(exam -> TeacherDto.createOnTeacherAndSubject(exam.getTeacher(), exam.getSubject()))
                .collect(Collectors.toList());
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findOne(teacherId);
    }
	
	@Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }
	
	@Override
    public void add(TeacherAddingForm teacherAddingForm) {
        teacherRepository.save(Teacher.builder()
                .name(teacherAddingForm.getName())
                .build());
    }

    @Override
    public void updateTeacherRating(TeacherVoteFrom teacherVoteFrom, Authentication authentication) {
        List<UserCriteriaVote> criteriaVotes = Arrays.asList(teacherVoteFrom.getCriteriaVotes());
        User author = authenticationService.getUserByAuthentication(authentication).getUser();
        Teacher teacher = teacherRepository.findOne(teacherVoteFrom.getTeacherId());
        saveUserVoteAboutTeacher(author, teacher);
        TeacherScore teacherScore = teacher.getTeacherScore();
        updateTeacherScore(teacherScore, criteriaVotes);
        teacherRepository.save(teacher);
    }

    private void saveUserVoteAboutTeacher(User author, Teacher teacher) {
        teacherVoteRepository.save(
                TeacherVote.builder()
                        .teacherId(teacher.getId())
                        .userId(author.getId())
                        .build()
        );
    }

    private void updateTeacherScore(TeacherScore teacherScore, List<UserCriteriaVote> criteriaVotes) {
        List<CriteriaScore> criteriaScores = teacherScore.getCriteriaScores();
        updateTeacherCriteriaScores(criteriaScores, criteriaVotes);
        Double averageCriteriaScore = calcAverageCriteriaScore(criteriaScores);
        teacherScore.setSum(teacherScore.getSum() + averageCriteriaScore);
        teacherScore.setVoteCount(teacherScore.getVoteCount() + 1);
    }

    private Double calcAverageCriteriaScore(List<CriteriaScore> criteriaScores) {
        Integer criteriaCount = criteriaScores.size();
        Double sum = 0.0;
        for (CriteriaScore criteriaScore: criteriaScores) {
            sum += criteriaScore.getSum() / criteriaScore.getVoteCount();
        }
        return sum / criteriaCount;
    }


    private void updateTeacherCriteriaScores(List<CriteriaScore> criteriaScores, List<UserCriteriaVote> criteriaVotes) {
        for (UserCriteriaVote userCriteriaVote: criteriaVotes) {
            Long currentCriteriaId = userCriteriaVote.getCriteriaId();
            Byte currentRateValue = userCriteriaVote.getRate();

            CriteriaScore currentCriteriaScore = criteriaScores.stream()
                    .filter(x -> x.getCriteria().getId().equals(currentCriteriaId))
                    .collect(Collectors.toList())
                    .get(0);

            currentCriteriaScore.setVoteCount(currentCriteriaScore.getVoteCount() + 1);
            currentCriteriaScore.setSum(currentCriteriaScore.getSum() + currentRateValue);
        }
    }

    @Override
    public TeacherScoreDto getTeacherScoreDtoByTeacherId(Long teacherId) {
        return TeacherScoreDto.createOnTeacherScore(teacherRepository.findOne(teacherId).getTeacherScore());
    }
}
