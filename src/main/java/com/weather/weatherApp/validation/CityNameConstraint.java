package com.weather.weatherApp.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CityNameValidator.class})
@Target({ElementType.PARAMETER, ElementType.FIELD,ElementType.METHOD})
@Retention( RetentionPolicy.RUNTIME)
public @interface CityNameConstraint {


    String message() default "Şehir bilgisi bulunamadı";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
