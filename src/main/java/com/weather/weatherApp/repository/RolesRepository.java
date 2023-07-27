package com.weather.weatherApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.weatherApp.model.RolesEntity;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

}
