package ru.kpfu.itis.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.app.forms.InstituteAddingForm;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 23.03.2018
 */
@Component
public class InstituteAddingFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(InstituteAddingForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO
    }
}
