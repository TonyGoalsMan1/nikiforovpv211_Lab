package tech.reliab.course.nikiforovda.bank;

import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.entity.BankOffice;
import tech.reliab.course.nikiforovda.bank.entity.User;
import tech.reliab.course.nikiforovda.bank.entity.CreditAccount;
import tech.reliab.course.nikiforovda.bank.entity.Employee;
import tech.reliab.course.nikiforovda.bank.entity.PaymentAccount;
import tech.reliab.course.nikiforovda.bank.service.BankAtmService;
import tech.reliab.course.nikiforovda.bank.service.BankOfficeService;
import tech.reliab.course.nikiforovda.bank.service.BankService;
import tech.reliab.course.nikiforovda.bank.service.CreditAccountService;
import tech.reliab.course.nikiforovda.bank.service.EmployeeService;
import tech.reliab.course.nikiforovda.bank.service.UserService;
import tech.reliab.course.nikiforovda.bank.service.PaymentAccountService;
import tech.reliab.course.nikiforovda.bank.service.impl.BankAtmServiceImpl;
import tech.reliab.course.nikiforovda.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.nikiforovda.bank.service.impl.BankServiceImpl;
import tech.reliab.course.nikiforovda.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.nikiforovda.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.nikiforovda.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.nikiforovda.bank.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        bankService.setBankOfficeService(bankOfficeService);
        EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
        BankAtmService bankAtmService = new BankAtmServiceImpl(bankOfficeService);
        UserService userService = new UserServiceImpl(bankService);
        bankService.setUserService(userService);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService);

        for (int i = 1; i < 6; i++) {
            String name = "BIG MONEY BANK #" + i;
            bankService.create(new Bank(name));
        }

        List<Bank> bankList = bankService.getAllBanks();
        for (Bank bank: bankList) {
            for (int i = 1; i < 3; i++) {
                bankOfficeService.create(new BankOffice(
                        "Office #" + i + " of " + bank.getName(),
                        "street WALLSTREET, house Bankovskaya #" + i,
                        bank,
                        true,
                        true,
                        0,
                        true,
                        true,
                        true,
                        17000,
                        100 * i
                ));
            }
        }

        List<BankOffice> officeList = bankOfficeService.getAllBankOffices();
        for (BankOffice office: officeList) {
            for (int i = 1; i < 4; i++) {
                employeeService.create(new Employee(
                        "Antony Nikiforov's Version #" + i,
                        LocalDate.of(2000, 2, 11),
                        "Cleaner",
                        office.getBank(),
                        false,
                        office,
                        false,
                        5000));
            }
        }

        // adding Atms
        for (BankOffice office: officeList) {
            for (int i = 1; i < 4; i++) {
                bankAtmService.create(new BankAtm(
                        "Super ATM #" + i + " of 'BIG MONEY BANK'",
                        office.getAddress(),
                        BankAtm.Status.WORKING,
                        office.getBank(),
                        office,
                        bankOfficeService.getAllEmployeesByOfficeId(office.getId())
                                .getFirst(),
                        true,
                        true,
                        0,
                        17));
            }
        }

        for (Bank bank: bankList) {
            System.out.println(bank.toString());
        }

        System.out.print('\n');

        for (BankOffice office: officeList) {
            System.out.println(office.toString());
        }
    }
}