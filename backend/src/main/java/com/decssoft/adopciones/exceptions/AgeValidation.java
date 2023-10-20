package com.decssoft.adopciones.exceptions;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author mis_p
 */
@Slf4j
public class AgeValidation implements ConstraintValidator<Age, Date> {

    @Override
    public boolean isValid(Date birthDate, ConstraintValidatorContext cvc) {
        if (birthDate == null) {
            return false;
        }
        Calendar dateInCalendar = Calendar.getInstance();
        dateInCalendar.setTime(birthDate);
        return Calendar.getInstance().get(Calendar.YEAR) - dateInCalendar.get(Calendar.YEAR) >= 18;
    }

}
