package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import models.*;
import views.*;
import custom.*;

public class DepControl extends MouseAdapter implements ActionListener {
	JTable table;
	JTextField idField;
	
	public DepControl (JTable table, JTextField idField) {
		this.table = table;
		this.idField = idField;
	}
	
	public DepControl (JTextField idField) {
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
	
	public void actionPerformed(ActionEvent e) {
        String btn_name = ((JButton) e.getSource()).getText();
		if(btn_name == "Tasks") {
			JFrame tasks = new JFrame();
			tasks.setBounds(100, 100, 510, 425);
			tasks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tasks.getContentPane().setLayout(null);
		
			int id = Integer.parseInt(idField.getText());
			
			JPanel taskpan = new JPanel();
			taskpan.setBounds(0, 0, 494, 391);
			tasks.getContentPane().add(taskpan);
			taskpan.setLayout(null);
			
			JLabel Title = new JLabel("");
			Title.setIcon(new ImageIcon(allPro.class.getResource("/images/task.png")));
			Title.setBounds(50, 1, 394, 58);
			taskpan.add(Title);
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			
			CustomTaskRenderer colouringTable = new CustomTaskRenderer();
		
			CellTableModel taskmodel = new CellTableModel();
			
			JTable tasktable = new JTable(taskmodel);
			JScrollPane scroller = new JScrollPane(tasktable);
			scroller.setBounds(10, 60, 475, 274);
			taskpan.add(scroller);
			taskmodel.addColumn("ID");  
			taskmodel.addColumn("Description");  
			taskmodel.addColumn("Final date"); 
			taskmodel.addColumn("Duration"); 
			taskmodel.addColumn("Comment"); 
			taskmodel.addColumn("Project"); 
			taskmodel.addColumn("Employee");
			taskmodel.addColumn("Status");
		    tasktable.removeColumn( tasktable.getColumnModel().getColumn(7) );
		    for (Task t: User.tasks) {
		    	if(t.departementId == id)  {
			    	if (t.commentary != null) {
			    		taskmodel.addRow(new Object[]{String.valueOf(t.taskId), t.description, t.final_date, t.duration, t.commentary, t.project, t.employee, t.status});
			    	} else {
			    		taskmodel.addRow(new Object[]{String.valueOf(t.taskId), t.description, t.final_date, t.duration, "", t.project, t.employee, t.status});
			    	}
		    	}
			}
		    
		    for (int i = 0; i < tasktable.getColumnCount(); i++) {
		    	tasktable.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
		    }
			
			tasks.setVisible(true);
			
			JButton back_button = new JButton("Back");
			back_button.setBounds(204, 345, 75, 28);
			taskpan.add(back_button);
			back_button.setBackground(new Color(135, 206, 235));
			back_button.setForeground(new Color(0, 0, 0));
			back_button.setFocusPainted(false);
			back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
			back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
			back_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tasks.dispose();
				}
			});
		} else if (btn_name == "Projects") {
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
		} else if (btn_name == "Employees") {
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
		}
		return;
	}
}
