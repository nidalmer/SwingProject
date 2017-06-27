package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import models.Departement;
import models.User;

public class selectDep extends MouseAdapter {
	JTable table;
	JTextField idField;
	
	public selectDep(JTable table, JTextField idField) {
		this.table = table;
		this.idField = idField;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		int row = table.getSelectedRow();
		int selectedId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		for (Departement d: User.departements) {
			if (d.depId == selectedId) {
				idField.setText(String.valueOf(d.depId).toString());
			}
		}
	}
}
