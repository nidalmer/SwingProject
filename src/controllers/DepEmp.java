package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import models.*;
import views.allPro;

public class DepEmp implements ActionListener {
	JTextField idField;
	
	public DepEmp(JTextField idField) {
		this.idField = idField;
	}

	public void actionPerformed(ActionEvent e) {
		JFrame employees = new JFrame();
		employees.setBounds(200, 100, 510, 425);
		employees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		employees.getContentPane().setLayout(null);

		int id = Integer.parseInt(idField.getText());
		
		JPanel employeepan = new JPanel();
		employeepan.setBounds(0, 0, 494, 391);
		employees.getContentPane().add(employeepan);
		employeepan.setLayout(null);
		
		JLabel Title = new JLabel("");
		Title.setIcon(new ImageIcon(allPro.class.getResource("/images/dep.png")));
		Title.setBounds(50, 1, 394, 58);
		employeepan.add(Title);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		CustomProRenderer colouringTable = new CustomProRenderer();

		CellTableModel employeemodel = new CellTableModel(); 
		
		JTable employeetable = new JTable(employeemodel);
		JScrollPane scroller = new JScrollPane(employeetable);
		scroller.setBounds(10, 60, 475, 274);
		employeepan.add(scroller);
		employeemodel.addColumn("ID"); 
		employeemodel.addColumn("Username"); 
		employeemodel.addColumn("Departement");
	    for (Employee em: User.employees) {
	    	if (em.departementId == id) {
	    		employeemodel.addRow(new Object[]{String.valueOf(em.userId), em.username, em.departement});
	    	}
	    }
	    
	    for (int i = 0; i < employeetable.getColumnCount(); i++) {
	    	employeetable.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
	    }
		
	    employees.setVisible(true);
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(204, 345, 75, 28);
		employeepan.add(back_button);
		back_button.setBackground(new Color(135, 206, 235));
		back_button.setForeground(new Color(0, 0, 0));
		back_button.setFocusPainted(false);
		back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				employees.dispose();
			}
		});
		
		return;
	}
}
