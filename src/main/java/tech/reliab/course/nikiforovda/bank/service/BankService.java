package tech.reliab.course.nikiforovda.bank.service;

import tech.reliab.course.nikiforovda.bank.entity.*;

import java.util.List;

public interface BankService {
    // create bank
    Bank create(Bank bank);

    Bank getBankById(int id);

    List<Bank> getAllBanks();

    void setBankOfficeService(BankOfficeService bankOfficeService);

    List<BankOffice> getAllBankOfficesByBankId(int id);

    void setUserService(UserService userService);

    // add office
    boolean addOffice(int id, BankOffice bankOffice);

    // delete office from bank
    boolean deleteOffice(int id, BankOffice bankOffice);

//    // add atm
//    boolean addAtm(int id, BankAtm bankAtm);
//
//    // delete atm from bank
//    boolean deleteAtm(int id, BankAtm bankAtm);
//
//    // add employee
//    boolean addEmployee(int id, Employee employee);
//
//    // delete employee
//    boolean deleteEmployee(int id, Employee employee);

    // add user
    boolean addUser(int id, User user);

    // delete user from bank
    boolean deleteUser(int id, User user);

    // calculate interest rate
    double calculateInterestRate(Bank bank);

    // input money
    boolean inputMoney(int id, long money);

    // output money
    boolean outputMoney(int id, long money);
}
