package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import models.*;
import views.allPro;

public class DepPro implements ActionListener {
	JTextField idField;
	
	public DepPro(JTextField idField) {
		this.idField = idField;
	}

	public void actionPerformed(ActionEvent e) {
		JFrame projects = new JFrame();
		projects.setBounds(200, 100, 510, 425);
		projects.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		projects.getContentPane().setLayout(null);
	
		int id = Integer.parseInt(idField.getText());
		
		JPanel projectpan = new JPanel();
		projectpan.setBounds(0, 0, 494, 391);
		projects.getContentPane().add(projectpan);
		projectpan.setLayout(null);
		
		JLabel Title = new JLabel("");
		Title.setIcon(new ImageIcon(allPro.class.getResource("/images/pro.png")));
		Title.setBounds(50, 3, 394, 58);
		projectpan.add(Title);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		CustomProRenderer colouringTable = new CustomProRenderer();
	
		CellTableModel projectmodel = new CellTableModel();
		
		JTable projecttable = new JTable(projectmodel);
		JScrollPane scroller = new JScrollPane(projecttable);
		scroller.setBounds(10, 60, 475, 274);
		projectpan.add(scroller);
		projectmodel.addColumn("ID"); 
		projectmodel.addColumn("Name"); 
		projectmodel.addColumn("Description"); 
		projectmodel.addColumn("Duration"); 
		projectmodel.addColumn("Budget"); 
		projectmodel.addColumn("Chef"); 
		projectmodel.addColumn("Departement");
	    for (Project p: User.projects) {
	    	if (p.departementId == id) {
	    		projectmodel.addRow(new Object[]{String.valueOf(p.proId), p.name, p.description, p.duration, p.budget, p.getChefName(), p.departement});
	    	}
	    }
	    
	    for (int i = 0; i < projecttable.getColumnCount(); i++) {
		    projecttable.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
	    }
		
		projects.setVisible(true);
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(204, 345, 75, 28);
		projectpan.add(back_button);
		back_button.setBackground(new Color(135, 206, 235));
		back_button.setForeground(new Color(0, 0, 0));
		back_button.setFocusPainted(false);
		back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				projects.dispose();
			}
		});
		
		return;
	}
}
