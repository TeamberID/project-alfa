package ru.kpfu.itis.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.app.forms.ManualAddingForm;
import ru.kpfu.itis.app.model.Exam;
import ru.kpfu.itis.app.services.ExamService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class ManualAddingFromValidator implements Validator {
    private ExamService examService;

    public ManualAddingFromValidator(ExamService examService) {
        this.examService = examService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(ManualAddingForm.class.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        ManualAddingForm form = (ManualAddingForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty.title", "Пустой заголовок");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "empty.author", "Пустой автор");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "empty.file", "Пустой файл");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "examId", "empty.examId", "Пустой идентификатор экзамена");

        Exam exam = examService.getExamById(form.getExamId());
        if (exam == null) {
            errors.reject("bad.examId", "Exam with id " + form.getExamId() + " does not exist.");
        }
    }
}
