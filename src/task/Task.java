package task;


public class Task {
	public int taskId;
	public String description;
	public String duration;
	public String final_date;
	public String status;;
	public String commentary;;
	public String comment_nature;
	public int projectId;
	public String project;
	public int employeeId;
	public String employee;
	public int departementId;
	public String departement;
	
	public Task(int taskId, String description, String final_date, String duration, String status, String comment_nature, String commentary, int employeeId, String employee, int departementId, String departement, int projectId, String project) {
		this.taskId = taskId;
		this.description = description;
		this.duration = duration;
		this.final_date = final_date;
		this.status = status;
		this.commentary = commentary;
		this.comment_nature = comment_nature;
		this.employeeId = employeeId;
		this.employee = employee;
		this.departementId = departementId;
		this.departement = departement;
		this.projectId = projectId;
		this.project = project;
	}

	public int getTaskId() {
		return taskId;
	}

	public String getDescription() {
		return description;
	}

	public String getDuration() {
		return duration;
	}

	public String getFinal_date() {
		return final_date;
	}

	public String getStatus() {
		return status;
	}

	public String getCommentary() {
		return commentary;
	}

	public String getComment_nature() {
		return comment_nature;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getEmployee() {
		return employee;
	}

	public int getDepartementId() {
		return departementId;
	}

	public String getDepartement() {
		return departement;
	}
}
