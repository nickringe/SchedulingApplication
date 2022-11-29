package co.grandcircus.EmployeeWebApi.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.EmployeeWebApi.Model.Employee;
import co.grandcircus.EmployeeWebApi.Model.Shift;


@Service
public class EmployeeService {
	
	@Value("${employeeapi.baseUrl}")
	private String baseUrl;
	private String url;
	private RestTemplate request = new RestTemplate();
	
	public Employee[] getAllEmployees() {
		url = baseUrl + "/";
		Employee[] response = request.getForObject(url, Employee[].class);	
		return response;
	}
	
	public Employee getEmployee(String id) {
		url = baseUrl + "/" + id;
		Employee response = request.getForObject(url, Employee.class);
		return response;
	}
	
	public void deleteEmployee(String id) {
		url = baseUrl + "/{id}";
		request.delete(url, id);
	}

	public void addEmployee(Employee employee) {
		url = baseUrl + "/";
		request.postForObject(url, employee, String.class);
	}
	
	public void updateEmployee(String id, Employee employee) {
		url = baseUrl + "/{id}";
		request.put(url, employee, id);
	}
	
//	public void updateEmployeeSchedule(Shift employeeShift, String id) {
//		System.out.println("Made it into the Service");
//		url = baseUrl + "/update/{id}";
//		request.put(url, employeeShift, id);
//		System.out.println("Made it through Service");
//	}
	
	public void deleteShift(String shiftId, String id) {
		url = baseUrl + "/delete/" + shiftId + "/" + id;
		request.delete(url, shiftId, id);
		
	}

	
	
}
