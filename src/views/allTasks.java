package views;

import controllers.*;
import models.*;
import custom.*;

import javax.swing.*;
import java.awt.*;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class allTasks {

	public JFrame frame;
	public static JTable empTable;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					allTasks window = new allTasks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public allTasks() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setBounds(10, 9, 46, 14);
		panel.add(lblName);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(46, 9, 74, 14);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblUser.setText(String.valueOf(User.username).toString());
		panel.add(lblUser);
		
		JLabel lblDepartement = new JLabel("Departement :");
		lblDepartement.setBounds(130, 9, 87, 14);
		panel.add(lblDepartement);
		
		JLabel lblUserDep = new JLabel("UserDep");
		lblUserDep.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserDep.setBounds(218, 9, 87, 14);
		lblUserDep.setText(String.valueOf(User.departement).toString());
		panel.add(lblUserDep);
		

		JLabel lblUserRank = new JLabel("Rank");
		lblUserRank.setBounds(300, 9, 87, 14);
		lblUserRank.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblUserRank);
	
		if (User.director) {
			lblUserRank.setText("(Director)");
		} else if (User.chef) {
			lblUserRank.setText("(Chef)");
		} else {
			lblUserRank.setText("(Employee)");
		}
		
		JButton logout_button = new JButton("");
		logout_button.addActionListener(new Connect());
		logout_button.setBounds(750, 0, 35, 29);
		panel.add(logout_button);
		logout_button.setIcon(new ImageIcon(MainMenu.class.getResource("/images/logout.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(0, 0, 784, 29);
		panel.add(panel_1);
		
		JLabel Title = new JLabel("");
		Title.setIcon(new ImageIcon(allTasks.class.getResource("/images/task.png")));
		Title.setBounds(203, 36, 409, 55);
		panel.add(Title);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 30, 784, 431);
		frame.getContentPane().add(panel_2);
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(558, 406, 75, 28);
		panel.add(back_button);
		back_button.setBackground(new Color(135, 206, 235));
		back_button.setForeground(new Color(0, 0, 0));
		back_button.setFocusPainted(false);
		back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		back_button.addActionListener(new BackMenu());
		
		JTextArea descField = new JTextArea();
		descField.setBounds(162, 96, 199, 59);
		panel.add(descField);
		descField.setForeground(new Color(0, 0, 0));
		descField.setColumns(10);
		
		JTextField idField = new JTextField(15);
		idField.setBounds(162, 94, 199, 59);
		panel.add(idField);
		idField.setForeground(new Color(0, 0, 0));
		idField.setColumns(10);
		idField.setVisible(false);
		
		JLabel lbldate = new JLabel("Final Date");
		lbldate.setBounds(87, 166, 65, 17);
		panel.add(lbldate);
		lbldate.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JDateChooser dateField = new JDateChooser();
		dateField.setDateFormatString("dd/MM/yy");
		dateField.setBounds(162, 166, 199, 20);
		panel.add(dateField);
		dateField.setForeground(new Color(0, 0, 0));
		
		JLabel lbldescription = new JLabel("Description");
		lbldescription.setBounds(27, 94, 123, 17);
		panel.add(lbldescription);
		lbldescription.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JSpinField durationField = new JSpinField();
		durationField.setForeground(Color.BLACK);
		durationField.setBounds(162, 197, 199, 20);
		panel.add(durationField);
		
		JLabel lblduration = new JLabel("Duration (in days)");
		lblduration.setHorizontalAlignment(SwingConstants.TRAILING);
		lblduration.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblduration.setBounds(12, 194, 140, 17);
		panel.add(lblduration);
		
		JLabel lblnature = new JLabel("Nature of comment");
		lblnature.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnature.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblnature.setBounds(12, 321, 140, 17);
		panel.add(lblnature);
		
		JTextArea commentField = new JTextArea();
		commentField.setForeground(Color.BLACK);
		commentField.setBounds(162, 349, 199, 39);
		panel.add(commentField);
		
		JLabel lblpro = new JLabel("Project");
		lblpro.setHorizontalAlignment(SwingConstants.TRAILING);
		lblpro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblpro.setBounds(10, 228, 140, 17);
		panel.add(lblpro);
		
		JComboBox<String> proField = new JComboBox<String>();
		for (Project p: User.projects) {
			proField.addItem(p.name);
		}
		proField.setForeground(Color.BLACK);
		proField.setBounds(162, 228, 199, 20);
		panel.add(proField);
		
		JLabel lblemp = new JLabel("Employee");
		lblemp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblemp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblemp.setBounds(12, 262, 140, 17);
		panel.add(lblemp);
		
		JComboBox<String> empField = new JComboBox<String>();
		for (Employee e: User.employees) {
			empField.addItem(e.username);
		}
		empField.setForeground(Color.BLACK);
		empField.setBounds(162, 259, 199, 20);
		panel.add(empField);
		
	    JComboBox<String> natureField = new JComboBox<String>();
		natureField.setBounds(174, 255, 200, 20);
		natureField.addItem("Urgent");
		natureField.addItem("Daily");
		natureField.addItem("Info");
		panel.add(natureField);
		natureField.setForeground(Color.BLACK);
		natureField.setBounds(162, 321, 199, 20);
		panel.add(natureField);
		
		JLabel lblcomment = new JLabel("Comment");
		lblcomment.setHorizontalAlignment(SwingConstants.TRAILING);
		lblcomment.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblcomment.setBounds(12, 352, 140, 17);
		panel.add(lblcomment);
		
		JComboBox<String> statusField = new JComboBox<String>();
		statusField.setBounds(162, 290, 199, 20);
		statusField.addItem("In progress");
		statusField.addItem("Waiting");
		statusField.addItem("Approved");
		statusField.addItem("Unapproved");
		panel.add(statusField);
		
		JLabel lblstatus = new JLabel("Status");
		lblstatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblstatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblstatus.setBounds(10, 290, 140, 17);
		panel.add(lblstatus);
		
		CustomTaskRenderer colouringTable = new CustomTaskRenderer();

		CellTableModel model = new CellTableModel();
		
		table = new JTable(model);
		table.addMouseListener(new TaskControl(table, idField, descField, dateField, durationField, commentField, proField, empField, statusField, natureField));
		JScrollPane scroller = new JScrollPane(table);
		scroller.setBounds(369, 94, 405, 294);
		panel.add(scroller);

	    // Create a couple of columns 
	    model.addColumn("ID");  
	    model.addColumn("Description");  
	    model.addColumn("Final date"); 
	    model.addColumn("Duration"); 
	    model.addColumn("Comment"); 
	    model.addColumn("Project"); 
	    model.addColumn("Employee");
	    model.addColumn("Status");
	    table.removeColumn( table.getColumnModel().getColumn(7) );
	    model.fillTask();
	    
	    for (int i = 0; i < table.getColumnCount(); i++) {
		    table.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
	    }
	    
	    
		JButton save_button = new JButton("New");
		save_button.setIcon(null);
		save_button.addActionListener(new TaskControl(descField, durationField, commentField, proField, empField, natureField, statusField, dateField));
		save_button.setBounds(70, 399, 120, 45);
		panel.add(save_button);
		save_button.setBackground(new Color(135, 206, 235));
		save_button.setForeground(new Color(0, 0, 0));
		save_button.setFocusPainted(false);
		save_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton clear_button = new JButton("Clear");
		clear_button.addActionListener(new TaskControl(descField, durationField, commentField, proField, empField, natureField, statusField, dateField));
		clear_button.setForeground(new Color(0, 0, 0));
		clear_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear_button.setFocusPainted(false);
		clear_button.setBackground(new Color(135, 206, 235));
		clear_button.setBounds(352, 408, 75, 28);
		panel.add(clear_button);
		
		JButton update_button = new JButton("Update");
		update_button.setIcon(null);
		update_button.addActionListener(new TaskControl(idField, descField, durationField, commentField, proField, empField, natureField, statusField, dateField));
		update_button.setForeground(Color.BLACK);
		update_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		update_button.setFocusPainted(false);
		update_button.setBackground(new Color(135, 206, 235));
		update_button.setBounds(200, 400, 120, 45);
		panel.add(update_button);
		
		JButton delete_button = new JButton("Delete");
		delete_button.addActionListener(new TaskControl(idField));
		delete_button.setForeground(Color.BLACK);
		delete_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		delete_button.setFocusPainted(false);
		delete_button.setBackground(new Color(135, 206, 235));
		delete_button.setBounds(454, 407, 75, 28);
		panel.add(delete_button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(allPro.class.getResource("/images/bg.png")));
		label.setBounds(0, 29, 785, 432);
		panel.add(label);
		
		if(User.chef || User.director) {
			statusField.setEnabled(false);
			natureField.setEnabled(false);
			commentField.setEnabled(false);
		} else {
			descField.setEditable(false);
			dateField.setEnabled(false);
			durationField.setEnabled(false);
			proField.setEnabled(false);
			empField.setEnabled(false);
		}
	}
}