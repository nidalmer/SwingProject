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

public class updateTask implements ActionListener {
	JTextArea descField;
	JSpinField durationField;
	JTextArea commentField;
	JTextField idField;
	JComboBox<String> proField;
	JComboBox<String> empField;
	JComboBox<String> natureField;
	JComboBox<String> statusField;
	JDateChooser dateField;
	private SqlConnection dao = new SqlConnection();

	public updateTask(JTextField idField, JTextArea descField, JSpinField durationField, JTextArea commentField, JComboBox<String> proField,
			JComboBox<String> empField, JComboBox<String> natureField, JComboBox<String> statusField, JDateChooser dateField) {
		this.descField = descField;
		this.durationField = durationField;
		this.commentField = commentField;
		this.proField = proField;
		this.empField = empField;
		this.natureField = natureField;
		this.statusField = statusField;
		this.dateField = dateField;
		this.idField = idField;
	}

	public void actionPerformed(ActionEvent e) {
		String description = descField.getText();
		String duration = String.valueOf((Integer)durationField.getValue()).toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = dateFormat.format(dateField.getDate());
		int project = User.proHasId(String.valueOf(proField.getSelectedItem()));
		int employee = User.empHasId(String.valueOf(empField.getSelectedItem()));
		String status = String.valueOf(statusField.getSelectedItem());
		String nature = String.valueOf(natureField.getSelectedItem());
		String comment = commentField.getText();
		int id = Integer.parseInt(idField.getText());
		if (User.chef || User.director) {
			if(!description.isEmpty() && !duration.isEmpty()) {
				dao.updateTask(description, date, duration, project, employee, id); 
				JOptionPane.showMessageDialog(null, "Task updates successfully!");
				dao.fetchTask();
				allTasks task = new allTasks();
				task.frame.setVisible(true);
				Component component = (Component) e.getSource();
				JFrame target = (JFrame) SwingUtilities.getRoot(component);
				target.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
			}
		} else {
			if(!comment.isEmpty()) {
				dao.updateTask2(status, nature, comment, id); 
				JOptionPane.showMessageDialog(null, "Task updates successfully!");
				dao.fetchTask();
				allTasks task = new allTasks();
				task.frame.setVisible(true);
				Component component = (Component) e.getSource();
				JFrame target = (JFrame) SwingUtilities.getRoot(component);
				target.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
			}
		}
		
		return;
	}
}

