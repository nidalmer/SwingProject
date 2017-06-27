package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import views.*;

public class BackMenu implements ActionListener{
	private JFrame target;

	public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
		target = (JFrame) SwingUtilities.getRoot(component);
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		target.dispose();
	}
}
