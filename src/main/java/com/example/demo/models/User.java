package com.example.demo.models;
//abstract classh gia na klhronomei o professor kai student

public abstract class User {
	private String username;
	private String password;
	private String name;
	private String lastname;
	private String Role;
	public User() {
		
	}
			
	
	public User(String username, String password, String name, String lastname) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
	}
	
	
	
	public User(String username, String password, String name, String lastname, String role) {
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		Role = role;
	}


	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", lastname=" + lastname
				+ "]";
	}
	
	
	
}
