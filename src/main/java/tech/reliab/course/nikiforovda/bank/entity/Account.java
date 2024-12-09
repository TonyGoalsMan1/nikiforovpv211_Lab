package tech.reliab.course.nikiforovda.bank.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Account {
    private static int counterId;
    protected int id = 0;
    protected User user = null;
    protected Bank bank = null;

    // special initialization with incrementing global counter
    private void initId() {
        id = counterId++;
    }

    public Account() {
        initId();
    }

    public Account(Account account) {
        id = account.id;
        user = new User(account.user);
        bank = new Bank(account.bank);
    }

    public Account(User user, Bank bank) {
        initId();
        this.user = user;
        this.bank = bank;
    }
}