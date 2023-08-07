package com.weather.weatherApp.service.weatherService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.weather.weatherApp.service.weatherService.IWeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatherApp.constans.Constanst;
import com.weather.weatherApp.dto.WeatherDto;
import com.weather.weatherApp.dto.WeatherResponse;
import com.weather.weatherApp.model.WeatherEntity;
import com.weather.weatherApp.repository.WeatherRepository;

@Service
public class WeatherService implements IWeatherService {

    private WeatherRepository weatherRepository;
    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    // Verilen şehir adına göre hava durumu bilgisini döndüren metod
    // Eğer veri veritabanında mevcutsa ve belirli bir süreden daha kısa bir süre önce güncellendiyse,
    // mevcut veriyi döndürür. Aksi takdirde güncel hava durumu bilgisini alarak veritabanına kaydeder.
    @Override
    public WeatherDto getWeatherByCityName(String city,String userName) {
        Optional<WeatherEntity> weatherEntityOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);

        if (weatherEntityOptional.isPresent()) {
            WeatherEntity weatherEntity = weatherEntityOptional.get();
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime lastUpdatedTime = weatherEntity.getUpdatedTime();
            long differenceInSeconds = ChronoUnit.SECONDS.between(lastUpdatedTime, currentTime);

            if (differenceInSeconds <= 60) {
                System.out.println("Şehir verisi güncel. Mevcut veriyi dönüyorum.");
                return WeatherDto.convert(weatherEntity);
            } else {
                return WeatherDto.convert(getWeatherFromWeatherStack(city,userName));
            }
        } else {

            return WeatherDto.convert(getWeatherFromWeatherStack(city,userName));
        }
    }

    // Belirli bir şehir için hava durumu bilgisini çekmek için kullanılan metod
    // Bu metod API'den veri çeker ve veritabanına kaydetmek için başka bir metodu çağırır
    @Override
    public WeatherEntity getWeatherFromWeatherStack(String city,String userName) {
        // REST çağrısı yaparak hava durumu bilgisi için API'den yanıt alınır
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getWeatherStackUrl(city), String.class);

        try {
            // JSON veriyi hava durumu nesnesine dönüştürür
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);



            // Null kontrolü ekleniyor
            if (weatherResponse.location() == null) {


                throw new RuntimeException("API'den geçerli bir hava durumu yanıtı alınamadı.");
            } else {
                return saveWeatherEntity(city, weatherResponse,userName);
            }
        } catch (JsonProcessingException e) {
            // Eğer JSON dönüşümü sırasında bir hata oluşursa, hatayı fırlatır ve çağıran yeri bilgilendirir
            throw new RuntimeException(e);
        }
    }

    // Bu metod, veritabanına hava durumu bilgisini kaydeden bir metottur.
    // API'den alınan hava durumu yanıtını ve ilgili şehir adını alır, bu bilgilerle yeni bir WeatherEntity nesnesi oluşturur ve bunu veritabanına kaydeder.
    @Override
    public WeatherEntity saveWeatherEntity(String city, WeatherResponse response,String userName) {
        // Diğer null kontrollerini burada yapabilirsiniz

        // Tarih ve saat formatlaması için kullanılacak DateTimeFormatter oluşturulur
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // WeatherEntity sınıfından yeni bir nesne oluşturulur ve API yanıtından alınan bilgilerle doldurulur
        WeatherEntity weatherEntity = new WeatherEntity(city,
                response.location().name(),
                response.location().country(),
                response.current().temperature(),
                response.current().weatherDescriptions(),
                LocalDateTime.parse(response.location().localtime(), dateTimeFormatter),
                LocalDateTime.now(),
                response.current().weatherIcons(),
                response.current().windSpeed(),
                response.current().humidity(),
                userName



        );

        // Oluşturulan WeatherEntity nesnesi veritabanına kaydedilir
        return weatherRepository.save(weatherEntity);
    }

    // WeatherStack API'sine yapılan isteğin URL'sini oluşturmak için kullanılır
    @Override
    public String getWeatherStackUrl(String city) {
        return Constanst.API_URL + Constanst.ACCESS_KEY_PARAM + Constanst.API_KEY + Constanst.QUERY_KEY_PARAM + city;
    }
}
