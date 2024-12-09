package tech.reliab.course.nikiforovda.bank.service.impl;

import tech.reliab.course.nikiforovda.bank.entity.PaymentAccount;
import tech.reliab.course.nikiforovda.bank.service.PaymentAccountService;
import tech.reliab.course.nikiforovda.bank.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private final Map<Integer, PaymentAccount> paymentAccountsMap = new HashMap<>();
    private final UserService userService;

    public PaymentAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance() < 0) {
            System.err.println("[ERROR] | PaymentAccount -> payment account balance must be non-negative");
            return null;
        }

        PaymentAccount newAccount = new PaymentAccount(paymentAccount);
        paymentAccountsMap.put(newAccount.getId(), newAccount);
        userService.addPaymentAccount(newAccount.getUser().getId(), newAccount);

        return newAccount;
    }

    public PaymentAccount getPaymentAccountById(int id) {
        PaymentAccount account = paymentAccountsMap.get(id);
        if (account == null) {
            System.err.println("[ERROR] Payment account with id " + id + " is not found");
        }
        return account;
    }

    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<PaymentAccount>(paymentAccountsMap.values());
    }

    public boolean inputMoney(int id, int money) {
        PaymentAccount paymentAccount = paymentAccountsMap.get(id);
        if (paymentAccount == null) {
            System.err.println("[ERROR] | PaymentAccount -> non existing payment account");
            return false;
        }

        if (money <= 0) {
            System.err.println("[ERROR] | PaymentAccount -> deposit amount must be positive");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance() + money);
        return true;
    }

    public boolean outputMoney(int id, int money) {
        PaymentAccount paymentAccount = paymentAccountsMap.get(id);
        if (paymentAccount == null) {
            System.err.println("[ERROR] | PaymentAccount -> non existing payment account");
            return false;
        }

        if (money <= 0) {
            System.err
                    .println("[ERROR] | PaymentAccount -> withdrawal amount must be positive");
            return false;
        }

        if (paymentAccount.getBalance() - money < 0) {
            System.err.println("[ERROR] |PaymentAccount -> not enough money");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance() - money);
        return true;
    }
}
