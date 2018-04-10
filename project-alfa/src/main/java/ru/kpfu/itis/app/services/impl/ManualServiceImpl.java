package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Manual;
import ru.kpfu.itis.app.services.ExamService;
import ru.kpfu.itis.app.services.ManualService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class ManualServiceImpl implements ManualService {

    private ExamService examService;

    public ManualServiceImpl(ExamService examService) {
        this.examService = examService;
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
}
