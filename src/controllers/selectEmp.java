package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JTextField;

import models.*;

public class selectEmp extends MouseAdapter {
	JTable table;
	JTextField idField;

	public selectEmp(JTable table, JTextField idField) {
		this.table = table;
		this.idField = idField;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		int row = table.getSelectedRow();
		int selectedId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		for (Employee e: User.employees) {
			if (e.userId == selectedId) {
				idField.setText(String.valueOf(e.userId).toString());
			}
		}
	}
}
