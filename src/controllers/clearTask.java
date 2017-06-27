package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class clearTask implements ActionListener {
	JTextArea descField;
	JSpinField durationField;
	JTextArea commentField;
	JComboBox<String> proField;
	JComboBox<String> empField;
	JComboBox<String> natureField;
	JComboBox<String> statusField;
	JDateChooser dateField;

	public clearTask(JTextArea descField, JSpinField durationField, JTextArea commentField, JComboBox<String> proField,
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

	public void actionPerformed(ActionEvent arg0) {
		dateField.setCalendar(null);
		descField.setText("");
		durationField.setValue(new Integer(0));
		commentField.setText("");
		proField.setSelectedIndex(0);
		empField.setSelectedIndex(0);
		statusField.setSelectedIndex(0);
		natureField.setSelectedIndex(0);
	}
}

