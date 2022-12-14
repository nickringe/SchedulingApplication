package co.grandcircus.EmployeeApi.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.util.TypeKey;

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
	
	//returns a list of all Employees
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
	
	//returns a list of all unassigned shifts ie Master Schedule
	@GetMapping("/master")
	public List<Shift> displayMasterSchedule() {
		Employee masterEmployee = repo.findById("634c155f245151700ec73b89").orElseThrow(() -> new EmployeeNotFoundException("634c155f245151700ec73b89"));
		
		List<Shift> masterList = new ArrayList<>();
		for (Shift s : masterEmployee.getSchedule()) {
			masterList.add(s);
		}
		return masterList;
	}
	
	//returns a single employee
	@GetMapping("/{id}")
	public Employee getOneEmployee(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	//creates a new employee
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
	
	//deletes an employee by id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable("id")String id) {
		repo.deleteById(id);
	}
	
	//deletes a shift based on id and shiftId
	@DeleteMapping("/delete/{shiftId}/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteShift(@PathVariable String shiftId, @PathVariable String id) {
		List<Shift> shiftList = repo.findById(id).get().getSchedule();
		for (Shift shift : shiftList) {
			
			if (shift.getId().equals(shiftId)) {
				repo.updateById(id, shiftId);
				System.out.println("2. Deleted shift with shiftID: " + shiftId);
				
			}
		}
			
	}
	
	//updates an employee
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") String id) {
		employee.setId(id);
		return repo.save(employee);
	}
	
	//updates an employee's shift/schedule
	@PutMapping("/update/{id}")
	public void updateEmployeeSchedule(@RequestBody Shift employeeShift, @PathVariable("id") String id) {
		repo.updateById(id, employeeShift.getShiftName(), employeeShift.getDate(), employeeShift.getStartTime(), employeeShift.getEndTime());
	}
	
	//returns all shifts for each employee. stores in HASH MAP
	@GetMapping("/shift/{start}/{end}")
	public HashMap<String, ArrayList<Shift>> getShiftsBetween(@PathVariable("start") String start, @PathVariable("end") String end){
		List<Shift> shifts = new ArrayList<>();
		for (Employee employee : repo.findAll()) {
			for (Shift employeeShift : employee.getSchedule()) {
				shifts.add(employeeShift);
			}
		}
		HashMap<String, ArrayList<Shift>> datesToEvents = new HashMap<String, ArrayList<Shift>>();
		LocalDate startDate;
		LocalDate endDate;
		
		for(Shift shift : shifts) {
			//Start and end of current object
			startDate = LocalDateTime.parse(shift.getDate() + "T" +shift.getStartTime()).toLocalDate();
			endDate = LocalDateTime.parse(shift.getDate() + "T" + 
			shift.getEndTime()).toLocalDate();
			
			//Put the current even in the list contained under the startdate key
			if(!datesToEvents.containsKey(startDate.toString())) {
				datesToEvents.put(startDate.toString(), new ArrayList<Shift>());
			}
			datesToEvents.get(startDate.toString()).add(shift);
			startDate = startDate.plusDays(1);
			
			while(startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
				if(!datesToEvents.containsKey(startDate.toString())) {
					datesToEvents.put(startDate.toString(), new ArrayList<Shift>());
				}
				datesToEvents.get(startDate.toString()).add(shift);
				startDate = startDate.plusDays(1);
			}
		}
		
		return datesToEvents;
	}
	
	//returns all shifts for each employee with id. stores in HASH MAP
	@GetMapping("/shift/{start}/{end}/{id}")
	public HashMap<String, ArrayList<Shift>> getShiftsBetweenWithId(@PathVariable("start") String start, @PathVariable("end") String end,
			@PathVariable("id") String id){
		List<Shift> shifts = new ArrayList<>();
		Employee employee = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		for (Shift employeeShift : employee.getSchedule()) {
			shifts.add(employeeShift);
		}
		
		HashMap<String, ArrayList<Shift>> datesToShifts = new HashMap<String, ArrayList<Shift>>();
		LocalDate startDate;
		LocalDate endDate;
		
		for(Shift shift : shifts) {
			//Start and end of current object
			startDate = LocalDateTime.parse(shift.getDate() + "T" +shift.getStartTime()).toLocalDate();
			endDate = LocalDateTime.parse(shift.getDate() + "T" + 
			shift.getEndTime()).toLocalDate();
			
			//Put the current shift in the list contained under the startDate key
			if(!datesToShifts.containsKey(startDate.toString())) {
				datesToShifts.put(startDate.toString(), new ArrayList<Shift>());
			}
			datesToShifts.get(startDate.toString()).add(shift);
			startDate = startDate.plusDays(1);
			
			while(startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
				if(!datesToShifts.containsKey(startDate.toString())) {
					datesToShifts.put(startDate.toString(), new ArrayList<Shift>());
				}
				datesToShifts.get(startDate.toString()).add(shift);
				startDate = startDate.plusDays(1);
			}
		}
		
		return datesToShifts;
	}
	
	//returns a List of all shifts for each employee between two times.
	@GetMapping("/shiftlist/{start}/{end}/{id}")
	public ArrayList<Shift> getShiftsBetweenTimesWithId(@PathVariable("start") String start, @PathVariable("end") String end,
			@PathVariable("id") String id){
	
		Employee employee = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

		ArrayList<Shift> shifts = new ArrayList<>();
		

		LocalDate shiftDate;
		
		
		for(Shift shift : employee.getSchedule()) {
			//Start and end of current object
			shiftDate = LocalDateTime.parse(shift.getDate() + "T" + shift.getStartTime()).toLocalDate();
			
			if ((shiftDate.isEqual(LocalDate.parse(start))) 
					|| (shiftDate.isAfter(LocalDate.parse(start)) && shiftDate.isBefore(LocalDate.parse(end))) 
					|| (shiftDate.isEqual(LocalDate.parse(end)))){
				shifts.add(shift);
			}
			
		}
		
		return shifts;
	}
	
	//returns a HASH MAP of all employees shifts based on start/end time
	@GetMapping("/shiftlist/{start}/{end}")
	public HashMap<String, ArrayList<Shift>> getShiftsBetweenTimes(@PathVariable("start")String start, @PathVariable("end") String end) {
		
		List<Employee> employeeList = repo.findAll();
		HashMap<String, ArrayList<Shift>> shiftList = new HashMap<>();
		
		for(Employee employee : employeeList) {
			for(Shift shift : employee.getSchedule()) {
				//Put the current shift in the list contained under the startDate key
				if(!shiftList.containsKey(start.toString())) {
					shiftList.put(start.toString(), new ArrayList<Shift>());
					
				}
				if (LocalDate.parse(shift.getDate()).isEqual(LocalDate.parse(start))) {
					shiftList.get(start.toString()).add(shift);	
				}
			}
		}
		
		//uncomment to see the key values being added to shiftList
//	for(String key : shiftList.keySet()) {
//		System.out.println("key: " + key.toString());
//	}
//	for(ArrayList<Shift> value : shiftList.values()) {
//		for (Shift shift : value) {
//			System.out.println("value: " + shift.getShiftName() + shift.getDate());
//		}
//	}
//		
		
		return shiftList;
	}
	
	
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String characterNotFoundHandler(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}

}
