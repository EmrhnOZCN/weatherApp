package com.weather.weatherApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="members")
public class MembersEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String userName; // E-posta adresini tutan alan

    private String pw;
    
    private boolean active;
    
    
	public MembersEntity() {
		super();
	}


	public MembersEntity(Long id, String userName, String pw, boolean active) {
		super();
		this.id = id;
		this.userName = userName;
		this.pw = pw;
		this.active = active;
	}
	
	public MembersEntity(String userName, String pw, boolean active) {
		super();
		
		this.userName = userName;
		this.pw = pw;
		this.active = active;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	


	

	

	

	
	
	
	
	
}
