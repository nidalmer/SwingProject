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
}
