package com.weather.weatherApp.service.userService;

import java.util.List;

import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.RolesEntity;


public interface IUserService {
	
	void addMembers(MembersEntity membersEntity);


    MembersEntity findByUserName(String userName);
}
