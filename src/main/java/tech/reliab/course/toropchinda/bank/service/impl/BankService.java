package tech.reliab.course.toropchinda.bank.service.impl;
import tech.reliab.course.toropchinda.bank.entity.Bank;

public interface BankService {
    void addOffice(Bank bank);
    void addAtm(Bank bank);
    void addEmployee(Bank bank);
    void addClient(Bank bank);
}
