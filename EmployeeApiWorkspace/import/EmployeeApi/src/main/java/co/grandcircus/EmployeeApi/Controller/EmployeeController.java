package co.grandcircus.EmployeeApi.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import co.grandcircus.EmployeeApi.EmployeeNotFoundException;
import co.grandcircus.EmployeeApi.Model.Employee;
import co.grandcircus.EmployeeApi.Model.Shift;
//import co.grandcircus.EmployeeApi.Model.Shift8080;
import co.grandcircus.EmployeeApi.Repos.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository repo;
	
	@GetMapping("/")
	public List<Employee> listAllEmployees(){
		List<Employee> returnList = repo.findAll();
		for (Employee e : returnList) {
			if (e.getFirstname().equals("Master")) {
				returnList.remove(e);
				break;
			}
		}
		return returnList;
	}
	
	@GetMapping("/master")
	public List<Shift> displayMasterSchedule() {
		Employee masterEmployee = repo.findById("634c155f245151700ec73b89").orElseThrow(() -> new EmployeeNotFoundException("634c155f245151700ec73b89"));
		
		List<Shift> masterList = new ArrayList<>();
		for (Shift s : masterEmployee.getSchedule()) {
			masterList.add(s);
		}
		return masterList;
	}
	
	@GetMapping("/{id}")
	public Employee getOneEmployee(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	@PostMapping("/") 
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee employee, Model model) {
		List<Employee> emps = new ArrayList<>();
		for (Employee e : repo.findAll()) {
			emps.add(e);
			if (e.getEmpId().equals(employee.getEmpId())) {
				System.out.println("Error, employee ID already exists");
				return null;
			}
		}
		return repo.insert(employee);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable("id")String id) {
		repo.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") String id) {
		employee.setId(id);
		return repo.save(employee);
	}
	
	
	@DeleteMapping("/delete/{shiftId}/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteShift(@PathVariable String shiftId, @PathVariable String id) {
		System.out.println("1. Made it to 8080 API");
		List<Shift> shiftList = repo.findById(id).get().getSchedule();
		for (Shift shift : shiftList) {
			
			if (shift.getId().equals(shiftId)) {
				repo.updateById(id, shiftId);
				System.out.println("2. Deleted shift with shiftID: " + shiftId);
				
			}
		}
			
	}
	
	@PostMapping("/add-shift/{shiftId}/{id}/{shiftName}/{date}/{startTime}/{endTime}")
	public void addEmployeeShift(@PathVariable String shiftId, @PathVariable String id, @PathVariable String shiftName,
			@PathVariable String date, @PathVariable String startTime, @PathVariable String endTime) {
		
		System.out.println("1. Made it to 8080 API");
		repo.updateById(id, shiftName, date, startTime, endTime);
		
	}
	
	
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String characterNotFoundHandler(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}

}
