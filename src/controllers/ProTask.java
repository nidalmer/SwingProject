package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import models.Task;
import models.User;
import views.allPro;

public class ProTask implements ActionListener {
	JTextField idField;
	
	public ProTask (JTextField idField) {
		this.idField = idField;
	}

	public void actionPerformed(ActionEvent e) {
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
		
		return;
	}
}
