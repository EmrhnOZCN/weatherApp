package com.weather.weatherApp.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weather.weatherApp.model.WeatherEntity;

public interface WeatherRepository extends JpaRepository<WeatherEntity,String> {

	
	Optional<WeatherEntity> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String cityName);
	//bunu yapmamızdaki sebep veri tabanında daha önce city name sorgulanmışmı eğer listle yaparsak 
	//öğrenğin 1000 tane istanbulla ilgili sorgu varsa 1000tanesini de çekmek zorunda
	
	
	List<WeatherEntity> findTop4ByOrderByUpdatedTimeDesc();
	
	
	@Query("SELECT COUNT(w) FROM WeatherEntity w WHERE w.updatedTime >= :since")
    long getQueryCountSince(@Param("since") LocalDateTime since);
}

