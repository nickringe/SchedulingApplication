package co.grandcircus.EmployeeApi;

public class EmployeeAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EmployeeAlreadyExistsException(Integer id) {
		super("Employee with id: " + id + " already exists");
	}

}
