package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import models.SqlConnection;
import models.User;
import views.allTasks;

public class addTask implements ActionListener {
	JTextArea descField;
	JSpinField durationField;
	JTextArea commentField;
	JComboBox<String> proField;
	JComboBox<String> empField;
	JComboBox<String> natureField;
	JComboBox<String> statusField;
	JDateChooser dateField;
	private SqlConnection dao = new SqlConnection();

	public addTask(JTextArea descField, JSpinField durationField, JTextArea commentField, JComboBox<String> proField,
			JComboBox<String> empField, JComboBox<String> natureField, JComboBox<String> statusField, JDateChooser dateField) {
		this.descField = descField;
		this.durationField = durationField;
		this.commentField = commentField;
		this.proField = proField;
		this.empField = empField;
		this.natureField = natureField;
		this.statusField = statusField;
		this.dateField = dateField;
	}

	public void actionPerformed(ActionEvent e) {
		String description = descField.getText();
		String duration = String.valueOf((Integer)durationField.getValue()).toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = dateFormat.format(dateField.getDate());
		int project = User.proHasId(String.valueOf(proField.getSelectedItem()));
		int employee = User.empHasId(String.valueOf(empField.getSelectedItem()));
		if(!description.isEmpty() && !duration.isEmpty()) {
			dao.addTask(description, date, duration, project, employee); 
			JOptionPane.showMessageDialog(null, "Task created successfully!");
			dao.fetchTask();
			allTasks task = new allTasks();
			task.frame.setVisible(true);
	        Component component = (Component) e.getSource();
			JFrame target = (JFrame) SwingUtilities.getRoot(component);
			target.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
		}
		return;
	}
}

