package tech.reliab.course.nikiforovda.bank.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class PaymentAccount extends Account {
    private int balance = 0;

    public PaymentAccount(int id, User user, Bank bank, int balance) {
        super(id, user, bank);
        this.balance = balance;
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        super(paymentAccount.id, paymentAccount.user, paymentAccount.bank);
        balance = paymentAccount.balance;
    }
}
