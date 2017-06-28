package custom;

import javax.swing.table.*;

import models.Departement;
import models.Employee;
import models.Project;
import models.Task;
import models.User;

public class CellTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }
	
	public void fillDep() {
	    for (Departement d : User.departements) {
		    addRow(new Object[]{String.valueOf(d.depId), d.name, d.chef});
	    }
	}
	
	public void fillPro() {
	    for (Project p: User.projects) {
		    addRow(new Object[]{String.valueOf(p.proId), p.name, p.description, p.duration, p.budget, p.getChefName(), p.departement, String.valueOf(p.proHasPercentage()).toString() + "%", p.valid});
	    } 
	}
	
	public void fillTask() {
	    for (Task t: User.tasks) {
	    	if (t.commentary != null) {
	    		addRow(new Object[]{String.valueOf(t.taskId), t.description, t.final_date, t.duration, t.commentary, t.project, t.employee, t.status});
	    	} else {
	    		addRow(new Object[]{String.valueOf(t.taskId), t.description, t.final_date, t.duration, "", t.project, t.employee, t.status});
	    	}
		}
	}
	
	public void fillEmp() {
		for (Employee e: User.employees) {
		    addRow(new Object[]{String.valueOf(e.userId), e.username, e.departement, e.chef});
	    }
	}
}
