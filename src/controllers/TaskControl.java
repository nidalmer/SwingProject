package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import models.*;
import views.*;
import custom.*;

public class TaskControl extends MouseAdapter implements ActionListener {
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
	private SqlConnection dao = new SqlConnection();

	public TaskControl(JTable table, JTextField idField, JTextArea descField, JDateChooser dateField,
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
	
	public TaskControl(JTextArea descField, JSpinField durationField, JTextArea commentField, JComboBox<String> proField,
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
	
	public TaskControl(JTextField idField, JTextArea descField, JSpinField durationField, JTextArea commentField, JComboBox<String> proField,
			JComboBox<String> empField, JComboBox<String> natureField, JComboBox<String> statusField, JDateChooser dateField) {
		this.descField = descField;
		this.durationField = durationField;
		this.commentField = commentField;
		this.proField = proField;
		this.empField = empField;
		this.natureField = natureField;
		this.statusField = statusField;
		this.dateField = dateField;
		this.idField = idField;
	}
	
	public TaskControl(JTextField idField) {
		this.idField = idField;
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
	
	public void actionPerformed(ActionEvent e) {
		String btn_name = ((JButton) e.getSource()).getText();
		if(btn_name == "New") {
			String description = descField.getText();
			String duration = String.valueOf((Integer)durationField.getValue()).toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date = dateFormat.format(dateField.getDate());
			int project = User.proHasId(String.valueOf(proField.getSelectedItem()));
			int employee = User.empHasId(String.valueOf(empField.getSelectedItem()));
			if(!description.isEmpty() && !duration.isEmpty()) {
				dao.addTask(description, date, duration, project, employee); 
				JOptionPane.showMessageDialog(null, "Task created successfully!");
				dao.fetchTask();
				allTasks task = new allTasks();
				task.frame.setVisible(true);
		        Component component = (Component) e.getSource();
				JFrame target = (JFrame) SwingUtilities.getRoot(component);
				target.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
			}
		} else if(btn_name == "Clear") {
			dateField.setCalendar(null);
			descField.setText("");
			durationField.setValue(new Integer(0));
			commentField.setText("");
			proField.setSelectedIndex(0);
			empField.setSelectedIndex(0);
			statusField.setSelectedIndex(0);
			natureField.setSelectedIndex(0);
		} else if(btn_name == "Delete") {
			int id = Integer.parseInt(idField.getText());
			int action = JOptionPane.showConfirmDialog(null, "Do you really want to this task?");
			if (action == 0) {
				dao.deleteTask(id); 
				JOptionPane.showMessageDialog(null, "Task deleted successfully!");
				dao.fetchTask();
				allTasks task = new allTasks();
				task.frame.setVisible(true);
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
			String description = descField.getText();
			String duration = String.valueOf((Integer)durationField.getValue()).toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date = dateFormat.format(dateField.getDate());
			int project = User.proHasId(String.valueOf(proField.getSelectedItem()));
			int employee = User.empHasId(String.valueOf(empField.getSelectedItem()));
			String status = String.valueOf(statusField.getSelectedItem());
			String nature = String.valueOf(natureField.getSelectedItem());
			String comment = commentField.getText();
			int id = Integer.parseInt(idField.getText());
			if (User.chef || User.director) {
				if(!description.isEmpty() && !duration.isEmpty()) {
					dao.updateTask(description, date, duration, project, employee, id); 
					JOptionPane.showMessageDialog(null, "Task updates successfully!");
					dao.fetchTask();
					allTasks task = new allTasks();
					task.frame.setVisible(true);
					Component component = (Component) e.getSource();
					JFrame target = (JFrame) SwingUtilities.getRoot(component);
					target.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
				}
			} else {
				if(!comment.isEmpty()) {
					dao.updateTask2(status, nature, comment, id); 
					JOptionPane.showMessageDialog(null, "Task updates successfully!");
					dao.fetchTask();
					allTasks task = new allTasks();
					task.frame.setVisible(true);
					Component component = (Component) e.getSource();
					JFrame target = (JFrame) SwingUtilities.getRoot(component);
					target.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
				}
			}
		}
		return;
	}
}
