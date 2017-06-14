package project;

import mainMenu.*;
import database.*;
import login.*;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class addProject {

	public JFrame frame;
	private SqlConnection dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addProject window = new addProject();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public addProject() {
		initialize();
		dao = new SqlConnection();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		logout_button.setBounds(479, 0, 35, 29);
		panel.add(logout_button);
		logout_button.setIcon(new ImageIcon(MainMenu.class.getResource("/images/logout.png")));
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login window = new Login();
				window.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 235));
		panel_1.setBounds(0, 0, 514, 29);
		panel.add(panel_1);
		
		JLabel Title = new JLabel("Add project");
		Title.setBounds(174, 40, 199, 31);
		Border compound;
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		Title.setBorder(compound);
		panel.add(Title);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 30, 514, 323);
		frame.getContentPane().add(panel_2);
		
		JTextField descField = new JTextField(15);
		descField.setBounds(174, 132, 199, 59);
		panel.add(descField);
		descField.setForeground(new Color(0, 0, 0));
		descField.setColumns(10);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(70, 101, 80, 17);
		panel.add(lblname);
		lblname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JTextField nameField = new JTextField(15);
		nameField.setBounds(174, 101, 199, 20);
		panel.add(nameField);
		nameField.setForeground(new Color(0, 0, 0));
		nameField.setColumns(10);
		
		JLabel lbldescription = new JLabel("Description");
		lbldescription.setBounds(27, 130, 123, 17);
		panel.add(lbldescription);
		lbldescription.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JTextField durationField = new JTextField(10);
		durationField.setForeground(Color.BLACK);
		durationField.setBounds(174, 202, 199, 20);
		panel.add(durationField);
		
		JLabel lblduration = new JLabel("Duration (in weeks)");
		lblduration.setHorizontalAlignment(SwingConstants.TRAILING);
		lblduration.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblduration.setBounds(10, 202, 140, 17);
		panel.add(lblduration);
		
		JLabel lblbudjet = new JLabel("Budget");
		lblbudjet.setHorizontalAlignment(SwingConstants.TRAILING);
		lblbudjet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblbudjet.setBounds(10, 233, 140, 17);
		panel.add(lblbudjet);
		
		JTextField budgetField = new JTextField(10);
		budgetField.setForeground(Color.BLACK);
		budgetField.setBounds(174, 233, 199, 20);
		panel.add(budgetField);
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(155, 366, 75, 28);
		panel.add(back_button);
		back_button.setBackground(new Color(135, 206, 235));
		back_button.setForeground(new Color(0, 0, 0));
		back_button.setFocusPainted(false);
		back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
				frame.dispose();
			}
		});
		back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		
		JButton save_button = new JButton("Save");
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String description = descField.getText();
				String duration = durationField.getText();
				String budget = budgetField.getText();
				int chef = User.userId;
				int departement = User.departementId;
				if(name != null && duration != null) {
					if ( dao.addProject(name, description, duration, budget, chef, departement) ) {
						JOptionPane.showMessageDialog(null, "Project " + name + " created successfully!");
						MainMenu menu = new MainMenu();
						menu.setVisible(true);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
					}
					return;
				}
			}
		});
		save_button.setBounds(324, 366, 75, 28);
		panel.add(save_button);
		save_button.setBackground(new Color(135, 206, 235));
		save_button.setForeground(new Color(0, 0, 0));
		save_button.setFocusPainted(false);
		save_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton clear_button = new JButton("Clear");
		clear_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameField.setText("");
				descField.setText("");
				durationField.setText("");
				budgetField.setText("");
			}
		});
		clear_button.setForeground(new Color(0, 0, 0));
		clear_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear_button.setFocusPainted(false);
		clear_button.setBackground(new Color(135, 206, 235));
		clear_button.setBounds(240, 367, 75, 28);
		panel.add(clear_button);
		
		JLabel lbldepartement = new JLabel("Departement");
		lbldepartement.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldepartement.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbldepartement.setBounds(10, 261, 140, 17);
		panel.add(lbldepartement);
		
		JTextField departementField = new JTextField(User.departement);
		departementField.setForeground(Color.BLACK);
		departementField.setBounds(174, 261, 199, 20);
		departementField.setEditable(false);
		panel.add(departementField);
		
		JLabel lblchef = new JLabel("Chef");
		lblchef.setHorizontalAlignment(SwingConstants.TRAILING);
		lblchef.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblchef.setBounds(10, 289, 140, 17);
		panel.add(lblchef);
		
		JTextField chefField = new JTextField(User.username);
		chefField.setForeground(Color.BLACK);
		chefField.setBounds(174, 289, 199, 20);
		chefField.setEditable(false);
		panel.add(chefField);
	}
}
