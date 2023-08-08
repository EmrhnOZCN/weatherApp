package com.weather.weatherApp.service.userService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.RolesEntity;
import com.weather.weatherApp.repository.MembersRepository;
import com.weather.weatherApp.repository.RolesRepository;

@Service
public class UserService implements IUserService {

	private MembersRepository membersRepository;
	private RolesRepository rolesRepository;

	public UserService(MembersRepository membersRepository, RolesRepository rolesRepository) {
		super();
		this.membersRepository = membersRepository;
		this.rolesRepository = rolesRepository;
	}

	// Yeni bir üye ekler
	@Override
	public void addMembers(MembersEntity membersEntity) {
		membersRepository.save(membersEntity);
	}

	@Override
	public MembersEntity findByUserName(String userName) {
		return membersRepository.findByUserName(userName);
	}
}
