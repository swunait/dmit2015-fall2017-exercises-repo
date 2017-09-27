package chinook.data;

import chinook.model.Employee;

public class EmployeeRepository extends AbstractJpaRepository<Employee> {
	private static final long serialVersionUID = 1L;

	public EmployeeRepository() {
		super(Employee.class);
	}

}
