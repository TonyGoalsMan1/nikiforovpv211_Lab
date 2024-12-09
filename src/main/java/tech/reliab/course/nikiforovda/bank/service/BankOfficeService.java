package tech.reliab.course.nikiforovda.bank.service;

import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.entity.BankOffice;
import tech.reliab.course.nikiforovda.bank.entity.Employee;

import java.util.List;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice);

    BankOffice getBankOfficeById(int id);

    List<BankOffice> getAllBankOffices();

    List<Employee> getAllEmployeesByOfficeId(int id);

    boolean installAtm(int id, BankAtm bankAtm);

    boolean removeAtm(int id, BankAtm bankAtm);

    boolean inputMoney(int id, int money);

    boolean outputMoney(int id, int money);

    boolean addEmployee(int id, Employee employee);

    boolean removeEmployee(int id, Employee employee);
}
