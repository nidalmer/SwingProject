package models;

import java.util.ArrayList;


public class User {
	public static String username;
	public static int userId;
	public static boolean chef;
	public static boolean director;
	public static String departement;
	public static int departementId;
	public static ArrayList<Employee> employees;
	public static ArrayList<Project> projects;
	public static ArrayList<Task> tasks;
	public static ArrayList<Departement> departements;
	
	public static String getUsername() {
		return username;
	}

	public static int getUserId() {
		return userId;
	}

	public static boolean isChef() {
		return chef;
	}

	public static boolean isDirector() {
		return director;
	}

	public static String getDepartement() {
		return departement;
	}

	public static int getDepartementId() {
		return departementId;
	}

	public static boolean proAlreadyExists(String name, int departement) {
		for(Project p: projects) {
			if (p.name.equals(name) && p.departementId == departement) {
				return true;
			}
		}
		return false;
	}	
	
	public static boolean empAlreadyExists(String username, int departement) {
		for(Employee e: employees) {
			if (e.username.equals(username) && e.departementId == departement) {
				return true;
			}
		}
		return false;
	}
	
	public static int proHasId(String name) {
		for(Project p: projects) {
			if (p.name.equals(name)) {
				return p.proId;
			}
		}
		return -1;
	}
	
	public static int empHasId(String name) {
		for(Employee e: employees) {
			if (e.username.equals(name)) {
				return e.userId;
			}
		}
		return 0;
	}
}
