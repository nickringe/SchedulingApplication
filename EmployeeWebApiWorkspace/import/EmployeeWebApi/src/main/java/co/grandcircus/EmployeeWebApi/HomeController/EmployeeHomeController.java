package co.grandcircus.EmployeeWebApi.HomeController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import co.grandcircus.EmployeeApi.EmployeeNotFoundException;
import co.grandcircus.EmployeeWebApi.Model.Employee;
import co.grandcircus.EmployeeWebApi.Model.Shift;
import co.grandcircus.EmployeeWebApi.Service.EmployeeService;

@Controller
public class EmployeeHomeController {

	@Autowired
	private EmployeeService service;

	@RequestMapping("/")
	public String showEmployees(Model model) {
		
		ArrayList<Employee> sortedList = new ArrayList<>();
		for (Employee employee : service.getAllEmployees()) {
			sortedList.add(employee);
		}
		Collections.sort(sortedList, Comparator.comparing(Employee::getLastname));
		
		model.addAttribute("sortedList", sortedList);
		model.addAttribute("employees", service.getAllEmployees());
		return "index";
	}
	
	@RequestMapping("/create-shift")
	public String showCreateShift(Model model) {
		
		model.addAttribute("employees", service.getAllEmployees());
		return "create-shift";
	}
	
	//this happens when User creates a brand new shift, to be put on either Master Schedule or given to a specific employee
	@PostMapping("/add-created-shift")
	public String addCreatedShift(Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String shiftName, @RequestParam(required = false) String date,
			@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
		
		String shiftAdded = "Shift added";
		
		System.out.println("1. Got to initial method");
		//if id is null, add to master schedule
		if (id == null) {
			Employee master = service.getEmployee("634c155f245151700ec73b89");
			List<Shift> masterSchedule = master.getSchedule();
			//
			Shift newShift = new Shift(shiftName, date, startTime, endTime);
			//masterSchedule.add(newShift);
			//master.setSchedule(masterSchedule);
			
			System.out.println("1.");
			//service.updateEmployee("634c155f245151700ec73b89", master);
			service.updateEmployeeSchedule(newShift, "634c155f245151700ec73b89");
			
			model.addAttribute("employees", service.getAllEmployees());
			model.addAttribute("shiftAdded", shiftAdded);
			
			
			return "create-shift";
		}
		System.out.println("2. Got to employee portion");
		//else add to employee's schedule
		Employee employee = service.getEmployee(id);
		//List<Shift> employeeSchedule = employee.getSchedule();
		Shift newEmployeeShift = new Shift(shiftName, date, startTime, endTime);
//		employeeSchedule.add(newEmployeeShift);
//		employee.setSchedule(employeeSchedule);
		
		//service.updateEmployee(id, employee);
		service.updateEmployeeSchedule(newEmployeeShift, employee.getId());
		System.out.println("3. Got past the service method");
		
		model.addAttribute("employees", service.getAllEmployees());
		model.addAttribute("shiftAdded", shiftAdded);
		return "create-shift";
	}
	
	@RequestMapping("/1")
	public String showEmployees1(Model model) {

		String empIdAlreadyExists = "Employee ID already exists";
		model.addAttribute("empIdAlreadyExists", empIdAlreadyExists);
		model.addAttribute("employees", service.getAllEmployees());
		return "index";
	}

	@RequestMapping("/delete")
	public String deleteEmployee(@RequestParam String id) {
		service.deleteEmployee(id);
		return "redirect:/";
	}

	@PostMapping("/add")
	public String addEmployee(Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname,
			@RequestParam(required = false) String email, @RequestParam(required = false) String empId) {
		
		//do nothing if employee ID already exists
		for (Employee emp : service.getAllEmployees()) {

			if (emp.getEmpId().equals(empId)) {
				String empIdAlreadyExists = "- Employee ID already exists -";
				model.addAttribute("empIdAlreadyExists", empIdAlreadyExists);

				return "redirect:/1";
			}
		}
	
		//add and save new employee
		Employee employee = new Employee(firstname, lastname, email, empId);
		List<Shift> employeeSchedule = new ArrayList<>();
		employee.setSchedule(employeeSchedule);
		employee.setTotalHours(0.00);
		service.addEmployee(employee);
		
		return "redirect:/";
	}

	@RequestMapping("/form")
	public String updateEmployee(Model model, @RequestParam(required = false) String id) {
		model.addAttribute("employee", service.getEmployee(id));
		return "employeeform";
	}

	@PostMapping("/postEmployee")
	public String saveEmployee(Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname,
			@RequestParam(required = false) String email, @RequestParam(required = false) String empId) {
		Employee employee = service.getEmployee(id);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setEmail(email);
		employee.setEmpId(empId);
		service.updateEmployee(id, employee);
		return "redirect:/";
	}

	@RequestMapping("/schedule")
	public String viewEmployeeSchedule(Model model, @RequestParam(required = false) String id) {
		model.addAttribute("employee", service.getEmployee(id));
		model.addAttribute("firstname", service.getEmployee(id).getFirstname());
		if (service.getEmployee(id).getSchedule() != null) {
			
			model.addAttribute("totalHours", service.getEmployee(id).getTotalHours());
		}
		return "schedule";
	}

	//deletes a given shift
	@RequestMapping("/remove")
	public String deleteShift(@RequestParam String shiftId, @RequestParam String id) {
		service.deleteShift(shiftId, id);
		return "redirect:/schedule?id=" + id;
	}

	@GetMapping("/add-shift")
	public String displayAddShift(Model model, @RequestParam(required = false) String id) {
		Employee employee = service.getEmployee(id);
		Employee master = service.getEmployee("634c155f245151700ec73b89");
		if (employee.getSchedule() == null) {
			model.addAttribute("master", master.getSchedule());
			model.addAttribute("employee", employee);
			model.addAttribute("firstname", employee.getFirstname());
			model.addAttribute("lastname", employee.getLastname());
			return "add-shift";

		} else {
			model.addAttribute("master", master.getSchedule());
			model.addAttribute("employee", employee);
			model.addAttribute("firstname", employee.getFirstname());
			model.addAttribute("lastname", employee.getLastname());
			model.addAttribute("shifts", employee.getSchedule());
		}
		return "add-shift";
	}

	@GetMapping("/search")
	public String searchForOpenShifts(Model model, @RequestParam(required = false) String id, String searchStart,
			String searchEnd) {

		Employee employee = service.getEmployee(id);
		Employee master = service.getEmployee("634c155f245151700ec73b89");
		if (employee.getSchedule() == null) {
			System.out.println("Employee schedule is null");
			List<Shift> masterShifts = master.getSchedule();
			List<Shift> openShifts = new ArrayList<>();
			for (Shift shift : masterShifts) {
				// if search time and open shifts line up
				if (LocalDateTime.parse(searchStart).isBefore(LocalDateTime.parse(shift.getEndTime()))
						&& LocalDateTime.parse(searchEnd).isBefore(LocalDateTime.parse(shift.getEndTime()))
						|| (LocalDateTime.parse(searchStart).isBefore(LocalDateTime.parse(shift.getEndTime()))
								&& LocalDateTime.parse(searchEnd).isEqual(LocalDateTime.parse(shift.getEndTime())))
						|| (LocalDateTime.parse(searchStart).isEqual(LocalDateTime.parse(shift.getStartTime()))
								&& LocalDateTime.parse(searchEnd).isEqual(LocalDateTime.parse(shift.getEndTime())))) {
					// then add to openShifts
					openShifts.add(shift);
				}
			}
			model.addAttribute("searchStartDateString",
					LocalDateTime.parse(searchStart).format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
			model.addAttribute("searchEndDateString",
					LocalDateTime.parse(searchEnd).format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
			model.addAttribute("searchStartString",
					LocalDateTime.parse(searchStart).format(DateTimeFormatter.ofPattern("hh:MM a")));
			model.addAttribute("searchEndString",
					LocalDateTime.parse(searchEnd).format(DateTimeFormatter.ofPattern("hh:MM a")));
			model.addAttribute("openShifts", openShifts);
			model.addAttribute("master", master.getSchedule());
			model.addAttribute("employee", employee);
			model.addAttribute("firstname", employee.getFirstname());
			model.addAttribute("lastname", employee.getLastname());
			return "add-shift";

		} else {

			List<Shift> masterShifts = master.getSchedule();
			List<Shift> openShifts = new ArrayList<>();
			for (Shift shift : masterShifts) {
				// if search time and open shifts line up
			if (LocalDateTime.parse(searchStart).isBefore(LocalDateTime.parse(shift.getEndTime()))
						&& LocalDateTime.parse(searchEnd).isBefore(LocalDateTime.parse(shift.getEndTime()))
						|| (LocalDateTime.parse(searchStart).isBefore(LocalDateTime.parse(shift.getEndTime()))
								&& LocalDateTime.parse(searchEnd).isEqual(LocalDateTime.parse(shift.getEndTime())))
						|| (LocalDateTime.parse(searchStart).isEqual(LocalDateTime.parse(shift.getStartTime()))
						&& LocalDateTime.parse(searchEnd).isEqual(LocalDateTime.parse(shift.getEndTime())))) 
				{
					 //then add
					openShifts.add(shift);
				}
			}

			model.addAttribute("searchStartDateString",
					LocalDateTime.parse(searchStart).format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
			model.addAttribute("searchEndDateString",
					LocalDateTime.parse(searchEnd).format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
			model.addAttribute("searchStartString",
					LocalDateTime.parse(searchStart).format(DateTimeFormatter.ofPattern("hh:MM a")));
			model.addAttribute("searchEndString",
					LocalDateTime.parse(searchEnd).format(DateTimeFormatter.ofPattern("hh:MM a")));
			model.addAttribute("openShifts", openShifts);
			model.addAttribute("master", master.getSchedule());
			model.addAttribute("employee", employee);
			model.addAttribute("firstname", employee.getFirstname());
			model.addAttribute("lastname", employee.getLastname());
			model.addAttribute("shifts", employee.getSchedule());
			

			return "add-shift";
		}
	}
	
	//Displays all open shifts on Master Schedule
	@GetMapping("/master")
	public String viewMasterSchedule(Model model) {
		Employee masterEmployee = service.getEmployee("634c155f245151700ec73b89");
		
		List<Shift> masterList = new ArrayList<>();
		for (Shift s : masterEmployee.getSchedule()) {
			masterList.add(s);
		}
		
		model.addAttribute("masterList", masterList);
		return "master";
	}
	
	//This method is where someone picks up a shift that is already created on the Master Schedule
	@PostMapping("/add-to-schedule")
	public String addShiftToEmployee(Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String shiftId, @RequestParam(required = false) String shiftName,
			@RequestParam(required = false) String date, @RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		
		//String id is the EMPLOYEE's ID
		//shiftId is the MASTER's shift ID
		
		Employee employee = service.getEmployee(id);
		Employee master = service.getEmployee("634c155f245151700ec73b89");
		
		List<Shift> employeeShifts = employee.getSchedule();
		List<Shift> masterSchedule = master.getSchedule();
		
		//looks for the shift user wants then adds it to the employee's schedule. Then removes it from the master schedule
		for (Shift shift : master.getSchedule()) {
			if (shift.getId().equals(shiftId)) {
				employeeShifts.add(shift);
				masterSchedule.remove(shift);
				System.out.println("2");
				employee.setSchedule(employeeShifts);
				master.setSchedule(masterSchedule);
				service.updateEmployee(id, employee);
				service.updateEmployee("634c155f245151700ec73b89", master);
				break;
				
			}
		}

			model.addAttribute("master", master.getSchedule());
			model.addAttribute("employee", employee);
			model.addAttribute("firstname", employee.getFirstname());
			model.addAttribute("lastname", employee.getLastname());
			model.addAttribute("shifts", employee.getSchedule());
		
		return "add-shift";
	}
	
	@RequestMapping("/confirm-delete")
	public String confirmDelete(Model model, @RequestParam(required=false) String id) {
		
		Employee employee = service.getEmployee(id);
		model.addAttribute("id", employee.getId());
		model.addAttribute("firstname", employee.getFirstname());
		model.addAttribute("lastname", employee.getLastname());
		
		
		return "confirm-delete";
	}
	
	@RequestMapping("/confirm-delete-shift")
	public String confirmDeleteShift(Model model, @RequestParam(required=false) String id, 
			@RequestParam(required=false) String shiftId, @RequestParam(required=false) String shiftName,
			@RequestParam(required=false) String date, @RequestParam(required=false) String startTime,
			@RequestParam(required=false) String endTime, @RequestParam(required=false) String shiftLength) {
		
		Employee employee = service.getEmployee(id);
		String shiftRemoved = shiftName + " Shift Deleted";
		model.addAttribute("shiftRemoved", shiftRemoved);
		model.addAttribute("firstname", employee.getFirstname());
		model.addAttribute("shiftname", shiftName);
		model.addAttribute("date", date);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("shiftLength", shiftLength);
		model.addAttribute("shiftId", shiftId);
		model.addAttribute("id", id);
		
		
		return "confirm-delete-shift";
	}
	
	@RequestMapping("/employee-details")
	public String showEmployeeDetails(Model model, @RequestParam(required = false) String id) {
		
		Employee employee = service.getEmployee(id);
		model.addAttribute("employee", employee);
		
		return "employee-details";
	}
}
