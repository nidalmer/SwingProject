package database;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

import departement.*;
import login.*;
import project.*;
import task.Task;
import employee.*;

public class SqlConnection {
	private final String JDBC_CONNECT = "jdbc:sqlite:database.db";
    private final String JDBC_CLASS = "org.sqlite.JDBC";
	private final String SQL_LOGIN = "SELECT * FROM employee JOIN departement WHERE employee.name=? AND employee.password=? AND employee.departement=departement.id;";
	private final String SQL_ADDPROJECT = "INSERT INTO project (name, description, duration, budget, departement, chef) VALUES (?, ?, ?, ?, ?, ?)";
	private final String SQL_EMPOFDEP = "SELECT * FROM employee JOIN departement WHERE employee.departement=? AND employee.departement=departement.id;";
	private final String SQL_PROOFDEP = "SELECT * FROM project JOIN departement WHERE project.departement=? AND project.departement=departement.id;";
	private final String SQL_TASKOFDEP = "SELECT * FROM task JOIN departement JOIN employee JOIN project WHERE employee.departement=departement.id AND employee.id=task.employee AND task.project = project.id AND departement.id=?;";
	private final String SQL_ADDTASK = "INSERT INTO task (description, final_date, duration, project, employee, status) VALUES (?, ?, ?, ?, ?, ?)";
	private final String SQL_UPDATETASK = "UPDATE task SET description=?, final_date=?, duration=?, project=?, employee=? WHERE id=?";
	private final String SQL_DELETETASK = "DELETE FROM task WHERE id=?";
	private final String SQL_UPDATETASK2 = "UPDATE task SET status=?, comment_nature=?, commentary=? WHERE id=?";
	private final String SQL_UPDATEPROJECT = "UPDATE project SET name=?, description=?, duration=?, budget=?, departement=?, chef=? WHERE id=?";
	private final String SQL_DELETEPROJECT = "DELETE FROM project WHERE id=?";
	public Connection connection;
	private PreparedStatement statement;
	private PreparedStatement addProject_statement;
	private PreparedStatement fetchEmp_statement;
	private PreparedStatement fetchPro_statement;
	private PreparedStatement fetchTask_statement;
	private PreparedStatement addTask_statement;
	private PreparedStatement deleteTask_statement;
	private PreparedStatement updateTask_statement;
	private PreparedStatement updateTask2_statement;
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
				Employee e = new Employee(res.getInt(1), res.getString(2), (res.getInt(4) != 1), (res.getInt(5) != 1), res.getInt(6), res.getString(8) );
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
	
	public boolean fetchTask (int departement) {
		try {
			fetchTask_statement = connection.prepareStatement(SQL_TASKOFDEP);
			fetchTask_statement.setInt(1, departement);
			ResultSet res = fetchTask_statement.executeQuery();
			ArrayList<Task> list = new ArrayList<Task>();
			Departement.depId = departement;
			while (res.next()){
				Task t = new Task(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6),  res.getString(7), res.getInt(9), res.getString(13), res.getInt(10), res.getString(11), res.getInt(18), res.getString(19));
				list.add(t);
			}
			Departement.tasks = list;
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
			Departement.projects = list;
			if (res.next()) {
				return true;
			}
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;				
	}
	
	public boolean addTask(String description, String finaldate, String duration, int project, int employee) {
		try {
			addTask_statement = connection.prepareStatement(SQL_ADDTASK);
			addTask_statement.setString(1, description);
			addTask_statement.setString(2, finaldate);
			addTask_statement.setString(3, duration);
			addTask_statement.setInt(4, project);
			addTask_statement.setInt(5, employee);
			addTask_statement.setString(6, "In progress");
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
	
	public boolean updateTask(String description, String finaldate, String duration, int project, int employee, int id) {
		try {
			updateTask_statement = connection.prepareStatement(SQL_UPDATETASK);
			updateTask_statement.setString(1, description);
			updateTask_statement.setString(2, finaldate);
			updateTask_statement.setString(3, duration);
			updateTask_statement.setInt(4, project);
			updateTask_statement.setInt(5, employee);
			updateTask_statement.setInt(6, id);
			int res = updateTask_statement.executeUpdate();
			if ( res != 0 ) {
				updateTask_statement.close();
				return true;
			} 
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
			 e.printStackTrace(System.out);
		}
		return false;				
	}
	
	public boolean updateTask2 (String status, String nature, String comment, int id) {
		try {
			updateTask2_statement = connection.prepareStatement(SQL_UPDATETASK2	);
			updateTask2_statement.setString(1, status);
			updateTask2_statement.setString(2, nature);
			updateTask2_statement.setString(3, comment);
			updateTask2_statement.setInt(4, id);
			int res = updateTask2_statement.executeUpdate();
			if ( res != 0 ) {
				updateTask2_statement.close();
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
	
	public boolean deleteTask(int id) {
		try {
			deleteTask_statement = connection.prepareStatement(SQL_DELETETASK);
			deleteTask_statement.setInt(1, id);
			
			int res = deleteTask_statement.executeUpdate();
			if ( res != 0 ) {
				deleteTask_statement.close();
				return true;
			} 
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e.getMessage());
			 e.printStackTrace(System.out);
		}
		return false;				
	}
	

}
