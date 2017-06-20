package project;

import mainMenu.*;
import login.*;
import database.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.components.JSpinField;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class allPro {

	public JFrame frame;
	public static JTable empTable;
	private JTable table;
	private SqlConnection dao;

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
		dao = new SqlConnection();
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
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login window = new Login();
				window.frame.setVisible(true);
				frame.dispose();
			}
		});
		logout_button.setBounds(750, 0, 35, 29);
		panel.add(logout_button);
		logout_button.setIcon(new ImageIcon(MainMenu.class.getResource("/images/logout.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(0, 0, 784, 29);
		panel.add(panel_1);
		
		JLabel Title = new JLabel("Manage projects");
		Title.setBounds(300, 52, 199, 31);
		Border compound;
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		Title.setBorder(compound);
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
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
				frame.dispose();
			}
		});
		
		CustomProRenderer colouringTable = new CustomProRenderer();

		DefaultTableModel model = new DefaultTableModel(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
		}; 
		
		JTextField idField = new JTextField(15);
		idField.setBounds(160, 171, 199, 59);
		panel.add(idField);
		idField.setForeground(new Color(0, 0, 0));
		idField.setColumns(10);
		idField.setVisible(false);
	    
		JTextField descField = new JTextField(15);
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
		table.addMouseListener(new MouseAdapter() {
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
					}
				}
			}
		});
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
	    for (Project p: User.projects) {
		    model.addRow(new Object[]{String.valueOf(p.proId), p.name, p.description, p.duration, p.budget, p.getChefName(), p.departement});
	    }
	    
	    for (int i = 0; i < table.getColumnCount(); i++) {
		    table.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
	    }
	    
		JButton save_button = new JButton("New");
		save_button.setIcon(null);
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String description = descField.getText();
				String duration = String.valueOf((Integer)durationField.getValue()).toString();
				String budget = budgetField.getText();
				int chef = User.userId;
				int departement = User.departementId;
				if(!name.isEmpty() && !duration.isEmpty()) {
					if (!User.proAlreadyExists(name, departement)) {
						dao.addProject(name, description, duration, budget, chef, departement); 
						JOptionPane.showMessageDialog(null, "Project " + name + " created successfully!");
						dao.fetchPro();
						allPro pro = new allPro();
						pro.frame.setVisible(true);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Project already exists please press Update!");
					} 
				} else {
					JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
				}
				return;
			}
		});
		save_button.setBounds(70, 399, 120, 45);
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
				durationField.setValue(new Integer(0));
				budgetField.setText("");
			}
		});
		clear_button.setForeground(new Color(0, 0, 0));
		clear_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear_button.setFocusPainted(false);
		clear_button.setBackground(new Color(135, 206, 235));
		clear_button.setBounds(352, 408, 75, 28);
		panel.add(clear_button);
		
		JButton update_button = new JButton("Update");
		update_button.setIcon(null);
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = nameField.getText();
				String description = descField.getText();
				String duration = String.valueOf((Integer)durationField.getValue()).toString();
				String budget = budgetField.getText();
				int chef = User.userId;
				int departement = User.departementId;
				int id = Integer.parseInt(idField.getText());
				if(!name.isEmpty() && !duration.isEmpty()) {
					dao.updateProject(name, description, duration, budget, chef, departement, id); 
					JOptionPane.showMessageDialog(null, "Project " + name + " updated successfully!");
					dao.fetchPro();
					allPro pro = new allPro();
					pro.frame.setVisible(true);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Please fill in the form correctly!");
				}
				return;
			}
		});
		update_button.setForeground(Color.BLACK);
		update_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		update_button.setFocusPainted(false);
		update_button.setBackground(new Color(135, 206, 235));
		update_button.setBounds(200, 400, 120, 45);
		panel.add(update_button);
		
		JButton delete_button = new JButton("Delete");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				int id = Integer.parseInt(idField.getText());
				int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete project " + name + "?");
				if (action == 0) {
					dao.deleteProject(id); 
					JOptionPane.showMessageDialog(null, "Project " + name + " deleted successfully!");
					dao.fetchPro();
					allPro pro = new allPro();
					pro.frame.setVisible(true);
					frame.dispose();
				}
				return;
			}
		});
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
	}
}