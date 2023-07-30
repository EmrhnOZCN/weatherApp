package com.weather.weatherApp.service.adminService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.weather.weatherApp.dto.WeatherDto;
import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.RolesEntity;
import com.weather.weatherApp.model.WeatherEntity;
import com.weather.weatherApp.repository.MembersRepository;
import com.weather.weatherApp.repository.RolesRepository;
import com.weather.weatherApp.repository.WeatherRepository;

@Service
public class AdminService implements IAdminService {

	private WeatherRepository weatherRepository;
	private MembersRepository membersRepository;
	private RolesRepository rolesRepository;

	public AdminService(WeatherRepository weatherRepository, MembersRepository membersRepository,
						RolesRepository rolesRepository) {
		super();
		this.weatherRepository = weatherRepository;
		this.membersRepository = membersRepository;
		this.rolesRepository = rolesRepository;
	}

	// En son güncellenen 4 hava durumu kaydını getirir
	@Override
	public List<WeatherEntity> getAll() {
		return weatherRepository.findTop4ByOrderByUpdatedTimeDesc();
	}

	// Tüm rolleri getirir


	// Kullanıcı sayısını getirir
	@Override
	public long getUserCount() {
		return membersRepository.count();
	}

	// Son 24 saat içinde yapılan hava durumu sorgularının sayısını getirir
	@Override
	public long getQueryCount() {
		LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
		return weatherRepository.getQueryCountSince(last24Hours);
	}

	// Tüm üyeleri sayfalama ile getirir
	@Override
	public Page<MembersEntity> getAllMembers(Pageable pageable) {
		return membersRepository.findAll(pageable);
	}

	// Kullanıcıyı siler
	@Override
	public void deleteUser(long userId) {
		membersRepository.deleteById(userId);
	}

}
