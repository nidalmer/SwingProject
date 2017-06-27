package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.components.JSpinField;

import models.*;

public class selectPro extends MouseAdapter {
	JTable table;
	JTextField idField;
	JTextArea descField;
	JTextField nameField;
	JSpinField durationField;
	JTextField budgetField;
	JTextField chefField;
	JTextField departementField;
	
	public selectPro(JTable table, JTextField idField, JTextArea descField, JTextField nameField,
			JSpinField durationField, JTextField budgetField, JTextField chefField, JTextField departementField) {
		this.table = table;
		this.idField = idField;
		this.descField = descField;
		this.nameField = nameField;
		this.durationField = durationField;
		this.budgetField = budgetField;
		this.chefField = chefField;
		this.departementField = departementField;
	}

	public void mouseClicked(MouseEvent arg0) {
		int row = table.getSelectedRow();
		int selectedId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		for (Project p: User.projects) {
			if (p.proId == selectedId) {
				descField.setText(p.description);
				nameField.setText(p.name);
				durationField.setValue(Integer.parseInt(p.duration));
				budgetField.setText(p.budget);
				chefField.setText(p.getChefName());
				departementField.setText(p.departement);
				idField.setText(String.valueOf(p.proId).toString());
			}
		}
	}
}
