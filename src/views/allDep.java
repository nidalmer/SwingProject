package views;

import controllers.*;
import models.*;

import javax.swing.*;
import java.awt.*;

public class allDep {

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
					allDep window = new allDep();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public allDep () {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 466);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 514, 427);
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
		logout_button.setBounds(479, 0, 35, 29);
		panel.add(logout_button);
		logout_button.setIcon(new ImageIcon(MainMenu.class.getResource("/images/logout.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(0, 0, 514, 29);
		panel.add(panel_1);
		
		JLabel Title = new JLabel("");
		Title.setIcon(new ImageIcon(allDep.class.getResource("/images/dep.png")));
		Title.setBounds(58, 38, 401, 59);
		panel.add(Title);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 30, 514, 397);
		frame.getContentPane().add(panel_2);

		JTextField idField = new JTextField(15);
		idField.setBounds(160, 171, 199, 59);
		panel.add(idField);
		idField.setForeground(new Color(0, 0, 0));
		idField.setColumns(10);
		idField.setVisible(false);
			
		CellTableModel model = new CellTableModel();

		table = new JTable(model);
		table.addMouseListener(new selectDep(table, idField));
		JScrollPane scroller = new JScrollPane(table);
		scroller.setBounds(38, 108, 436, 255);
		panel.add(scroller);
		
	    model.addColumn("ID"); 
	    model.addColumn("Name"); 
	    model.addColumn("Chef");
	    model.fillDep();
	    
		JButton task_button = new JButton("Tasks");
		task_button.setIcon(null);
		task_button.setBounds(384, 374, 75, 28);
		task_button.setBackground(new Color(135, 206, 235));
		task_button.setForeground(new Color(0, 0, 0));
		task_button.setFocusPainted(false);
		task_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		task_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		panel.add(task_button);
		task_button.addActionListener(new DepTask(idField));
		
		JButton project_button = new JButton("Projects");
		project_button.setIcon(null);
		project_button.setBounds(142, 374, 100, 28);
		project_button.setBackground(new Color(135, 206, 235));
		project_button.setForeground(new Color(0, 0, 0));
		project_button.setFocusPainted(false);
		project_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		project_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		panel.add(project_button);
		project_button.addActionListener(new DepPro(idField));
		
		JButton employee_button = new JButton("Employees");
		employee_button.setIcon(null);
		employee_button.setBounds(252, 374, 122, 28);
		employee_button.setBackground(new Color(135, 206, 235));
		employee_button.setForeground(new Color(0, 0, 0));
		employee_button.setFocusPainted(false);
		employee_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		employee_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		panel.add(employee_button);
		employee_button.addActionListener(new DepEmp(idField));
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(58, 374, 75, 28);
		panel.add(back_button);
		back_button.setBackground(new Color(135, 206, 235));
		back_button.setForeground(new Color(0, 0, 0));
		back_button.setFocusPainted(false);
		back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		back_button.addActionListener(new BackMenu());

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(allPro.class.getResource("/images/bg.png")));
		label.setBounds(0, 29, 514, 398);
		panel.add(label);
	}
}