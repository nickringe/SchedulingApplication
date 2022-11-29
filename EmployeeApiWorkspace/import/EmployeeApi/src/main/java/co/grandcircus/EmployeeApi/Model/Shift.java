package co.grandcircus.EmployeeApi.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//web api model (8080)
public class Shift {
	
	private String id;
	private String shiftName;
	private String date;
	private String startTime;
	private String endTime;
	private Double shiftLength;
	
	public Shift() {
	}
	
	public Shift(String shiftName, String date, String startTime, String endTime) {
		this.shiftName = shiftName;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;		
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDateString() {
		LocalDate dateString = LocalDate.parse(date);
		String dayString1 = dateString.getDayOfWeek().toString().substring(0,1);
		String dayString2 = dateString.getDayOfWeek().toString().substring(1,3).toLowerCase();
		return dayString1 + dayString2 + " " + dateString.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getShiftLength() {
		return shiftLength;
	}

	public void setShiftLength(Double shiftLength) {
		this.shiftLength = shiftLength;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStartTimeString() {
		String formattedDateTime = date + "T" + startTime;
		LocalDateTime startString = LocalDateTime.parse(formattedDateTime);
		return startString.format(DateTimeFormatter.ofPattern("hh:MM a"));
		
	}
	
	public String getEndTimeString() {
		String formattedDateTime = date + "T" + endTime;
		LocalDateTime endString = LocalDateTime.parse(formattedDateTime);
		return endString.format(DateTimeFormatter.ofPattern("hh:MM a"));
		
	}
	
	
	


}
