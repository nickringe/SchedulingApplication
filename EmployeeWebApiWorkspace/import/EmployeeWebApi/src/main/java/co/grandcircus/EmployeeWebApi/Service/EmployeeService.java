package co.grandcircus.EmployeeWebApi.Service;


import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
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
	
	public HashMap<String, ArrayList<Shift>> getShiftsByTimeRange(String start, String end){
		String url = baseUrl + "/shift/" + start + "/" + end;
		
		ParameterizedTypeReference<HashMap<String, ArrayList<Shift>>> responseType = 
				new ParameterizedTypeReference<HashMap<String, ArrayList<Shift>>>(){};
		
		RequestEntity<Void> request1 = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
		HashMap<String, ArrayList<Shift>> response = request.exchange(request1, responseType).getBody();
		return response;
	}
	
	public HashMap<String, ArrayList<Shift>> getShiftsByTimeRangeAndId(String start, String end, String id){
		String url = baseUrl + "/shift/" + start + "/" + end + "/" + id;
		
		ParameterizedTypeReference<HashMap<String, ArrayList<Shift>>> responseType = 
				new ParameterizedTypeReference<HashMap<String, ArrayList<Shift>>>(){};
		
		RequestEntity<Void> request1 = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
		HashMap<String, ArrayList<Shift>> response = request.exchange(request1, responseType).getBody();
		return response;
	}

	
	
}
