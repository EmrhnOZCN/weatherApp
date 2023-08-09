package com.weather.weatherApp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
// CityNameValidator sınıfıyla ilişkilendirilen bir kısıtlama tanımlıyoruz.
@Constraint(validatedBy = {CityNameValidator.class})
// Bu kısıtlamanın uygulanabileceği hedeflerin türlerini belirtiyoruz.
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
// Bu kısıtlamanın çalışma zamanında kullanılacağını belirtiyoruz.
@Retention(RetentionPolicy.RUNTIME)
public @interface CityNameConstraint {

    // Kısıtlama ihlali durumunda görüntülenecek varsayılan mesajı belirtiyoruz.
    String message() default "Şehir bilgisi bulunamadı";

    // Kısıtlamayı gruplandırmak için kullanılan sınıfları belirtiyoruz.
    Class<?>[] groups() default {};

    // Kısıtlama ihlali durumunda taşınan ek verileri belirtiyoruz.
    Class<? extends Payload>[] payload() default {};
}
