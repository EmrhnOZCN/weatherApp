package com.weather.weatherApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.weatherApp.model.MembersEntity;

public interface MembersRepository extends JpaRepository<MembersEntity, Long> {
	long count();
	MembersEntity findByUserName(String userName);
}
