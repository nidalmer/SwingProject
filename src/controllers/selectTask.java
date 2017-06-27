package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import models.*;

public class selectTask extends MouseAdapter {
	JTable table;
	JTextField idField;
	JTextArea descField;
	JDateChooser dateField;
	JSpinField durationField;
	JTextArea commentField;
	JComboBox<String> proField;
	JComboBox<String> empField;
	JComboBox<String> statusField;
	JComboBox<String> natureField;
	
	

	public selectTask(JTable table, JTextField idField, JTextArea descField, JDateChooser dateField,
			JSpinField durationField, JTextArea commentField, JComboBox<String> proField, JComboBox<String> empField,
			JComboBox<String> statusField, JComboBox<String> natureField) {
		this.table = table;
		this.idField = idField;
		this.descField = descField;
		this.dateField = dateField;
		this.durationField = durationField;
		this.commentField = commentField;
		this.proField = proField;
		this.empField = empField;
		this.statusField = statusField;
		this.natureField = natureField;
	}

	public void mouseClicked(MouseEvent arg0) {
		int row = table.getSelectedRow();
		int selectedId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		for (Task t: User.tasks) {
			if (t.taskId == selectedId) {
				idField.setText(Integer.valueOf(t.taskId).toString());
				descField.setText(t.description);
				DateFormat df = new SimpleDateFormat("dd/MM/yy"); 
				Date startDate;
				try {
				    startDate = df.parse(t.final_date);
				    String newDateString = df.format(startDate);
				    dateField.setDate(startDate);
				    System.out.println(newDateString);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				durationField.setValue(Integer.parseInt(t.duration));
				commentField.setText(t.commentary);
				for (int i = 0; i < proField.getItemCount(); i++) {
					String item = proField.getItemAt(i);
					if (t.project.equals(item) ) {
						proField.setSelectedIndex(i);
						break;
					}
				}
				for (int i = 0; i < empField.getItemCount(); i++) {
					String item = empField.getItemAt(i);
					if (t.employee.equals(item) ) {
						empField.setSelectedIndex(i);
					}
				}
				for (int i = 0; i < statusField.getItemCount(); i++) {
					String item = statusField.getItemAt(i);
					if (t.status.equals(item) ) {
						statusField.setSelectedIndex(i);
					}
				}
				if (t.comment_nature != null) {
					for (int i = 0; i < natureField.getItemCount(); i++) {
						String item = natureField.getItemAt(i);
						if (t.comment_nature.equals(item) ) {
							natureField.setSelectedIndex(i);
						}
					}
				}
			}
		}
	}
}