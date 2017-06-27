package views;

import controllers.*;
import models.*;

import javax.swing.*;
import java.awt.*;

import com.toedter.components.JSpinField;

public class allPro {

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
					allPro window = new allPro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public allPro() {
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
		logout_button.addActionListener(new Logout());
		logout_button.setBounds(750, 0, 35, 29);
		panel.add(logout_button);
		logout_button.setIcon(new ImageIcon(MainMenu.class.getResource("/images/logout.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(0, 0, 784, 29);
		panel.add(panel_1);
		
		JLabel Title = new JLabel();
		Title.setIcon(new ImageIcon(allPro.class.getResource("/images/pro.png")));
		Title.setBounds(218, 52, 394, 58);
		panel.add(Title);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 30, 784, 431);
		frame.getContentPane().add(panel_2);
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(637, 406, 75, 28);
		panel.add(back_button);
		back_button.setBackground(new Color(135, 206, 235));
		back_button.setForeground(new Color(0, 0, 0));
		back_button.setFocusPainted(false);
		back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		back_button.addActionListener(new BackMenu());
		
		CustomProRenderer colouringTable = new CustomProRenderer();

		CellTableModel model = new CellTableModel();
		
		JTextField idField = new JTextField(15);
		idField.setBounds(160, 171, 199, 59);
		panel.add(idField);
		idField.setForeground(new Color(0, 0, 0));
		idField.setColumns(10);
		idField.setVisible(false);
	    
		JTextArea descField = new JTextArea();
		descField.setBounds(160, 171, 199, 59);
		panel.add(descField);
		descField.setForeground(new Color(0, 0, 0));
		descField.setColumns(10);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(70, 140, 80, 17);
		panel.add(lblname);
		lblname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JTextField nameField = new JTextField(15);
		nameField.setBounds(160, 140, 199, 20);
		panel.add(nameField);
		nameField.setForeground(new Color(0, 0, 0));
		nameField.setColumns(10);
		
		JLabel lbldescription = new JLabel("Description");
		lbldescription.setBounds(27, 169, 123, 17);
		panel.add(lbldescription);
		lbldescription.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JSpinField durationField = new JSpinField();
		durationField.setForeground(Color.BLACK);
		durationField.setBounds(160, 241, 199, 20);
		panel.add(durationField);
		
		JLabel lblduration = new JLabel("Duration (in weeks)");
		lblduration.setHorizontalAlignment(SwingConstants.TRAILING);
		lblduration.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblduration.setBounds(10, 241, 140, 17);
		panel.add(lblduration);
		
		JLabel lblbudjet = new JLabel("Budget");
		lblbudjet.setHorizontalAlignment(SwingConstants.TRAILING);
		lblbudjet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblbudjet.setBounds(10, 272, 140, 17);
		panel.add(lblbudjet);
		
		JTextField budgetField = new JTextField(10);
		budgetField.setForeground(Color.BLACK);
		budgetField.setBounds(160, 272, 199, 20);
		panel.add(budgetField);
		
		JLabel lbldepartement = new JLabel("Departement");
		lbldepartement.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldepartement.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbldepartement.setBounds(10, 300, 140, 17);
		panel.add(lbldepartement);
		
		JTextField departementField = new JTextField(User.departement);
		departementField.setForeground(Color.BLACK);
		departementField.setBounds(160, 300, 199, 20);
		departementField.setEditable(false);
		panel.add(departementField);
		
		JLabel lblchef = new JLabel("Chef");
		lblchef.setHorizontalAlignment(SwingConstants.TRAILING);
		lblchef.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblchef.setBounds(10, 328, 140, 17);
		panel.add(lblchef);
		
		JTextField chefField = new JTextField(User.username);
		chefField.setForeground(Color.BLACK);
		chefField.setBounds(160, 328, 199, 20);
		chefField.setEditable(false);
		panel.add(chefField);
		
		table = new JTable(model);
		table.addMouseListener(new selectPro(table, idField, descField, nameField, durationField, budgetField, chefField, departementField));
		JScrollPane scroller = new JScrollPane(table);
		scroller.setBounds(369, 121, 405, 252);
		panel.add(scroller);

	    // Create a couple of columns 
	    model.addColumn("ID"); 
	    model.addColumn("Name"); 
	    model.addColumn("Description"); 
	    model.addColumn("Duration"); 
	    model.addColumn("Budget"); 
	    model.addColumn("Chef"); 
	    model.addColumn("Departement"); 
	    model.fillPro();
	    
	    for (int i = 0; i < table.getColumnCount(); i++) {
		    table.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
	    }
	    
		JButton save_button = new JButton("New");
		save_button.setIcon(null);
		save_button.addActionListener(new addPro(nameField, descField, durationField, budgetField));
		
		JButton task_button = new JButton("Tasks");
		task_button.setIcon(null);
		task_button.addActionListener(new ProTask(idField));
		task_button.setBounds(295, 399, 120, 45);
		panel.add(task_button);
		task_button.setBackground(new Color(135, 206, 235));
		task_button.setForeground(new Color(0, 0, 0));
		task_button.setFocusPainted(false);
		task_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		save_button.setBounds(46, 399, 120, 45);
		panel.add(save_button);
		save_button.setBackground(new Color(135, 206, 235));
		save_button.setForeground(new Color(0, 0, 0));
		save_button.setFocusPainted(false);
		save_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton clear_button = new JButton("Clear");
		clear_button.addActionListener(new clearPro(nameField, descField, durationField, budgetField));
		clear_button.setForeground(new Color(0, 0, 0));
		clear_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear_button.setFocusPainted(false);
		clear_button.setBackground(new Color(135, 206, 235));
		clear_button.setBounds(445, 407, 75, 28);
		panel.add(clear_button);
		
		JButton update_button = new JButton("Update");
		update_button.setIcon(null);
		update_button.addActionListener(new updatePro(idField, nameField, descField, durationField, budgetField));
		update_button.setForeground(Color.BLACK);
		update_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		update_button.setFocusPainted(false);
		update_button.setBackground(new Color(135, 206, 235));
		update_button.setBounds(171, 399, 120, 45);
		panel.add(update_button);
		
		JButton delete_button = new JButton("Delete");
		delete_button.addActionListener(new deletePro(idField));
		delete_button.setForeground(Color.BLACK);
		delete_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		delete_button.setFocusPainted(false);
		delete_button.setBackground(new Color(135, 206, 235));
		delete_button.setBounds(552, 407, 75, 28);
		panel.add(delete_button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(allPro.class.getResource("/images/bg.png")));
		label.setBounds(0, 29, 785, 432);
		panel.add(label);
	}
}