package project;

import departement.Departement;
import employee.Employee;
import login.User;

public class Project {
	public String name;
	public int proId;
	public String description;
	public String duration;
	public String budget;
	public int chef;
	public int departementId;
	public String departement;
	
	public Project(int proId, String name, String description, String duration, String budget, int chef, int departementId, String departement) {
		this.proId = proId;
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.budget = budget;
		this.chef = chef;
		this.departement = departement;
		this.departementId = departementId;
	}

	public String getName() {
		return name;
	}

	public int getProId() {
		return proId;
	}
	
	public String getDescription() {
		return description;
	}

	public String getDuration() {
		return duration;
	}
	
	public String getBudget() {
		return budget;
	}

	public int getChef() {
		return chef;
	}
	
	public String getChefName() {
		for (Employee e : User.employees) {
			if (e.userId == this.chef) {
				return e.username;
			}
		}
		return "";
	}

	public int getDepartementId() {
		return departementId;
	}
	
	public String getDepartement() {
		return departement;
	}

}
