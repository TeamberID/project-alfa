package ru.kpfu.itis.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.app.forms.TutorAddingForm;

/**
 * Created by Timur Iderisov on 02.05.2018.
 */
@Component
public class TutorAddingFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(TutorAddingForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO
    }
}