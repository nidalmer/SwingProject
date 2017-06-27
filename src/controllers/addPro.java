package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.components.JSpinField;

import models.SqlConnection;
import models.User;
import views.allPro;

public class addPro implements ActionListener {
	JTextField nameField;
	JTextArea descField;
	JSpinField durationField;
	JTextField budgetField;
	private SqlConnection dao = new SqlConnection();

	public addPro(JTextField nameField, JTextArea descField, JSpinField durationField, JTextField budgetField) {
		this.nameField = nameField;
		this.descField = descField;
		this.durationField = durationField;
		this.budgetField = budgetField;
	}

	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String description = descField.getText();
		String duration = String.valueOf((Integer)durationField.getValue()).toString();
		String budget = budgetField.getText();
		int chef = User.userId;
		int departement = User.departementId;
		if(!name.isEmpty() && !duration.isEmpty()) {
			if (!User.proAlreadyExists(name, departement)) {
				dao.addProject(name, description, duration, budget, chef, departement); 
				JOptionPane.showMessageDialog(null, "Project " + name + " created successfully!");
				dao.fetchPro();
				allPro pro = new allPro();
				pro.frame.setVisible(true);
		        Component component = (Component) e.getSource();
				JFrame target = (JFrame) SwingUtilities.getRoot(component);
				target.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Project already exists please press Update!");
			} 
		} else {
			JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
		}
		return;
	}
}
