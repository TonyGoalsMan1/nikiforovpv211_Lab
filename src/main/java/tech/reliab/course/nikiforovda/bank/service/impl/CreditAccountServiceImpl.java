package tech.reliab.course.nikiforovda.bank.service.impl;

import tech.reliab.course.nikiforovda.bank.entity.CreditAccount;
import tech.reliab.course.nikiforovda.bank.service.CreditAccountService;
import tech.reliab.course.nikiforovda.bank.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditAccountServiceImpl implements CreditAccountService {
    private final Map<Integer, CreditAccount> creditAccountsMap = new HashMap<>();
    private final UserService userService;

    public CreditAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public CreditAccount create(CreditAccount creditAccount) {
        if (creditAccount == null) {
            return null;
        }

        if (creditAccount.getMonthsAmount() < 1) {
            System.err.println("[ERROR] | CreditAccount - monthCount must be at least 1");
            return null;
        }

        if (creditAccount.getCreditSum() <= 0) {
            System.err.println("[ERROR] | CreditAccount - creditAmount must be positive");
            return null;
        }

        if (creditAccount.getBank() == null) {
            System.err.println("[ERROR] | CreditAccount - no bank");
            return null;
        }

        CreditAccount newAccount = new CreditAccount(creditAccount);
        creditAccountsMap.put(newAccount.getId(), newAccount);
        userService.addCreditAccount(newAccount.getUser().getId(), newAccount);

        return newAccount;
    }

    public CreditAccount getCreditAccountById(int id) {
        CreditAccount account = creditAccountsMap.get(id);
        if (account == null) {
            System.err.println("[ERROR] Credit account with id -> " + id + " is not found");
        }
        return account;
    }

    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<CreditAccount>(creditAccountsMap.values());
    }

    public boolean doMonthlyPayment(int id) {
        CreditAccount creditAccount = creditAccountsMap.get(id);
        if (creditAccount == null || creditAccount.getPaymentAccount() == null) {
            System.err.println("[ERROR] | CreditAccount - no account to take money from");
            return false;
        }

        final int monthlyPayment = creditAccount.getMonthlyPayment();
        final int paymentAccountBalance = creditAccount.getPaymentAccount().getBalance();

        if (paymentAccountBalance - monthlyPayment < 0) {
            System.err.println("[ERROR] | CreditAccount - not enough balance for monthly payment");
            return false;
        }

        creditAccount.getPaymentAccount().setBalance(paymentAccountBalance - monthlyPayment);
        creditAccount.setCreditSum(creditAccount.getCreditSum() - monthlyPayment);

        return true;
    }
}
