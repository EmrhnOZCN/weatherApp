package com.weather.weatherApp.exception;

// Yinelemeli bir kullanıcı kaydı girişiminde fırlatılan özel istisna sınıfıdır.
public class DuplicateUserException extends RuntimeException {

    // İstisna mesajını belirterek tek parametreli bir kurucu yöntem.
    public DuplicateUserException(String message) {
        super(message);
    }

    // İstisna mesajını ve nedenini belirterek iki parametreli bir kurucu yöntem.
    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
