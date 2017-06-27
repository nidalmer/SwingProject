package views;

import controllers.*;
import models.*;

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
		panel.setBounds(0, 0, 442, 269);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setBounds(10, 9, 46, 14);
		lblName.setForeground(new Color(0, 0, 0));
		panel.add(lblName);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(46, 9, 74, 14);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblUser.setText(String.valueOf(User.username).toString());
		panel.add(lblUser);
		
		JButton logout_button = new JButton("");
		logout_button.setBounds(397, 0, 37, 34);
		logout_button.setIcon(new ImageIcon(MainMenu.class.getResource("/images/logout.png")));
		logout_button.addActionListener(new Logout());
		panel.add(logout_button);
		
		JLabel lblDepartement = new JLabel("Departement :");
		lblDepartement.setBounds(130, 9, 87, 14);
		panel.add(lblDepartement);
		
		JLabel lblUserDep = new JLabel("UserDep");
		lblUserDep.setBounds(218, 9, 87, 14);
		lblUserDep.setHorizontalAlignment(SwingConstants.CENTER);
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
		ProfileBar.setBounds(0, 0, 442, 34);
		ProfileBar.setForeground(new Color(173, 216, 230));
		ProfileBar.setBackground(new Color(173, 216, 230));
		panel.add(ProfileBar);
		
		JButton btnAllPro = new JButton("Manage Projects");
		btnAllPro.setBounds(20, 56, 197, 88);
		panel.add(btnAllPro);
		btnAllPro.setIcon(new ImageIcon(MainMenu.class.getResource("/images/schedule.png")));
		
		JButton btnAllEmp = new JButton("Manage employees");
		btnAllEmp.setBounds(232, 56, 185, 88);
		panel.add(btnAllEmp);
		btnAllEmp.setIcon(new ImageIcon(MainMenu.class.getResource("/images/networking.png")));
		
		JButton btnAllDep = new JButton("Manage Departements");
		btnAllDep.setBounds(232, 155, 185, 88);
		panel.add(btnAllDep);
		btnAllDep.setIcon(new ImageIcon(MainMenu.class.getResource("/images/city-hall.png")));
		
		JButton btnAllTasks = new JButton("Manage Tasks");
		btnAllTasks.setBounds(20, 155, 197, 88);
		panel.add(btnAllTasks);
		btnAllTasks.setIcon(new ImageIcon(MainMenu.class.getResource("/images/time-management.png")));
		btnAllTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allTasks taskT = new allTasks();
				taskT.frame.setVisible(true);
				dispose();
			}
		});
		btnAllDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allDep depT = new allDep();
				depT.frame.setVisible(true);
				dispose();
			}
		});
		btnAllEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allEmp empT = new allEmp();
				empT.frame.setVisible(true);
				dispose();
			}
		});
		btnAllPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allPro proT = new allPro();
				proT.frame.setVisible(true);
				dispose();
			}
		});
		
		if(!User.director) {
			btnAllDep.setEnabled(false);
			if (!User.chef) {
				btnAllEmp.setEnabled(false);
				btnAllPro.setEnabled(false);
			}
		}
	}
}
