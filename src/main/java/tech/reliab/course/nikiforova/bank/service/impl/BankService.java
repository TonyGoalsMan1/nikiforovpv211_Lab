package tech.reliab.course.nikiforova.bank.service.impl;
import tech.reliab.course.nikiforova.bank.entity.Bank;

public interface BankService {
    void addOffice(Bank bank);
    void addAtm(Bank bank);
    void addEmployee(Bank bank);
    void addClient(Bank bank);
}
