package departement;

import mainMenu.*;
import project.*;
import task.*;
import login.*;
import employee.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

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
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login window = new Login();
				window.frame.setVisible(true);
				frame.dispose();
			}
		});
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
			


		DefaultTableModel model = new DefaultTableModel(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
			
			
		}; 

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int selectedId = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
				for (Departement d: User.departements) {
					if (d.depId == selectedId) {
						idField.setText(String.valueOf(d.depId).toString());
					}
				}
			}
		});
		JScrollPane scroller = new JScrollPane(table);
		scroller.setBounds(38, 108, 436, 255);
		panel.add(scroller);
		
	    model.addColumn("ID"); 
	    model.addColumn("Name"); 
	    model.addColumn("Chef");
	    for (Departement d : User.departements) {
		    model.addRow(new Object[]{String.valueOf(d.depId), d.name, d.chef});
	    }
	    
	    
		JButton task_button = new JButton("Tasks");
		task_button.setIcon(null);
		task_button.setBounds(384, 374, 75, 28);
		task_button.setBackground(new Color(135, 206, 235));
		task_button.setForeground(new Color(0, 0, 0));
		task_button.setFocusPainted(false);
		task_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		task_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		panel.add(task_button);
		task_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {JFrame tasks = new JFrame();
			tasks.setBounds(100, 100, 510, 425);
			tasks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tasks.getContentPane().setLayout(null);
	
			int id = Integer.parseInt(idField.getText());
			
			JPanel taskpan = new JPanel();
			taskpan.setBounds(0, 0, 494, 391);
			tasks.getContentPane().add(taskpan);
			taskpan.setLayout(null);
			
			Title.setIcon(new ImageIcon(allPro.class.getResource("/images/task.png")));
			Title.setBounds(50, 11, 394, 58);
			Border compound;
			
			Border raisedbevel = BorderFactory.createRaisedBevelBorder();
			Border loweredbevel = BorderFactory.createLoweredBevelBorder();
			compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
			Title.setBorder(compound);
			taskpan.add(Title);
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			
			
			CustomTaskRenderer colouringTable = new CustomTaskRenderer();

			DefaultTableModel taskmodel = new DefaultTableModel(){
			    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int mColIndex) {
			        return false;
			    }
			}; 
			
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
		    	if(t.departementId == id)  {
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
			back_button.setBounds(204, 345, 75, 28);
			taskpan.add(back_button);
			back_button.setBackground(new Color(135, 206, 235));
			back_button.setForeground(new Color(0, 0, 0));
			back_button.setFocusPainted(false);
			back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
			back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
			back_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tasks.dispose();
				}
			});
			
			return;
			}
		});
		
		JButton project_button = new JButton("Projects");
		project_button.setIcon(null);
		project_button.setBounds(142, 374, 100, 28);
		project_button.setBackground(new Color(135, 206, 235));
		project_button.setForeground(new Color(0, 0, 0));
		project_button.setFocusPainted(false);
		project_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		project_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		panel.add(project_button);
		project_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {JFrame projects = new JFrame();
			projects.setBounds(200, 100, 510, 425);
			projects.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			projects.getContentPane().setLayout(null);
	
			int id = Integer.parseInt(idField.getText());
			
			JPanel projectpan = new JPanel();
			projectpan.setBounds(0, 0, 494, 391);
			projects.getContentPane().add(projectpan);
			projectpan.setLayout(null);
			
			Title.setIcon(new ImageIcon(allPro.class.getResource("/images/pro.png")));
			Title.setBounds(50, 11, 394, 58);
			Border compound;
			
			Border raisedbevel = BorderFactory.createRaisedBevelBorder();
			Border loweredbevel = BorderFactory.createLoweredBevelBorder();
			compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
			Title.setBorder(compound);
			projectpan.add(Title);
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			
			
			CustomProRenderer colouringTable = new CustomProRenderer();

			DefaultTableModel projectmodel = new DefaultTableModel(){
			    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int mColIndex) {
			        return false;
			    }
			}; 
			
			JTable projecttable = new JTable(projectmodel);
			JScrollPane scroller = new JScrollPane(projecttable);
			scroller.setBounds(10, 60, 475, 274);
			projectpan.add(scroller);
			projectmodel.addColumn("ID"); 
			projectmodel.addColumn("Name"); 
			projectmodel.addColumn("Description"); 
			projectmodel.addColumn("Duration"); 
			projectmodel.addColumn("Budget"); 
			projectmodel.addColumn("Chef"); 
			projectmodel.addColumn("Departement");
		    for (Project p: User.projects) {
		    	if (p.departementId == id) {
		    		projectmodel.addRow(new Object[]{String.valueOf(p.proId), p.name, p.description, p.duration, p.budget, p.getChefName(), p.departement});
		    	}
		    }
		    
		    for (int i = 0; i < table.getColumnCount(); i++) {
			    projecttable.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
		    }
			
			projects.setVisible(true);
			
			JButton back_button = new JButton("Back");
			back_button.setBounds(204, 345, 75, 28);
			projectpan.add(back_button);
			back_button.setBackground(new Color(135, 206, 235));
			back_button.setForeground(new Color(0, 0, 0));
			back_button.setFocusPainted(false);
			back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
			back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
			back_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					projects.dispose();
				}
			});
			
			return;
			}
		});
		
		JButton employee_button = new JButton("Employees");
		employee_button.setIcon(null);
		employee_button.setBounds(252, 374, 122, 28);
		employee_button.setBackground(new Color(135, 206, 235));
		employee_button.setForeground(new Color(0, 0, 0));
		employee_button.setFocusPainted(false);
		employee_button.setFont(new Font("Tahoma", Font.BOLD, 12));
		employee_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		panel.add(employee_button);
		employee_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {JFrame employees = new JFrame();
			employees.setBounds(200, 100, 510, 425);
			employees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			employees.getContentPane().setLayout(null);
	
			int id = Integer.parseInt(idField.getText());
			
			JPanel employeepan = new JPanel();
			employeepan.setBounds(0, 0, 494, 391);
			employees.getContentPane().add(employeepan);
			employeepan.setLayout(null);
			
			Title.setIcon(new ImageIcon(allPro.class.getResource("/images/dep.png")));
			Title.setBounds(50, 11, 394, 58);
			Border compound;
			
			Border raisedbevel = BorderFactory.createRaisedBevelBorder();
			Border loweredbevel = BorderFactory.createLoweredBevelBorder();
			compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
			Title.setBorder(compound);
			employeepan.add(Title);
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			Title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			
			
			CustomProRenderer colouringTable = new CustomProRenderer();

			DefaultTableModel employeemodel = new DefaultTableModel(){
			    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int mColIndex) {
			        return false;
			    }
			}; 
			
			JTable employeetable = new JTable(employeemodel);
			JScrollPane scroller = new JScrollPane(employeetable);
			scroller.setBounds(10, 60, 475, 274);
			employeepan.add(scroller);
			employeemodel.addColumn("ID"); 
			employeemodel.addColumn("Username"); 
			employeemodel.addColumn("Departement");
		    for (Employee em: User.employees) {
		    	if (em.departementId == id) {
		    		employeemodel.addRow(new Object[]{String.valueOf(em.userId), em.username, em.departement});
		    	}
		    }
		    
		    for (int i = 0; i < table.getColumnCount(); i++) {
		    	employeetable.getColumnModel().getColumn(i).setCellRenderer(colouringTable);
		    }
			
		    employees.setVisible(true);
			
			JButton back_button = new JButton("Back");
			back_button.setBounds(204, 345, 75, 28);
			employeepan.add(back_button);
			back_button.setBackground(new Color(135, 206, 235));
			back_button.setForeground(new Color(0, 0, 0));
			back_button.setFocusPainted(false);
			back_button.setFont(new Font("Tahoma", Font.BOLD, 12));
			back_button.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
			back_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					employees.dispose();
				}
			});
			
			return;
			}
		});
		
		JButton back_button = new JButton("Back");
		back_button.setBounds(58, 374, 75, 28);
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(allPro.class.getResource("/images/bg.png")));
		label.setBounds(0, 29, 514, 398);
		panel.add(label);
	}
}