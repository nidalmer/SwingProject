package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.toedter.components.JSpinField;

import models.*;
import views.*;
import custom.*;

public class ProControl extends MouseAdapter implements ActionListener {
	JTable table;
	JTextField idField;
	JTextArea descField;
	JTextField nameField;
	JSpinField durationField;
	JTextField budgetField;
	JTextField chefField;
	JTextField departementField;
	private SqlConnection dao = new SqlConnection();
	
	public ProControl(JTable table, JTextField idField, JTextArea descField, JTextField nameField,
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
	
	public ProControl(JTextField nameField, JTextArea descField, JSpinField durationField, JTextField budgetField) {
		this.nameField = nameField;
		this.descField = descField;
		this.durationField = durationField;
		this.budgetField = budgetField;
	}
	
	public ProControl(JTextField idField, JTextField nameField, JTextArea descField, JSpinField durationField, JTextField budgetField) {
		this.nameField = nameField;
		this.descField = descField;
		this.durationField = durationField;
		this.budgetField = budgetField;
		this.idField = idField;
	}
	
	public ProControl(JTextField idField) {
		this.idField = idField;
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
	
	public void actionPerformed(ActionEvent e) {
		String btn_name = ((JButton) e.getSource()).getText();
		if(btn_name == "New") {
			String name = nameField.getText();
			String description = descField.getText();
			String duration = String.valueOf((Integer)durationField.getValue()).toString();
			String budget = budgetField.getText();
			int chef = User.userId;
			int departement = User.departementId;
			if(!name.isEmpty() && !duration.isEmpty()) {
				if (!User.proAlreadyExists(name, departement)) {
					dao.addProject(name, description, duration, budget, chef, departement); 
					JOptionPane.showMessageDialog(null, "Project " + name + " created successfully!");
					dao.fetchPro();
					allPro pro = new allPro();
					pro.frame.setVisible(true);
			        Component component = (Component) e.getSource();
					JFrame target = (JFrame) SwingUtilities.getRoot(component);
					target.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Project already exists please press Update!");
				} 
			} else {
				JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
			}
		} else if(btn_name == "Clear") {
			nameField.setText("");
			descField.setText("");
			durationField.setValue(new Integer(0));
			budgetField.setText("");
		} else if(btn_name == "Delete") {
			int id = Integer.parseInt(idField.getText());
			int action = JOptionPane.showConfirmDialog(null, "Do you really want to this task?");
			if (action == 0) {
				dao.deleteTask(id); 
				JOptionPane.showMessageDialog(null, "Task deleted successfully!");
				dao.fetchTask();
				allPro pro = new allPro();
				pro.frame.setVisible(true);
				Component component = (Component) e.getSource();
				JFrame target = (JFrame) SwingUtilities.getRoot(component);
				target.dispose();
			}
		} else if(btn_name == "Tasks") {
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
		    	if(t.projectId == id)  {
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
		} else if(btn_name == "Update") {
			String name = nameField.getText();
			String description = descField.getText();
			String duration = String.valueOf((Integer)durationField.getValue()).toString();
			String budget = budgetField.getText();
			int chef = User.userId;
			int departement = User.departementId;
			int id = Integer.parseInt(idField.getText());
			if(!name.isEmpty() && !duration.isEmpty()) {
				dao.updateProject(name, description, duration, budget, chef, departement, id); 
				JOptionPane.showMessageDialog(null, "Project " + name + " updated successfully!");
				dao.fetchPro();
				allPro pro = new allPro();
				pro.frame.setVisible(true);
				Component component = (Component) e.getSource();
				JFrame target = (JFrame) SwingUtilities.getRoot(component);
				target.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
			}
		}
		return;
	}
}
