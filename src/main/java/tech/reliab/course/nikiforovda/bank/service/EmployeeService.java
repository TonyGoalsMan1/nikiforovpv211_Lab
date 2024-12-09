package tech.reliab.course.nikiforovda.bank.service;

import tech.reliab.course.nikiforovda.bank.entity.BankOffice;
import tech.reliab.course.nikiforovda.bank.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();
}
