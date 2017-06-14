package project;
import mainMenu.*;
import departement.Departement;
import login.*;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class searchProject {

	public JFrame frame;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchProject window = new searchProject();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public searchProject() {
		initialize();
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
		
		JLabel Title = new JLabel("Search for project by name");
		Title.setBounds(152, 45, 243, 31);
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
		descField.setBounds(174, 178, 199, 59);
		panel.add(descField);
		descField.setForeground(new Color(0, 0, 0));
		descField.setColumns(10);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(70, 147, 80, 17);
		panel.add(lblname);
		lblname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JTextField nameField = new JTextField(15);
		nameField.setBounds(174, 147, 199, 20);
		panel.add(nameField);
		nameField.setForeground(new Color(0, 0, 0));
		nameField.setColumns(10);
		
		JLabel lbldescription = new JLabel("Description");
		lbldescription.setBounds(27, 176, 123, 17);
		panel.add(lbldescription);
		lbldescription.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JTextField durationField = new JTextField(10);
		durationField.setForeground(Color.BLACK);
		durationField.setBounds(174, 248, 199, 20);
		panel.add(durationField);
		
		JLabel lblduration = new JLabel("Duration (in weeks)");
		lblduration.setHorizontalAlignment(SwingConstants.TRAILING);
		lblduration.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblduration.setBounds(10, 248, 140, 17);
		panel.add(lblduration);
		
		JLabel lblbudjet = new JLabel("Budget");
		lblbudjet.setHorizontalAlignment(SwingConstants.TRAILING);
		lblbudjet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblbudjet.setBounds(10, 279, 140, 17);
		panel.add(lblbudjet);
		
		JTextField budgetField = new JTextField(10);
		budgetField.setForeground(Color.BLACK);
		budgetField.setBounds(174, 279, 199, 20);
		panel.add(budgetField);
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(241, 366, 75, 28);
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
		
		JLabel lbldepartement = new JLabel("Departement");
		lbldepartement.setHorizontalAlignment(SwingConstants.TRAILING);
		lbldepartement.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbldepartement.setBounds(10, 307, 140, 17);
		panel.add(lbldepartement);
		
		JTextField departementField = new JTextField();
		departementField.setForeground(Color.BLACK);
		departementField.setBounds(174, 307, 199, 20);
		panel.add(departementField);
		
		JLabel lblchef = new JLabel("Chef");
		lblchef.setHorizontalAlignment(SwingConstants.TRAILING);
		lblchef.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblchef.setBounds(10, 335, 140, 17);
		panel.add(lblchef);
		
		JTextField chefField = new JTextField();
		chefField.setForeground(Color.BLACK);
		chefField.setBounds(174, 335, 199, 20);
		panel.add(chefField);
		
		searchField = new JTextField(10);
		searchField.setForeground(Color.BLACK);
		searchField.setBounds(174, 95, 199, 20);
		panel.add(searchField);
		
		JButton search_button = new JButton("");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchName = searchField.getText();
				for (Project p : Departement.projects) {
					if(p.name.equals(searchName)) {
						System.out.println(p.name);
						nameField.setText(p.name);
						descField.setText(p.description);
						durationField.setText(p.duration);
						budgetField.setText(p.budget);
						departementField.setText(p.departement);
						chefField.setText(p.getChefName());
					}
				}
				if (nameField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "This project doesn't exist!");
				}
			}
		});
		search_button.setIcon(new ImageIcon(searchProject.class.getResource("/images/search.png")));
		search_button.setBounds(383, 95, 20, 20);
		panel.add(search_button);
	}
}