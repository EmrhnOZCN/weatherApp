package com.weather.weatherApp.model;

import jakarta.persistence.*;

@Entity
@Table(name="members")
public class MembersEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String userName; // E-posta adresini tutan alan

    private String pw;
    
    private boolean active;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private RolesEntity rolesEntity;

    
    
	public MembersEntity() {
		super();
	}


	public MembersEntity(Long id, String userName, String pw, boolean active,RolesEntity rolesEntity) {
		super();
		this.id = id;
		this.userName = userName;
		this.pw = pw;
		this.active = active;
		this.rolesEntity=rolesEntity;
	}
	
	public MembersEntity(String userName, String pw, boolean active,RolesEntity rolesEntity) {
		super();
		
		this.userName = userName;
		this.pw = pw;
		this.active = active;
		this.rolesEntity=rolesEntity;
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


	public RolesEntity getRolesEntity() {
		return rolesEntity;
	}

	public void setRolesEntity(RolesEntity rolesEntity) {
		this.rolesEntity = rolesEntity;
	}
}
