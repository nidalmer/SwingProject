package views;

import controllers.*;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class Login {
	
	int attempts = 3;

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public Login() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Title = new JLabel("Welcome To Our Project");
		Title.setIcon(new ImageIcon(Login.class.getResource("/images/welcome.png")));
		Title.setBounds(56, 11, 405, 94);
		Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		frame.getContentPane().add(Title);
		
		usernameField = new RoundJTextField(15);
		usernameField.setForeground(new Color(0, 0, 0));
		usernameField.setBounds(161, 116, 199, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(89, 116, 62, 17);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(89, 144, 62, 17);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(lblPassword);
		
		passwordField = new RoundJPasswordField(15);
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setBounds(161, 144, 199, 20);
		frame.getContentPane().add(passwordField);
		
		JButton connect_button = new JButton("Connect");
		connect_button.setBackground(new Color(30, 144, 255));
		connect_button.setForeground(new Color(0, 0, 0));
		connect_button.setFocusPainted(false);
		connect_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		connect_button.setBounds(161, 204, 89, 23);
		connect_button.setOpaque(false);
		connect_button.setContentAreaFilled(false);
		connect_button.setBorder(new LineBorder(Color.BLUE));
		connect_button.addActionListener(new Connect(attempts, usernameField, passwordField));
		connect_button.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		frame.getContentPane().add(connect_button);
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.setBackground(new Color(135, 206, 235));
		cancel_button.setForeground(new Color(0, 0, 0));
		cancel_button.setFocusPainted(false);
		cancel_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cancel_button.setOpaque(false);
		cancel_button.setContentAreaFilled(false);
		cancel_button.setBorder(new LineBorder(Color.BLUE));
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		cancel_button.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		cancel_button.setBounds(271, 204, 89, 23);
		frame.getContentPane().add(cancel_button);
		
		JLabel bg = new JLabel();
		bg.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Untitled-1.png")));
		bg.setBounds(0, 0, 522, 321);
		frame.getContentPane().add(bg);
	}
}
