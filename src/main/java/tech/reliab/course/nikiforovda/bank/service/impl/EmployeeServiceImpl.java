package tech.reliab.course.nikiforovda.bank.service.impl;

import tech.reliab.course.nikiforovda.bank.entity.Employee;
import tech.reliab.course.nikiforovda.bank.service.BankOfficeService;
import tech.reliab.course.nikiforovda.bank.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private final Map<Integer, Employee> employeesMap = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    public EmployeeServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getSalary() < 0) {
            System.err.println("[ERROR] | Employee -> salary must be non-negative");
            return null;
        }

        Employee newEmployee = new Employee(employee);
        employeesMap.put(newEmployee.getId(), newEmployee);
        bankOfficeService.addEmployee(newEmployee.getBankOffice().getId(), newEmployee);

        return newEmployee;
    }

    public Employee getEmployeeById(int id) {
        Employee employee = employeesMap.get(id);
        if (employee == null) {
            System.err.println("[ERROR] Employee with id ->" + id + " is not found");
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<Employee>(employeesMap.values());
    }
}