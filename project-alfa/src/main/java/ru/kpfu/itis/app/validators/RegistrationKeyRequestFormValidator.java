package ru.kpfu.itis.app.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.app.forms.RegistrationKeyRequestForm;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class RegistrationKeyRequestFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(RegistrationKeyRequestForm.class.getName());
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationKeyRequestForm form = (RegistrationKeyRequestForm) o;
        if (form.getDocumentImageMultipartFile() == null) {
            errors.reject("empty.documentImage", "Document image is empty. Please, fix it, because we have to check you.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "invalid.name", "Check 'name' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "invalid.surname", "Check 'surname' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "invalid.email", "Check 'email' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countOfKey", "invalid.countOfKey", "Check 'count of key' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "university", "invalid.university", "Check 'university' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "institute", "invalid.institute", "Check 'institute' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "course", "invalid.course", "Check 'course' field. Is it empty?");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "group", "invalid.group", "Check 'group' field. Is it empty?");
    }
}
