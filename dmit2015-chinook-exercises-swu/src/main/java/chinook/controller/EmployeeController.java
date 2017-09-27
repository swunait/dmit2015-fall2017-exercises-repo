package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import chinook.data.EmployeeRepository;
import chinook.model.Employee;

@Model
public class EmployeeController {

	@Inject
	private EmployeeRepository employeeRepository;
	
	private List<Employee> employees;
	
	@PostConstruct
	void init() {
		employees = employeeRepository.findAll();
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	
}
