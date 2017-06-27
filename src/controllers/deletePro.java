package controllers;

import java.awt.Component;
import java.awt.event.*;

import javax.swing.*;

import models.*;
import views.allTasks;

public class deletePro implements ActionListener {
	JTextField idField;
	private SqlConnection dao = new SqlConnection();
	
	public deletePro(JTextField idField) {
		this.idField = idField;
	}

	public void actionPerformed(ActionEvent e) {
		int id = Integer.parseInt(idField.getText());
		int action = JOptionPane.showConfirmDialog(null, "Do you really want to this task?");
		if (action == 0) {
			dao.deleteTask(id); 
			JOptionPane.showMessageDialog(null, "Task deleted successfully!");
			dao.fetchTask();
			allTasks task = new allTasks();
			task.frame.setVisible(true);
			Component component = (Component) e.getSource();
			JFrame target = (JFrame) SwingUtilities.getRoot(component);
			target.dispose();
		}
		return;
	}
}
