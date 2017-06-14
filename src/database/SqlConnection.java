package database;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

import departement.*;
import login.*;
import project.*;
import employee.*;

public class SqlConnection {
	private final String JDBC_CONNECT = "jdbc:sqlite:database.db";
    private final String JDBC_CLASS = "org.sqlite.JDBC";
	private final String SQL_LOGIN = "SELECT * FROM employee JOIN departement WHERE employee.name=? AND employee.password=? AND employee.departement=departement.id;";
	private final String SQL_ADDPROJECT = "INSERT INTO project (name, description, duration, budget, departement, chef) VALUES (?, ?, ?, ?, ?, ?)";
	private final String SQL_EMPOFDEP = "SELECT * FROM employee JOIN departement WHERE employee.departement=? AND employee.departement=departement.id;";
	private final String SQL_PROOFDEP = "SELECT * FROM project JOIN departement WHERE project.departement=? AND project.departement=departement.id;";
	private final String SQL_ADDTASK = "INSERT INTO task (description, final_date, duration, project, employee) VALUES (?, ?, ?, ?, ?)";
	private final String SQL_UPDATEPROJECT = "UPDATE project SET name=?, description=?, duration=?, budget=?, departement=?, chef=? WHERE id=?";
	private final String SQL_DELETEPROJECT = "DELETE FROM project WHERE id=?";
	public Connection connection;
	private PreparedStatement statement;
	private PreparedStatement addProject_statement;
	private PreparedStatement fetchEmp_statement;
	private PreparedStatement fetchPro_statement;
	private PreparedStatement addTask_statement;
	private PreparedStatement updateProject_statement;
	private PreparedStatement deleteProject_statement;
	
	public  SqlConnection() {
		try {
			connection = getConnection();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	 public Connection getConnection() {
	 	try {
	    	Class.forName(JDBC_CLASS);
	    	Connection c = DriverManager.getConnection(JDBC_CONNECT);
	    	return c;
	 	}
		catch (ClassNotFoundException | SQLException e ) {
			JOptionPane.showMessageDialog(null, "Database error : " + e.getMessage());
		}
	 	return null;
	}
	 
	public boolean login(String username, String password) {
		try {
			statement = connection.prepareStatement(SQL_LOGIN);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet res = statement.executeQuery();
			if ( res.next() ) {
				int id = res.getInt(1);
				User.userId = id;
				String name = res.getString(2);
				User.username = name;
				boolean chef = (res.getInt(3) != 0);
				User.chef = chef;
				boolean director = (res.getInt(4) != 0);
				User.director = director;
				int departementId = res.getInt(7);
				User.departementId = departementId;
				String departement = res.getString(8);
				User.departement = departement;
				res.close();
				statement.close();
				return true;
			} 
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;				
	}
	
	public boolean addProject(String name, String description, String duration, String budget, int chef, int departement) {
		try {
			addProject_statement = connection.prepareStatement(SQL_ADDPROJECT);
			addProject_statement.setString(1, name);
			addProject_statement.setString(2, description);
			addProject_statement.setString(3, duration);
			addProject_statement.setString(4, budget);
			addProject_statement.setInt(5, departement);
			addProject_statement.setInt(6, chef);
			
			int res = addProject_statement.executeUpdate();
			if ( res != 0 ) {
				addProject_statement.close();
				return true;
			} 
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
			 e.printStackTrace(System.out);
		}
		return false;				
	}
	
	public boolean fetchEmp (int departement) {
		try {
			fetchEmp_statement = connection.prepareStatement(SQL_EMPOFDEP);
			fetchEmp_statement.setInt(1, departement);
			ResultSet res = fetchEmp_statement.executeQuery();
			ArrayList<Employee> list = new ArrayList<Employee>();
			Departement.depId = departement;
			while (res.next()){
				Employee e = new Employee(res.getInt(1), res.getString(2), (res.getInt(4) != 0), (res.getInt(5) != 0), res.getInt(6), res.getString(8) );
				list.add(e);
			}
			Departement.employees = list;
			if (res.next()) {
				return true;
			}
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;				
	}
	
	public boolean fetchPro (int departement) {
		try {
			fetchPro_statement = connection.prepareStatement(SQL_PROOFDEP);
			fetchPro_statement.setInt(1, departement);
			ResultSet res = fetchPro_statement.executeQuery();
			ArrayList<Project> list = new ArrayList<Project>();
			while (res.next()){
				Project p = new Project(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getInt(7), res.getInt(8), res.getString(9));
				list.add(p);
			}
			for (Project p: list) {
				System.out.println(p.duration);
			}
			Departement.projects = list;
			if (res.next()) {
				return true;
			}
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;				
	}
	
	public boolean addTask(String description, String finaldate, String duration, String project, String employee) {
		try {
			addTask_statement = connection.prepareStatement(SQL_ADDTASK);
			addTask_statement.setString(1, description);
			addTask_statement.setString(2, finaldate);
			addTask_statement.setString(3, duration);
			System.out.println("IDS");
			for(Project p : Departement.projects) {
				if (p.name == project) {
					System.out.println(p.getName());
					addTask_statement.setInt(4, p.getProId());
				}
			}
			for(Employee e : Departement.employees) {
				if (e.username == employee) {
					System.out.println(e.getUsername());
					addTask_statement.setInt(5, e.getUserId());
				}
			}
			int res = addTask_statement.executeUpdate();
			if ( res != 0 ) {
				addTask_statement.close();
				return true;
			} 
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
			 e.printStackTrace(System.out);
		}
		return false;				
	}
	
	public boolean updateProject(String name, String description, String duration, String budget, int chef, int departement, int id) {
		try {
			updateProject_statement = connection.prepareStatement(SQL_UPDATEPROJECT);
			updateProject_statement.setString(1, name);
			updateProject_statement.setString(2, description);
			updateProject_statement.setString(3, duration);
			updateProject_statement.setString(4, budget);
			updateProject_statement.setInt(5, departement);
			updateProject_statement.setInt(6, chef);
			updateProject_statement.setInt(7, id);
			
			int res = updateProject_statement.executeUpdate();
			if ( res != 0 ) {
				updateProject_statement.close();
				return true;
			} 
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
			 e.printStackTrace(System.out);
		}
		return false;				
	}
	
	public boolean deleteProject(int id) {
		try {
			deleteProject_statement = connection.prepareStatement(SQL_DELETEPROJECT);
			deleteProject_statement.setInt(1, id);
			
			int res = deleteProject_statement.executeUpdate();
			if ( res != 0 ) {
				deleteProject_statement.close();
				return true;
			} 
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
			 e.printStackTrace(System.out);
		}
		return false;				
	}
	

}
