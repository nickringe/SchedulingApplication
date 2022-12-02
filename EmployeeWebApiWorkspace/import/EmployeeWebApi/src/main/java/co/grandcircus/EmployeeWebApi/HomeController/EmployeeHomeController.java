package co.grandcircus.EmployeeWebApi.HomeController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
		
		ArrayList<Employee> sortedList = new ArrayList<>();
		for (Employee employee : service.getAllEmployees()) {
			sortedList.add(employee);
		}
		Collections.sort(sortedList, Comparator.comparing(Employee::getLastname));
		model.addAttribute("employees", service.getAllEmployees());
		model.addAttribute("sortedList", sortedList);
		return "create-shift";
	}
	
	//this happens when User creates a brand new shift, to be put on either Master Schedule or given to a specific employee
	@PostMapping("/add-created-shift")
	public String addCreatedShift(Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String shiftName, @RequestParam(required = false) String date,
			@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
		
		String shiftAdded = shiftName + " Shift added on " + date;
		
		System.out.println("1. Got to initial method");
		//if id is null, add to master schedule
		if (id == null) {
			//find Master schedule and create shift to be added
			Employee master = service.getEmployee("634c155f245151700ec73b89");
			List<Shift> masterSchedule = master.getSchedule();
			Shift newShift = new Shift(shiftName, date, startTime, endTime);
			
			//generate a random Employee ID and set it
			Integer random_int = (int)Math.floor(Math.random()*(99999999-10000000+1)+10000000);
			newShift.setId(random_int.toString());
			
			//add the completed shift to the Master Schedule, set Master schedule
			masterSchedule.add(newShift);
			master.setSchedule(masterSchedule);
			
			System.out.println("1a. Got to master schedule portion");
			//saves updated shift to the database
			service.updateEmployee("634c155f245151700ec73b89", master);
			
			model.addAttribute("employees", service.getAllEmployees());
			model.addAttribute("shiftAdded", shiftAdded);
			
			
			return "create-shift";
		}
		System.out.println("1a. Got to employee portion");
		//else add to employee's schedule
		Employee employee = service.getEmployee(id);
		List<Shift> employeeSchedule = employee.getSchedule();
		Shift newEmployeeShift = new Shift(shiftName, date, startTime, endTime);
		Integer random_int = (int)Math.floor(Math.random()*(99999999-10000000+1)+10000000);
		newEmployeeShift.setId(random_int.toString());
		employeeSchedule.add(newEmployeeShift);
		employee.setSchedule(employeeSchedule);
		
		service.updateEmployee(id, employee);
		
		System.out.println("3. Got past the service method");
		
		model.addAttribute("employees", service.getAllEmployees());
		model.addAttribute("shiftAdded", shiftAdded);
		return "create-shift";
	}
	
	@RequestMapping("/1")
	public String showEmployees1(Model model) {

		String empIdAlreadyExists = "Employee ID already exists";
		model.addAttribute("empIdAlreadyExists", empIdAlreadyExists);
		
		return "add-employee";
	}

	//deletes an employee based on their object ID
	@RequestMapping("/delete")
	public String deleteEmployee(@RequestParam String id) {
		service.deleteEmployee(id);
		return "redirect:/";
	}

	//add a new employee
	@PostMapping("/add")
	public String addEmployee(Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname,
			@RequestParam(required = false) String email, @RequestParam(required = false) String empId,
			@RequestParam(required = false) String mainPhone, @RequestParam(required = false) String otherPhone,
			@RequestParam(required = false) String streetAddress, @RequestParam(required = false) String city,
			@RequestParam(required = false) String state, @RequestParam(required = false) String zipcode,
			@RequestParam(required = false) Double payRate, @RequestParam(required = false) String emergencyContact,
			@RequestParam(required = false) String emergencyPhone) {
		
		//do nothing if employee ID already exists
		for (Employee emp : service.getAllEmployees()) {

			if (emp.getEmpId().equals(empId)) {
				String empIdAlreadyExists = "- Employee ID " + empId + " already exists -";
				model.addAttribute("empIdAlreadyExists", empIdAlreadyExists);

				return "add-employee";
			}
		}
	
		//add and save new employee
		Employee employee = new Employee(firstname, lastname, email, empId);
		List<Shift> employeeSchedule = new ArrayList<>();
		employee.setSchedule(employeeSchedule);
		employee.setTotalHours(0.00);
		
		//check to see if any other fields were updated
		if(mainPhone != null) {
			employee.setMainPhone(mainPhone);
		}
		if(otherPhone != null) {
			employee.setOtherPhone(otherPhone);
		}
		if(streetAddress != null) {
			employee.setStreetAddress(streetAddress);
		}
		if(city != null) {
			employee.setCity(city);
		}
		if(state != null) {
			employee.setState(state);
		}
		if(zipcode != null) {
			employee.setZipcode(zipcode);
		}
		if(payRate != null) {
			employee.setPayRate(payRate);
		}
		if(emergencyContact != null) {
			employee.setEmergencyContact(emergencyContact);
		}
		if(emergencyPhone != null) {
			employee.setEmergencyPhone(emergencyPhone);
		}
		service.addEmployee(employee);
		String success = firstname + " " + lastname + " successfully added";
		model.addAttribute("success", success);
		
		return "add-employee";
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
			@RequestParam(required = false) String mainPhone, @RequestParam(required = false) String otherPhone,
			@RequestParam(required = false) String streetAddress, @RequestParam(required = false) String city,
			@RequestParam(required = false) String state, @RequestParam(required = false) String zipcode,
			@RequestParam(required = false) Double payRate, @RequestParam(required = false) String emergencyContact,
			@RequestParam(required = false) String emergencyPhone) {
		Employee employee = service.getEmployee(id);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setEmail(email);
		employee.setEmpId(empId);
		
		//check to see if any other fields were updated
				if(mainPhone != null) {
					employee.setMainPhone(mainPhone);
				}
				if(otherPhone != null) {
					employee.setOtherPhone(otherPhone);
				}
				if(streetAddress != null) {
					employee.setStreetAddress(streetAddress);
				}
				if(city != null) {
					employee.setCity(city);
				}
				if(state != null) {
					employee.setState(state);
				}
				if(zipcode != null) {
					employee.setZipcode(zipcode);
				}
				if(payRate != null) {
					employee.setPayRate(payRate);
				}
				if(emergencyContact != null) {
					employee.setEmergencyContact(emergencyContact);
				}
				if(emergencyPhone != null) {
					employee.setEmergencyPhone(emergencyPhone);
				}
		service.updateEmployee(id, employee);
		return "redirect:/";
	}

	//returns a list of ALL shifts for one employee
	@RequestMapping("/schedule")
	public String viewEmployeeSchedule(Model model, @RequestParam(required = false) String id) {
		model.addAttribute("employee", service.getEmployee(id));
		model.addAttribute("firstname", service.getEmployee(id).getFirstname());
		if (service.getEmployee(id).getSchedule() != null) {
			
			model.addAttribute("totalHours", service.getEmployee(id).getTotalHours());
		}
		return "schedule";
	}
	
	//weekly view for schedule
	@RequestMapping("/schedule-weekly")
	public String viewWeeklyEmployeeSchedule(Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String date) {
		model.addAttribute("employee", service.getEmployee(id));
		model.addAttribute("firstname", service.getEmployee(id).getFirstname());
		if (service.getEmployee(id).getSchedule() != null) {
			model.addAttribute("totalHours", service.getEmployee(id).getTotalHours());
		}
		// If page is entered with no params (clicking on weekly view instead of either
				// of the arrows)
				LocalDate today;
				if (date == null) {
					today = LocalDate.now();
				} else {
					today = LocalDate.parse(date);
				}
				date = today.toString();
				String displayToday = LocalDate.now().toString();
				model.addAttribute("displayToday", displayToday);
				
				// Stores the numbers to be printed for the current week
				List<LocalDate> dates = new ArrayList<LocalDate>(7);
				HashMap<String, ArrayList<Shift>> shifts;

				// determines the day num of the current day, so that we can determine how many
				// days to backpedal in order to point at sunday
				int dayOffset = calculateDayOfWeek(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
				today = today.minusDays(dayOffset);

				// Used for printing the correct day numbers of this week on the jsp
				for (int i = 0; i < 7; i++) {
					dates.add(today);
					today = today.plusDays(1);
				}
				
				shifts = service.getShiftsByTimeRangeAndId(dates.get(0).toString(), dates.get(dates.size() -1).toString(), id);
			

				// Set today to the first day of this week
				today = today.minusDays(7);
				model.addAttribute("curWeekDate", today);
				model.addAttribute("curWeekMonthString", monthNumToString(today.getMonthValue()));
				// Set today to next weeks date
				today = today.plusDays(7);
				model.addAttribute("nextWeekDate", today.toString());
				// Set today to last weeks date
				today = today.minusDays(14);
				model.addAttribute("prevWeekDate", today.toString());
				// Day numbers to be printed
				model.addAttribute("dates", dates);
				model.addAttribute("shifts", shifts);
				shifts.forEach((key, value) -> System.out.println(key.toString() + ":" + value.toString()));
				// Set today to curDay for daily info section
				today = LocalDate.parse(date);
				model.addAttribute("curDayDate", today);
				model.addAttribute("curDayMonthString", monthNumToString(today.getMonthValue()));
		return "schedule-weekly";
	}

	//deletes a given shift
	@RequestMapping("/remove")
	public String deleteShift(@RequestParam String shiftId, @RequestParam String id) {
		service.deleteShift(shiftId, id);
		return "redirect:/schedule?id=" + id;
	}

	//adds a shift to either the Master Schedule or an employee's schedule
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

	//returns shifts that are within a given time range
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
	
	//confirms you want to delete the employee
	@RequestMapping("/confirm-delete")
	public String confirmDelete(Model model, @RequestParam(required=false) String id) {
		
		Employee employee = service.getEmployee(id);
		model.addAttribute("id", employee.getId());
		model.addAttribute("firstname", employee.getFirstname());
		model.addAttribute("lastname", employee.getLastname());
		
		
		return "confirm-delete";
	}
	
	//confirms you want to delete a single shift
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
	
	//shows employee details jsp
	@RequestMapping("/employee-details")
	public String showEmployeeDetails(Model model, @RequestParam(required = false) String id) {
		
		Employee employee = service.getEmployee(id);
		model.addAttribute("employee", employee);
		
		return "employee-details";
	}
	
	@RequestMapping("/test")
	public String testDates(@RequestParam(required = false) String id,
			@RequestParam(required = false) String shiftName, @RequestParam(required = false) String date,
			@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
		
		System.out.println("Success");
		return "add-employee";
	}
	
	//add employee jsp
	@RequestMapping("/add-employee")
	public String showAddEmployee(Model model) {
		return "add-employee";
	}
	
	//shows ALL shifts on a weekly calendar view
	@RequestMapping("/weekly-calendar")
	public String displayWeek(Model model, @RequestParam(required = false) String date) {

		// If page is entered with no params (clicking on weekly view instead of either
		// of the arrows)
		LocalDate today;
		if (date == null) {
			today = LocalDate.now();
		} else {
			today = LocalDate.parse(date);
		}
		date = today.toString();
		String displayToday = LocalDate.now().toString();
		model.addAttribute("displayToday", displayToday);
		
		// Stores the numbers to be printed for the current week
		List<LocalDate> dates = new ArrayList<LocalDate>(7);
		HashMap<String, ArrayList<Shift>> shifts;

		// determines the day num of the current day, so that we can determine how many
		// days to backpedal in order to point at sunday
		int dayOffset = calculateDayOfWeek(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
		today = today.minusDays(dayOffset);

		// Used for printing the correct day numbers of this week on the jsp
		for (int i = 0; i < 7; i++) {
			dates.add(today);
			today = today.plusDays(1);
		}
		
		shifts = service.getShiftsByTimeRange(dates.get(0).toString(), dates.get(dates.size() -1).toString());
	

		// Set today to the first day of this week
		today = today.minusDays(7);
		model.addAttribute("curWeekDate", today);
		model.addAttribute("curWeekMonthString", monthNumToString(today.getMonthValue()));
		// Set today to next weeks date
		today = today.plusDays(7);
		model.addAttribute("nextWeekDate", today.toString());
		// Set today to last weeks date
		today = today.minusDays(14);
		model.addAttribute("prevWeekDate", today.toString());
		// Day numbers to be printed
		model.addAttribute("dates", dates);
		model.addAttribute("shifts", shifts);
		shifts.forEach((key, value) -> System.out.println(key.toString() + ":" + value.toString()));
		// Set today to curDay for daily info section
		today = LocalDate.parse(date);
		model.addAttribute("curDayDate", today);
		model.addAttribute("curDayMonthString", monthNumToString(today.getMonthValue()));

		return "weekly-calendar";
	}
	
	@RequestMapping("/shift-details")
	public String displayShiftDetails(Model model, @RequestParam String id,
			@RequestParam String shiftId) {
		
		Employee employee = service.getEmployee(id);
		for (Shift shift : employee.getSchedule()) {
			if (shift.getId().equals(shiftId)) {
				model.addAttribute("shift", shift);
			}
		}
		
		model.addAttribute("employee", employee);
		
		return "shift-details";
		
	}
	
	//helper methods to calculate dates
	
	private static int calculateDayOfWeek(int day, int month, int year) {
		// Source:
		// https://artofmemory.com/blog/how-to-calculate-the-day-of-the-week/

		int[] monthCodes = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };
		int yearCode, monthCode, centuryCode = 6, dateNum, leapYearCode;
		yearCode = ((year % 100) + ((year % 100) / 4)) % 7;
		monthCode = monthCodes[month - 1];
		switch (year / 100) {
		case 17:
			centuryCode = 4;
			break;
		case 18:
			centuryCode = 2;
			break;
		case 19:
			centuryCode = 0;
			break;
		case 20:
			centuryCode = 6;
			break;
		case 21:
			centuryCode = 4;
			break;
		case 22:
			centuryCode = 2;
			break;
		case 23:
			centuryCode = 0;
			break;
		}
		dateNum = day;
		if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && (month == 0 || month == 1))
			leapYearCode = -1;
		else
			leapYearCode = 0;

		return (yearCode + monthCode + centuryCode + dateNum + leapYearCode) % 7;
	}

	/**
	 * Return the number of days in the given month
	 * 
	 * @param monthNum the number of the month [1-12]
	 * @param year     the year in gregorian calendar (eg. 2022)
	 * @return the number of days in the given month
	 */
	private static int numDaysInMonth(int monthNum, int year) {
		int leapYearCode = 0;
		int[] numDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		// Checks if its a leap year
		if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && (monthNum == 2))
			leapYearCode = 1;

		return numDays[monthNum - 1] + leapYearCode;
	}

	private static String monthNumToString(int monthNum) {
		switch (monthNum) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "Invalid month";
		}
	}
	// helper method to make sure month is always 2 digits (required by API call)
		public String monthToString(Integer month) {
			if (month.toString().length() == 1) {
				String newMonth = "0" + month;
				return newMonth;
			}
			return month.toString();
		}

		// helper method to make sure day is always 2 digits (required by API call)
		public String dayToString(Integer day) {
			if (day.toString().length() == 1) {
				String newDay = "0" + day;
				return newDay;
			}
			return day.toString();
		}

}
