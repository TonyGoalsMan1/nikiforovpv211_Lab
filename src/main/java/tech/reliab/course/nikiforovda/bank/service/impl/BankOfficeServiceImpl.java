package tech.reliab.course.nikiforovda.bank.service.impl;


import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.entity.BankOffice;
import tech.reliab.course.nikiforovda.bank.entity.Employee;
import tech.reliab.course.nikiforovda.bank.service.BankOfficeService;
import tech.reliab.course.nikiforovda.bank.service.BankService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankOfficeServiceImpl implements BankOfficeService {
    private final Map<Integer, BankOffice> bankOfficesMap = new HashMap<>();
    private final Map<Integer, List<Employee>> employeesByBankOfficeIdMap = new HashMap<>();
    private final Map<Integer, List<BankAtm>> BankAtmsByBankOfficeIdMap = new HashMap<>();
    private final BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    public BankOffice create(BankOffice bankOffice) {
        if (bankOffice == null) {
            return null;
        }

        if (bankOffice.getTotalMoney() < 0) {
            System.err.println("[ERROR] | BankOffice -> total money must be non-negative");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.err.println("[ERROR] | BankOffice -> must have bank");
            return null;
        }

        if (bankOffice.getRentPrice() < 0) {
            System.err.println("[ERROR] | BankOffice -> rentPrice must be non-negative");
            return null;
        }

        BankOffice newOffice = new BankOffice(bankOffice);
        bankOfficesMap.put(bankOffice.getId(), newOffice);
        employeesByBankOfficeIdMap.put(newOffice.getId(), new ArrayList<>());
        BankAtmsByBankOfficeIdMap.put(newOffice.getId(), new ArrayList<>());
        bankService.addOffice(newOffice.getBank().getId(), newOffice);

        return newOffice;
    }

    public BankOffice getBankOfficeById(int id) {
        BankOffice office = bankOfficesMap.get(id);
        if (office == null) {
            System.err.println("[ERROR] Office with id -> " + id + " is not found");
        }
        return office;
    }

    public List<BankOffice> getAllBankOffices() {
        return new ArrayList<BankOffice>(bankOfficesMap.values());
    }

    public List<Employee> getAllEmployeesByOfficeId(int id) {
        return employeesByBankOfficeIdMap.get(id);
    }

    public boolean inputMoney(int id, int money) {
        BankOffice bankOffice = bankOfficesMap.get(id);
        if (bankOffice == null) {
            System.err.println("[ERROR] | BankOffice -> can not deposit money to not existing office");
            return false;
        }

        if (money <= 0) {
            System.err.println("[ERROR] | BankOffice -> can not deposit money -> deposit amount must be positive");
            return false;
        }

        if (!bankOffice.isCashOutputAvailable()) {
            System.err.println("[ERROR] | BankOffice -> can not deposit money -> office can not accept deposit");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney() + money);
        return true;
    }

    public boolean outputMoney(int id, int money) {
        BankOffice bankOffice = bankOfficesMap.get(id);
        if (bankOffice == null) {
            System.err.println("[ERROR] | BankOffice -> can not withdraw money from not existing office");
            return false;
        }

        if (money <= 0) {
            System.err.println("[ERROR] | BankOffice -> can not withdraw money -> withdraw amount must be positive");
            return false;
        }

        if (!bankOffice.isCashOutputAvailable()) {
            System.err.println("[ERROR] | BankOffice -> can not withdraw money -> office can not give withdrawal");
            return false;
        }

        if (bankOffice.getTotalMoney() - money < 0) {
            System.err.println("[ERROR] | BankOffice -> can not withdraw money -> office does not have enough money");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney() - money);
        return true;
    }

    public boolean installAtm(int id, BankAtm bankAtm) {
        BankOffice bankOffice = bankOfficesMap.get(id);
        if (bankOffice != null && bankAtm != null) {
            if (!bankOffice.isAtmPlaceable()) {
                System.err.println("[ERROR] | BankOffice -> can not install atm");
                return false;
            }

            bankOffice.setAtmsAmount(bankOffice.getAtmsAmount() + 1);
            bankOffice.getBank().setAtmsAmount(bankOffice.getBank().getAtmsAmount() + 1);
            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankAtm.setBank(bankOffice.getBank());
            List<BankAtm> bankAtms = BankAtmsByBankOfficeIdMap.get(bankOffice.getId());
            bankAtms.add(bankAtm);
            return true;
        }
        return false;
    }

    public boolean removeAtm(int id, BankAtm bankAtm) {
        BankOffice bankOffice = bankOfficesMap.get(id);
        if (bankOffice != null && bankAtm != null) {
            if (bankOffice.getAtmsAmount() - 1 < 0) {
                System.err.println("[ERROR] | BankOffice -> can not remove ATM, no ATMs");
                return false;
            }

            bankOffice.setAtmsAmount(bankOffice.getAtmsAmount() - 1);
            return true;
        }
        return false;
    }

    public boolean addEmployee(int id, Employee employee) {
        BankOffice bankOffice = bankOfficesMap.get(id);
        if (bankOffice != null && employee != null) {
            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());
            List<Employee> officeEmployees = employeesByBankOfficeIdMap.get(bankOffice.getId());
            officeEmployees.add(employee);
            return true;
        }
        return false;
    }

    public boolean removeEmployee(int id, Employee employee) {
        BankOffice bankOffice = bankOfficesMap.get(id);
        if (bankOffice != null && employee != null) {
            List<Employee> officeEmployees = employeesByBankOfficeIdMap.get(bankOffice.getId());
            officeEmployees.remove(employee);
            return true;
        }
        return false;
    }
}
