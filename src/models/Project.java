package models;

public class Project {
	public String name;
	public int proId;
	public String description;
	public String duration;
	public String budget;
	public int chef;
	public int departementId;
	public String departement;
	public boolean valid;
	
	public Project(int proId, String name, String description, String duration, String budget, int chef, int departementId, String departement, boolean valid) {
		this.proId = proId;
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.budget = budget;
		this.chef = chef;
		this.departement = departement;
		this.departementId = departementId;
		this.valid = valid;
	}

	public String getName() {
		return name;
	}

	public int getProId() {
		return proId;
	}
	
	public String getDescription() {
		return description;
	}

	public String getDuration() {
		return duration;
	}
	
	public String getBudget() {
		return budget;
	}

	public int getChef() {
		return chef;
	}
	
	public String getChefName() {
		for (Employee e : User.employees) {
			if (e.userId == this.chef) {
				return e.username;
			}
		}
		return "";
	}

	public int getDepartementId() {
		return departementId;
	}
	
	public String getDepartement() {
		return departement;
	}
	
	public int ApprovedCount() {
		int count = 0;
		for(Task t: User.tasks) {
			if (t.projectId == proId && t.status.equals("Approved")) {
					count++;
			}
		}
		return count;
	}
	
	public int unapprovedCount() {
		int count = 0;
		for(Task t: User.tasks) {
			if (t.projectId == proId && t.status.equals("Unapproved")) {
					count++;
			}
		}
		return count;
	}
	
	public int waitingCount() {
		int count = 0;
		for(Task t: User.tasks) {
			if (t.projectId == proId && t.status.equals("Waiting")) {
					count++;
			}
		}
		return count;
	}
	
	public int progressCount() {
		int count = 0;
		for(Task t: User.tasks) {
			if (t.projectId == proId && t.status.equals("In progress")) {
					count++;
			}
		}
		return count;
	}
	

	
	public int proHasPercentage() {
		float count = 0;
		float countvalid = 0;
		int percentage = 0;
		for(Task t: User.tasks) {
			if (t.projectId == proId) {
				count++;
				if (t.status.equals("Approved")) {
					countvalid++;
				}
			}
		}
		percentage = Math.round(countvalid/count*100);
		return percentage;
	}

}
