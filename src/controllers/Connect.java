package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import models.SqlConnection;
import views.Login;
import views.MainMenu;

public class Connect implements ActionListener{
	SqlConnection dao = new SqlConnection();
	int attempts;
	JTextField usernameField;
	JPasswordField passwordField;

	public Connect(int attempts, JTextField usernameField, JPasswordField passwordField) {
		this.attempts = attempts;
		this.usernameField = usernameField;
		this.passwordField = passwordField;
	}
	
	public Connect() {};

	@Override
	public void actionPerformed(ActionEvent e) {
		String btn_name = ((JButton) e.getSource()).getText();
		if(btn_name == "Connect") {
			String username = usernameField.getText();
			char[] passwordChars = passwordField.getPassword();
			String password = String.valueOf(passwordChars);
			while (attempts != 0) {
				if (dao.login(username, password)) {
					dao.fetchEmp();
					dao.fetchPro();
					dao.fetchTask();
					dao.fetchDep();
					JOptionPane.showMessageDialog(null, "Logged in successfully " + username);
					MainMenu menu = new MainMenu();
					menu.setVisible(true);
			        Component component = (Component) e.getSource();
					JFrame target = (JFrame) SwingUtilities.getRoot(component);
					target.dispose();
				} else {
					attempts--;
					JOptionPane.showMessageDialog(null, "Incorrect name or password! " + attempts + " attempts left!");
				}
				return;
			}
			if (attempts == 0) {
				JOptionPane.showMessageDialog(null, "Login attempts exceeded");
				System.exit(0);
			}
		} else {
			Component component = (Component) e.getSource();
			JFrame target = (JFrame) SwingUtilities.getRoot(component);
			Login window = new Login();
			window.frame.setVisible(true);
			target.dispose();
		}
	}
}
