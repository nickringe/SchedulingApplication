package co.grandcircus.EmployeeApi.Model;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
public class Employee implements Comparator<Employee> {
	
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String empId;
	private List<Shift> schedule;
	private Double totalHours;
	private String mainPhone;
	private String otherPhone;
	private String streetAddress;
	private String city;
	private String state;
	private String zipcode;
	private Double payRate;
	private String emergencyContact;
	private String emergencyPhone;

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
	
	public void setTotalHours(Double totalHours) {
		this.totalHours = totalHours;
	}


	public String getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Double getPayRate() {
		return payRate;
	}

	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	@Override
	public int compare(Employee o1, Employee o2) {
		//sorts employees by last name
		return o2.getLastname().compareTo(o1.getLastname());
	}
	
	
	
	

	
	
	

}
