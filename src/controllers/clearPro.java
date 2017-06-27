package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.toedter.components.JSpinField;

public class clearPro implements ActionListener {
	JTextField nameField;
	JTextArea descField;
	JSpinField durationField;
	JTextField budgetField;

	public clearPro(JTextField nameField, JTextArea descField, JSpinField durationField, JTextField budgetField) {
		this.nameField = nameField;
		this.descField = descField;
		this.durationField = durationField;
		this.budgetField = budgetField;
	}

	public void actionPerformed(ActionEvent arg0) {
		nameField.setText("");
		descField.setText("");
		durationField.setValue(new Integer(0));
		budgetField.setText("");
	}
}
