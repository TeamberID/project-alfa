package ru.kpfu.itis.app.validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.app.forms.RegistrationKeyRequestForm;
import ru.kpfu.itis.app.model.Institute;
import ru.kpfu.itis.app.model.University;
import ru.kpfu.itis.app.repositories.UniversitiesRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class RegistrationKeyRequestFormValidator implements Validator {

    private UniversitiesRepository universitiesRepository;

    @Autowired
    public RegistrationKeyRequestFormValidator(UniversitiesRepository universitiesRepository) {
        this.universitiesRepository = universitiesRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(RegistrationKeyRequestForm.class.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationKeyRequestForm form = (RegistrationKeyRequestForm) o;
        if (form.getDocumentStorageName() == null) {
            errors.reject("empty.documentImage", "Document image is empty. Please, fix it, because we have to check you.");
        }
        if (!checkUniAndInstitute(form.getUniversityId(), form.getInstituteId())) {
            errors.reject("empty.universityOrInstitute", "Select your university and institute");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "invalid.name", "Check 'name' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "invalid.surname", "Check 'surname' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "invalid.email", "Check 'email' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countOfKey", "invalid.countOfKey", "Check 'count of key' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "universityId", "invalid.universityId", "Check 'university' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "instituteId", "invalid.instituteId", "Check 'institute' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "course", "invalid.course", "Check 'course' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "group", "invalid.group", "Check 'group' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documentStorageName", "invalid.documentStorageName", "Check 'file' field. Is it empty?");
    }

    private boolean checkUniAndInstitute(Long universityId, Long instituteId) {
        Optional<University> universityOptional = universitiesRepository.findById(universityId);
        if (!universityOptional.isPresent()) {
            return false;
        }
        University university = universityOptional.get();
        List<Institute> institutes = university.getInstitutes();
        long count = institutes.stream().filter(x -> x.getId().equals(instituteId)).count();
        return count == 1;
    }
}
