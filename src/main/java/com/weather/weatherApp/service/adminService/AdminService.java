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
public class AdminService  implements IAdminService{
	
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

	@Override
	public List<WeatherEntity> getAll() {
		
		
		
		
		return weatherRepository.findTop4ByOrderByUpdatedTimeDesc();
	}
	
	@Override
	public List<RolesEntity> getAllRoles() {
		// TODO Auto-generated method stub
		return rolesRepository.findAll();
	}
	
	@Override
	public long getUserCount() {
		// TODO Auto-generated method stub
		
		System.out.println(membersRepository.count());
		return membersRepository.count();
	}
	
	@Override
	public long getQueryCount() {
		
		
		LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
		
		System.out.println(last24Hours);
		
		return weatherRepository.getQueryCountSince(last24Hours);
	}
	
	
	@Override
	public Page<MembersEntity> getAllMembers(Pageable pageable) {
		// TODO Auto-generated method stub
		return membersRepository.findAll(pageable);
	}
	
	
	@Override
	public void deleteUser(long userId) {
		
		
		membersRepository.deleteById(userId);
		
	}
	
	
}
