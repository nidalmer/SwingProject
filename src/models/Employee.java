package models;

public class Employee {
	public String username;
	public int userId;
	public boolean chef;
	public boolean director;
	public String departement;
	public int departementId;
	
	public Employee(int userId, String username, boolean chef, boolean director, int departementId, String departement) {
		this.username = username;
		this.userId = userId;
		this.chef = chef;
		this.director = director;
		this.departement = departement;
		this.departementId = departementId;
	}

	public String getUsername() {
		return username;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public boolean isChef() {
		return chef;
	}

	public boolean isDirector() {
		return director;
	}

	public String getDepartement() {
		return departement;
	}

	public int getDepartementId() {
		return departementId;
	}
	
}