package tech.reliab.course.nikiforovda.bank.service.impl;

import lombok.Setter;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.service.BankAtmService;
import tech.reliab.course.nikiforovda.bank.service.BankOfficeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAtmServiceImpl implements BankAtmService {
    private final Map<Integer, BankAtm> BankAtmsMap = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    public BankAtm create(BankAtm bankAtm) {
        if (bankAtm == null) {
            return null;
        }
        if (bankAtm.getTotalMoney() < 0) {
            System.err.println("[ERROR] Can not create BankAtm - totalMoney must be non-negative");
            return null;
        }
        if (bankAtm.getMaintenancePrice() < 0) {
            System.err.println("[ERROR] Can not create BankAtm - maintenancePrice must be non-negative");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("[ERROR] Can not create BankAtm - bankOffice can not be null");
            return null;
        }

        BankAtm bankAtmCopy = new BankAtm(bankAtm);
        BankAtmsMap.put(bankAtmCopy.getId(), bankAtmCopy);
        bankOfficeService.installAtm(bankAtmCopy.getBankOffice().getId(), bankAtmCopy);
        return new BankAtm(bankAtm);
    }

    public BankAtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    public BankAtm getBankAtmById(int id) {
        BankAtm atm = BankAtmsMap.get(id);
        if (atm == null) {
            System.err.println("Atm with id " + id + " is not found");
        }
        return atm;
    }

    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(BankAtmsMap.values());
    }

    public boolean inputMoney(BankAtm bankAtm, int money) {
        if (bankAtm == null) {
            System.err.println("[ERROR] BankAtm can not deposit money - non existing ATM");
            return false;
        }
        if (money <= 0) {
            System.err.println("[ERROR] BankAtm can not deposit money - amount is not positive");
            return false;
        }
        if (!bankAtm.isCashOutputAvailable()) {
            System.err.println("[ERROR] BankAtm can not deposit money - deposit is not allowed");
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney() + money);
        return true;
    }

    public boolean outputMoney(BankAtm bankAtm, int money) {
        if (bankAtm == null) {
            System.err.println("[ERROR] BankAtm can not withdraw money - non existing ATM");
            return false;
        }
        if (money <= 0) {
            System.err.println("[ERROR] BankAtm can not withdraw money - amount is not positive");
            return false;
        }
        if (!bankAtm.isCashOutputAvailable()) {
            System.err.println("[ERROR] BankAtm can not withdraw money - deposit is not allowed");
            return false;
        }
        if (bankAtm.getTotalMoney() - money < 0) {
            System.err.println("[ERROR] BankAtm can not withdraw money - ATM does not have enough money");
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney() - money);
        return true;
    }
}
