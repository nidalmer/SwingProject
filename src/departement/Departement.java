package departement;

import java.util.ArrayList;

import project.*;
import employee.*;

public class Departement {
	public static String name;
	public static int depId;
	public static ArrayList<Employee> employees;
	public static ArrayList<Project> projects;
	
	public Departement(int depId) {
		Departement.depId = depId;
	}
}
