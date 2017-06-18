package departement;

import java.util.ArrayList;

import project.*;
import task.Task;
import employee.*;

public class Departement {
	public static String name;
	public static int depId;
	public static ArrayList<Employee> employees;
	public static ArrayList<Project> projects;
	public static ArrayList<Task> tasks;
	
	public Departement(int depId) {
		Departement.depId = depId;
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
