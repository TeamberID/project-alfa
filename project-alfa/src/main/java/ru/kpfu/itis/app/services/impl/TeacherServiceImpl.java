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
import ru.kpfu.itis.app.repositories.CriteriaRepository;
import ru.kpfu.itis.app.repositories.SubjectRepository;
import ru.kpfu.itis.app.repositories.TeacherVoteRepository;
import ru.kpfu.itis.app.repositories.TeachersRepository;
import ru.kpfu.itis.app.services.AuthenticationService;
import ru.kpfu.itis.app.services.SessionService;
import ru.kpfu.itis.app.services.TeacherService;
import ru.kpfu.itis.app.utils.FileStorageUtil;

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
    private SubjectRepository subjectRepository;
    private FileStorageUtil fileStorageUtil;
    private AuthenticationService authenticationService;
    private TeacherVoteRepository teacherVoteRepository;
    private CriteriaRepository criteriaRepository;

    public TeacherServiceImpl(SessionService sessionService, TeachersRepository teacherRepository,
                              SubjectRepository subjectRepository, FileStorageUtil fileStorageUtil, AuthenticationService authenticationService, TeacherVoteRepository teacherVoteRepository, CriteriaRepository criteriaRepository) {
        this.sessionService = sessionService;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.fileStorageUtil = fileStorageUtil;
        this.authenticationService = authenticationService;
        this.teacherVoteRepository = teacherVoteRepository;
        this.criteriaRepository = criteriaRepository;
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
        List<Subject> teacherSubjects = teacherAddingForm.getSubjectsId().stream()
                .map(subjectRepository::findOne)
                .collect(Collectors.toList());

        TeacherPhoto teacherPhoto = TeacherPhoto.builder()
                .url(fileStorageUtil.getBucketURL() + "/" + teacherAddingForm.getTeacherPhotoStorageName())
                .build();

        TeacherScore teacherScore = prepareStartedTeacherScore();

        teacherRepository.save(Teacher.builder()
                .name(teacherAddingForm.getName())
                .subjects(teacherSubjects)
                .photo(teacherPhoto)
                .teacherScore(teacherScore)
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

    private TeacherScore prepareStartedTeacherScore() {
        TeacherScore teacherScore = TeacherScore.builder().sum(5D).voteCount(1L).build();

        List<CriteriaScore> criteriaScoreList = criteriaRepository.findAll().stream()
                .map(currentCriteria ->
                        CriteriaScore.builder().teacherScore(teacherScore).criteria(currentCriteria).sum(5L).voteCount(1L).build())
                .collect(Collectors.toList());

        teacherScore.setCriteriaScores(criteriaScoreList);
        return teacherScore;
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
