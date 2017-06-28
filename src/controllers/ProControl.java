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
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

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
	JComboBox<String> validField;
	private SqlConnection dao = new SqlConnection();
	
	public ProControl(JTable table, JTextField idField, JTextArea descField, JTextField nameField,
			JSpinField durationField, JTextField budgetField, JTextField chefField, JTextField departementField, JComboBox<String> validField) {
		this.table = table;
		this.idField = idField;
		this.descField = descField;
		this.nameField = nameField;
		this.durationField = durationField;
		this.budgetField = budgetField;
		this.chefField = chefField;
		this.departementField = departementField;
		this.validField = validField;
	}
	
	public ProControl(JTextField nameField, JTextArea descField, JSpinField durationField, JTextField budgetField, JComboBox<String> validField) {
		this.nameField = nameField;
		this.descField = descField;
		this.durationField = durationField;
		this.budgetField = budgetField;
		this.validField = validField;
	}
	
	public ProControl(JTextField idField, JTextField nameField, JTextArea descField, JSpinField durationField, JTextField budgetField, JComboBox<String> validField) {
		this.nameField = nameField;
		this.descField = descField;
		this.durationField = durationField;
		this.budgetField = budgetField;
		this.idField = idField;
		this.validField = validField;
	}
	
	public ProControl(JTextField idField) {
		this.idField = idField;
	}
	
	public ProControl() {}

	@Override
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
				for (int i = 0; i < validField.getItemCount(); i++) {
					String item = validField.getItemAt(i);
					String stat;
					if (p.valid) {
						stat = "Approved";
					} else {
						stat = "Unapproved";
					}
					if (stat.equals(item) ) {
						validField.setSelectedIndex(i);
						break;
					}
				}
				if (p.proHasPercentage() != 100) {
					validField.setEnabled(false);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String btn_name = ((JButton) e.getSource()).getText();
		if(btn_name == "New") {
			String name = nameField.getText();
			String description = descField.getText();
			String duration = String.valueOf((Integer)durationField.getValue()).toString();
			String budget = budgetField.getText();
			int chef = User.userId;
			int departement = User.departementId;
			validField.setEnabled(false);
			String valid = "Unapproved";
			if(!name.isEmpty() && !duration.isEmpty()) {
				if (!User.proAlreadyExists(name, departement)) {
					dao.addProject(name, description, duration, budget, chef, departement, valid); 
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
			validField.setSelectedIndex(1);
		} else if(btn_name == "Delete") {
			try {
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
			} catch  (Exception e1) {
				 JOptionPane.showMessageDialog(null, "Please select a project!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if(btn_name == "Tasks") {
			try {
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
				back_button.setIcon(new ImageIcon(allPro.class.getResource("/images/back.png")));
				back_button.setBounds(204, 345, 105, 45);
				taskpan.add(back_button);
				back_button.setBackground(new Color(135, 206, 235));
				back_button.setForeground(new Color(0, 0, 0));
				back_button.setFocusPainted(false);
				back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
				back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
				back_button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						tasks.dispose();
					}
				});
			} catch  (Exception e1) {
				 JOptionPane.showMessageDialog(null, "Please select a project!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if(btn_name == "Update") {
			try {
				String name = nameField.getText();
				String description = descField.getText();
				String duration = String.valueOf((Integer)durationField.getValue()).toString();
				String budget = budgetField.getText();
				int chef = User.userId;
				int departement = User.departementId;
				int id = Integer.parseInt(idField.getText());
				String valid = String.valueOf(validField.getSelectedItem());
				if(!name.isEmpty() && !duration.isEmpty()) {
					dao.updateProject(name, description, duration, budget, chef, departement, valid, id); 
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
			} catch  (Exception e1) {
				 JOptionPane.showMessageDialog(null, "Please select a project!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if (btn_name == "Import") {
			JFileChooser importfile = new JFileChooser();
			importfile.setCurrentDirectory(new File("user.dir"));
			importfile.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
			Component component = (Component) e.getSource();
			JFrame target = (JFrame) SwingUtilities.getRoot(component);
			int returnValue = importfile.showOpenDialog(target);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File profile = importfile.getSelectedFile();
				try {
					@SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(new FileReader(new File(profile.getPath())));
					ArrayList<String[]> elements = new ArrayList<String[]>();
		            String line = null;
		            while((line = br.readLine())!=null) {
		                String[] splitted = line.split(";");
		                elements.add(splitted);
		            }
		            for(int i=0; i<elements.size(); i++) {
		            	if (!User.proAlreadyExists(elements.get(i)[0], Integer.parseInt(elements.get(i)[5]))) {
							dao.addProject(elements.get(i)[0], elements.get(i)[1], elements.get(i)[2], elements.get(i)[3], Integer.parseInt(elements.get(i)[4]), Integer.parseInt(elements.get(i)[5]), elements.get(i)[6]); 
							JOptionPane.showMessageDialog(null, "Project " + elements.get(i)[0] + " created successfully!");
							dao.fetchPro();
							allPro pro = new allPro();
							pro.frame.setVisible(true);
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
			
		} else if(btn_name == "Progress") {
			try {
				int approvedcnt = 0;
				int unapprovedcnt = 0;
				int waitingcnt = 0;
				int progresscnt = 0;
				int id = Integer.parseInt(idField.getText());
				for (Project p: User.projects) {
					if (p.proId == id) {
						approvedcnt = p.ApprovedCount();
						unapprovedcnt = p.unapprovedCount();
						waitingcnt = p.waitingCount();
						progresscnt = p.progressCount();
					}
				}
				DefaultPieDataset dataset = new DefaultPieDataset();
				dataset.setValue("Approved", approvedcnt);
				dataset.setValue("Unapproved", unapprovedcnt);
				dataset.setValue("Waiting", waitingcnt);
				dataset.setValue("In progress", progresscnt);
				JFreeChart chart = ChartFactory.createPieChart3D(
		                "Project progress", // chart title                   
		                dataset, // data 
		                true, // include legend                   
		                true,
		                false);
				
				PiePlot3D plot = (PiePlot3D) chart.getPlot();
				plot.setSectionPaint("Approved", new Color(214, 245, 214));
				plot.setSectionPaint("Unapproved", new Color(255, 204, 204));
				plot.setSectionPaint("Waiting", new Color(255, 255, 153));
				plot.setSectionPaint("In progress", new Color(204, 204, 255));
		        plot.setStartAngle(270);
		        plot.setForegroundAlpha(0.60f);
		        plot.setInteriorGap(0.02);
		        // create chart panel the add it to swing panel in  jframe
		        ChartFrame frame = new ChartFrame ("Progress", chart);
		        frame.setVisible(true);
		        frame.setSize(500, 400);
			} catch  (Exception e1) {
				 JOptionPane.showMessageDialog(null, "Please select a project!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		return;
	}
}