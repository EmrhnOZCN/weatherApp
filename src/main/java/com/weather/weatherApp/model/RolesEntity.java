package com.weather.weatherApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RolesEntity {

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

	
	

	
    private String role;
    
    
    

    


	public RolesEntity() {
		super();
	}







	public RolesEntity(Long roleId,  String role) {
		super();
		this.roleId = roleId;

		this.role = role;
	}
	
	public RolesEntity( String role) {
		

		this.role = role;
	}







	public Long getRoleId() {
		return roleId;
	}







	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}












	public String getRole() {
		return role;
	}







	public void setRole(String role) {
		this.role = role;
	}




	
	
	
	
	
	
	
}
