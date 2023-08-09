package com.weather.weatherApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CityNameValidator implements ConstraintValidator<CityNameConstraint, String> {

    private static final Logger logger = LoggerFactory.getLogger(CityNameValidator.class);

    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        // Bu metodun içeriği boş bırakılmış, çünkü herhangi bir başlangıç işlemi yapmanız gerekmiyor.
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Gelen şehir adını düzenlemek ve geçerliliğini kontrol etmek için aşağıdaki adımları takip ediyoruz:

        // Adı alfanumerik karakterlere indirgeyerek özel karakterleri kaldırıyoruz.
        value = value.replaceAll("[^a-zA-Z0-9]", "");

        // Şehir adının sadece sayısal olmadığını ve boş olmadığını kontrol ediyoruz.
        boolean isValid = !StringUtils.isNumeric(value) && !StringUtils.isBlank(value);

        if (!isValid) {
            // Eğer geçerli bir şehir adı değilse, kısıtlama ihlali bildirimi oluşturuyoruz.
            context.buildConstraintViolationWithTemplate("City name is not valid: " + value).addConstraintViolation();

            // Ayrıca loga da bu durumu kaydediyoruz.
            logger.info("The city parameter is not valid. value: " + value);
        }

        // Sonuç olarak, şehir adının hem sayısal hem de boş olmadığı durumları kontrol ederek sonucu dönüyoruz.
        return isValid;
    }
}
