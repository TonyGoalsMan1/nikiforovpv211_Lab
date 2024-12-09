package tech.reliab.course.nikiforovda.bank.service.impl;

import tech.reliab.course.nikiforovda.bank.entity.CreditAccount;
import tech.reliab.course.nikiforovda.bank.entity.PaymentAccount;
import tech.reliab.course.nikiforovda.bank.entity.User;
import tech.reliab.course.nikiforovda.bank.service.BankService;
import tech.reliab.course.nikiforovda.bank.service.UserService;

import java.util.*;

public class UserServiceImpl implements UserService {
    private final Map<Integer, User> usersMap = new HashMap<>();
    private final Map<Integer, List<PaymentAccount>> paymentAccountsByUserIdMap = new HashMap<>();
    private final Map<Integer, List<CreditAccount>> creditAccountsByUserIdMap = new HashMap<>();
    private final BankService bankService;

    public UserServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    public User create(User user) {
        if (user == null) {
            return null;
        }

        if (user.getBank() == null) {
            System.err.println("[ERROR] User must have bank");
            return null;
        } else {
            User newUser = new User(user);

            Random random = new Random();
            final int monthlyIncome = random.nextInt() * 10000;
            newUser.setMonthlyIncome(monthlyIncome);
            calculateCreditRating(newUser);

            usersMap.put(newUser.getId(), newUser);
            paymentAccountsByUserIdMap.put(newUser.getId(), new ArrayList<>());
            creditAccountsByUserIdMap.put(newUser.getId(), new ArrayList<>());
            bankService.addUser(newUser.getBank().getId(), newUser);

            return newUser;
        }
    }

    public User getUserById(int id) {
        User user = usersMap.get(id);
        if (user == null) {
            System.err.println("[ERROR] Client with id -> " + id + " is not found");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(usersMap.values());
    }

    public boolean addPaymentAccount(int id, PaymentAccount account) {
        User client = usersMap.get(id);
        if (client != null) {
            List<PaymentAccount> userCreditAccounts = paymentAccountsByUserIdMap.get(id);
            userCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    public boolean addCreditAccount(int id, CreditAccount account) {
        User user = usersMap.get(id);
        if (user != null) {
            List<CreditAccount> userCreditAccounts = creditAccountsByUserIdMap.get(id);
            userCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    public List<PaymentAccount> getAllPaymentAccountsByUserId(int id) {
        return paymentAccountsByUserIdMap.get(id);
    }

    public List<CreditAccount> getAllCreditAccountsByUserId(int id) {
        return creditAccountsByUserIdMap.get(id);
    }

    public int calculateCreditRating(User user) {
        user.setCreditRating(user.getMonthlyIncome() / 10);
        return user.getCreditRating();
    }

}