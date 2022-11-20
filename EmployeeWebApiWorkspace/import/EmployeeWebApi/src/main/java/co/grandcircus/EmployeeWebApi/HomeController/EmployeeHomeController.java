package co.grandcircus.EmployeeWebApi.HomeController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

		model.addAttribute("employees", service.getAllEmployees());
		return "index";
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
		
		for (Employee emp : service.getAllEmployees()) {

			if (emp.getEmpId().equals(empId)) {

				return "redirect:/1";
			}
		}
	
		Employee employee = new Employee(firstname, lastname, email, empId);
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
			@RequestParam(required = false) String email, @RequestParam(required = false) String empId,
			@RequestParam(required = false) List<Shift> schedule) {
		Employee employee = service.getEmployee(id);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setEmail(email);
		employee.setEmpId(empId);
		employee.setSchedule(schedule);
		service.updateEmployee(id, employee);
		return "redirect:/";
	}

	@RequestMapping("/schedule")
	public String viewEmployeeSchedule(Model model, @RequestParam(required = false) String id) {
		model.addAttribute("employee", service.getEmployee(id));
		model.addAttribute("firstname", service.getEmployee(id).getFirstname());
		if (service.getEmployee(id).getSchedule() != null) {
			//model.addAttribute("totalHours", service.getEmployee(id).getSchedule().get(0).getTotalHours());
			model.addAttribute("totalHours", service.getEmployee(id).getTotalHours());
		}
		return "schedule";
	}

	@RequestMapping("/remove")
	public String deleteShift(@RequestParam String shiftId, @RequestParam String id) {
		System.out.println("1. Home Controller - next should be service.deleteShift(shiftId, id)");
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
		System.out.println("1");
		for (Shift shift : master.getSchedule()) {
			if (shift.getId().equals(shiftId)) {
				employeeShifts.add(shift);
				System.out.println("2");
				service.addShift(shiftId, id, shiftName, date, startTime, endTime);
				//service.deleteShift(shiftId, "634c155f245151700ec73b89");
			}
		}

			model.addAttribute("master", master.getSchedule());
			model.addAttribute("employee", employee);
			model.addAttribute("firstname", employee.getFirstname());
			model.addAttribute("lastname", employee.getLastname());
			model.addAttribute("shifts", employee.getSchedule());
		
		return "add-shift";
	}
}
