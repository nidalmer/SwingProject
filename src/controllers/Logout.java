package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import views.Login;

public class Logout implements ActionListener{
	private JFrame target;

	public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
		target = (JFrame) SwingUtilities.getRoot(component);
		Login window = new Login();
		window.frame.setVisible(true);
		target.dispose();
	}
}
