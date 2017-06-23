package mainMenu;

import login.*;
import project.*;
import task.*;
import employee.*;
import departement.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 34, 434, 227);
		panel.add(panel_2);
		
		JButton btnAddProject = new JButton("Add Project");
		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProject project = new addProject();
				project.frame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(btnAddProject);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTask task = new addTask();
				task.frame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(btnAddTask);
		
		JButton btnSearchPro = new JButton("Search for project by name");
		btnSearchPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProject searchP = new searchProject();
				searchP.frame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(btnSearchPro);
		
		JButton btnAllEmp = new JButton("Display all employees");
		btnAllEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allEmp empT = new allEmp();
				empT.frame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(btnAllEmp);
		
		JButton btnAllPro = new JButton("Display all projects");
		btnAllPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allPro proT = new allPro();
				proT.frame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(btnAllPro);
		
		JButton btnAllTasks = new JButton("Display all tasks");
		btnAllTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allTasks taskT = new allTasks();
				taskT.frame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(btnAllTasks);
		
		JButton btnAllDep = new JButton("Display all departements");
		btnAllDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allDep depT = new allDep();
				depT.frame.setVisible(true);
				dispose();
			}
		});
		panel_2.add(btnAllDep);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setBounds(10, 9, 46, 14);
		panel.add(lblName);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(46, 9, 74, 14);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblUser.setText(String.valueOf(User.username).toString());
		panel.add(lblUser);
		
		JButton logout_button = new JButton("");
		logout_button.setIcon(new ImageIcon(MainMenu.class.getResource("/images/logout.png")));
		logout_button.setBounds(397, 0, 37, 34);
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login window = new Login();
				window.frame.setVisible(true);
				dispose();
			}
		});
		panel.add(logout_button);
		
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
		if (User.director) {
			lblUserRank.setText("(Director)");
		} else if (User.chef) {
			lblUserRank.setText("(Chef)");
		} else if (!User.director && !User.chef) {
			lblUserRank.setText("(Employee)");
		}
		panel.add(lblUserRank);
		
		JPanel ProfileBar = new JPanel();
		ProfileBar.setForeground(new Color(173, 216, 230));
		ProfileBar.setBackground(new Color(173, 216, 230));
		ProfileBar.setBounds(0, 0, 434, 34);
		panel.add(ProfileBar);
		
	}
}
