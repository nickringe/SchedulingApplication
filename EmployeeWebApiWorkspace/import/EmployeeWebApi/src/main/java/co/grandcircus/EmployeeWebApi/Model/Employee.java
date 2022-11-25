package co.grandcircus.EmployeeWebApi.Model;

import java.util.List;

import co.grandcircus.EmployeeApi.Model.Shift;

public class Employee {
	
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String empId;
	private List<Shift> schedule;
	private Double totalHours;

	
	public Employee() {
		
	}

	public Employee(String firstname, String lastname, String email, String empId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.empId = empId;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public List<Shift> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Shift> schedule) {
		this.schedule = schedule;
	}
	
	public Double getTotalHours() {
		if (this.schedule == null) {
			return 0.00;
		}
		
		return totalHours;
	}
	

}
