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

public class updatePro implements ActionListener {
	JTextField nameField;
	JTextArea descField;
	JSpinField durationField;
	JTextField budgetField;
	JTextField idField;
	private SqlConnection dao = new SqlConnection();

	public updatePro(JTextField idField, JTextField nameField, JTextArea descField, JSpinField durationField, JTextField budgetField) {
		this.nameField = nameField;
		this.descField = descField;
		this.durationField = durationField;
		this.budgetField = budgetField;
		this.idField = idField;
	}

	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String description = descField.getText();
		String duration = String.valueOf((Integer)durationField.getValue()).toString();
		String budget = budgetField.getText();
		int chef = User.userId;
		int departement = User.departementId;
		int id = Integer.parseInt(idField.getText());
		if(!name.isEmpty() && !duration.isEmpty()) {
			dao.updateProject(name, description, duration, budget, chef, departement, id); 
			JOptionPane.showMessageDialog(null, "Project " + name + " updated successfully!");
			dao.fetchPro();
			allPro pro = new allPro();
			pro.frame.setVisible(true);
			Component component = (Component) e.getSource();
			JFrame target = (JFrame) SwingUtilities.getRoot(component);
			target.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
		}
		return;
	}
}
