package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.ManualAddingForm;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.model.FileInfo;
import ru.kpfu.itis.app.model.Manual;
import ru.kpfu.itis.app.repositories.ManualRepository;
import ru.kpfu.itis.app.services.ExamService;
import ru.kpfu.itis.app.services.ManualService;
import ru.kpfu.itis.app.utils.FileStorageUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class ManualServiceImpl implements ManualService {

    private ExamService examService;
    private ManualRepository manualRepository;
    private FileStorageUtil fileStorageUtil;

    public ManualServiceImpl(ExamService examService, ManualRepository manualRepository, FileStorageUtil fileStorageUtil) {
        this.examService = examService;
        this.manualRepository = manualRepository;
        this.fileStorageUtil = fileStorageUtil;
    }

    @Override
    public List<Manual> getUserManualsByExamId(Long examId) {
        return examService.getExamById(examId).getManuals();
    }

    @Override
    public List<Manual> getUserManualsByExamIdAndCount(Long examId, Integer count) {
        return getUserManualsByExamId(examId).stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public List<Manual> getAllManuals() {
        return manualRepository.findAll();
    }

    @Override
    public void saveManual(ManualAddingForm form) {
        Exam exam = examService.getExamById(form.getExamId());
        FileInfo manualFileInfo = fileStorageUtil.getManualFileInfoByMultipart(form.getFile());
        Manual manual = Manual.builder()
                .title(form.getTitle())
                .author(form.getAuthor())
                .exam(exam)
                .subject(exam.getSubject())
                .fileInfo(manualFileInfo)
                .build();
        fileStorageUtil.saveManualToStorage(form.getFile(), manual);
        manualRepository.save(manual);
    }

    @Override
    public void deleteManualById(Long manualId) {
        manualRepository.delete(manualId);
    }
}
