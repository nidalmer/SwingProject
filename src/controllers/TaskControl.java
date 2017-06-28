package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import models.*;
import views.*;

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
	
	public TaskControl() {}

	@Override
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
	
	@Override
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
			try {
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
			} catch  (Exception e1) {
				 JOptionPane.showMessageDialog(null, "Please select a task!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if(btn_name == "Update") {
			try {
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
			} catch  (Exception e1) {
				 JOptionPane.showMessageDialog(null, "Please select a task!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if (btn_name == "Import") {
			JFileChooser importfile = new JFileChooser();
			importfile.setCurrentDirectory(new File("user.dir"));
			importfile.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
			Component component = (Component) e.getSource();
			JFrame target = (JFrame) SwingUtilities.getRoot(component);
			int returnValue = importfile.showOpenDialog(target);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File taskfile = importfile.getSelectedFile();
				try {
					@SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(new FileReader(new File(taskfile.getPath())));
					ArrayList<String[]> elements = new ArrayList<String[]>();
		            String line = null;
		            while((line = br.readLine())!=null) {
		                String[] splitted = line.split(";");
		                elements.add(splitted);
		            }
		            for(int i=0; i<elements.size(); i++) {
		            	if (!User.taskAlreadyExists(elements.get(i)[0], Integer.parseInt(elements.get(i)[4]))) {
							dao.addTask(elements.get(i)[0], elements.get(i)[1], elements.get(i)[2], Integer.parseInt(elements.get(i)[3]), Integer.parseInt(elements.get(i)[4])); 
							JOptionPane.showMessageDialog(null, "Project " + elements.get(i)[0] + " created successfully!");
							dao.fetchPro();
							allTasks task = new allTasks();
							task.frame.setVisible(true);
						    Component component1 = (Component) e.getSource();
							JFrame target1 = (JFrame) SwingUtilities.getRoot(component1);
							target1.dispose();
						}
		            }
		            JOptionPane.showMessageDialog(null, "Projects imported successfully!");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
			} else {
				JOptionPane.showMessageDialog(null, "No file chosen!");
			}
			
		}
		return;
	}
}
