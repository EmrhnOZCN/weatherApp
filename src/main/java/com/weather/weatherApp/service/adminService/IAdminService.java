package com.weather.weatherApp.service.adminService;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weather.weatherApp.dto.WeatherDto;
import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.RolesEntity;
import com.weather.weatherApp.model.WeatherEntity;

public interface IAdminService {
	
	List<WeatherEntity> getAll();
	
	List<RolesEntity> getAllRoles();
	
	long getUserCount();
	
	
	void deleteUser(long userId);

	
	

	Page<MembersEntity> getAllMembers(Pageable pageable);

	long getQueryCount();
	
	
	
}
