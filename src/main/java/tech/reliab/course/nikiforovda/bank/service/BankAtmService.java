package tech.reliab.course.nikiforovda.bank.service;

import tech.reliab.course.nikiforovda.bank.entity.BankAtm;

import java.util.List;

public interface BankAtmService {
    // create bank
    BankAtm create(BankAtm bankAtm);

    BankAtm getBankAtmById(int id);

    List<BankAtm> getAllBankAtms();

    // input money
    boolean inputMoney(BankAtm bankAtm, int money);

    // output money
    boolean outputMoney(BankAtm bankAtm, int money);
}
