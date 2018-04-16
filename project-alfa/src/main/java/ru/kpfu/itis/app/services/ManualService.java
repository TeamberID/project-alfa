package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.ManualAddingForm;
import ru.kpfu.itis.app.model.Manual;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface ManualService {
    List<Manual> getUserManualsByExamId(Long examId);
    List<Manual> getUserManualsByExamIdAndCount(Long examId, Integer count);

    List<Manual> getAllManuals();

    void saveManual(ManualAddingForm form);

    void deleteManualById(Long manualId);
}
