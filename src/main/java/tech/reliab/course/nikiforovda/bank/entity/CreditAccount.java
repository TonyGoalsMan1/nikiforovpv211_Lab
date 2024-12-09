package tech.reliab.course.nikiforovda.bank.entity;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class CreditAccount extends Account {
    private LocalDate dateStart = null;
    private LocalDate dateEnd = null;
    private int monthsAmount = 0;
    private int creditSum = 0;
    private int monthlyPayment = 0;
    private int interestRate = 0;
    private Employee employee = null;
    private PaymentAccount paymentAccount = null;

    public CreditAccount(CreditAccount creditAccount) {
        super(creditAccount.id, creditAccount.user, creditAccount.bank);
        dateStart = creditAccount.dateStart;
        dateEnd = creditAccount.dateEnd;
        monthsAmount = creditAccount.monthsAmount;
        creditSum = creditAccount.creditSum;
        monthlyPayment = creditAccount.monthlyPayment;
        interestRate = creditAccount.interestRate;
        employee = new Employee(creditAccount.employee);
        paymentAccount = new PaymentAccount(creditAccount.paymentAccount);
    }

    public CreditAccount(int id, User user, Bank bank, LocalDate dateStart, LocalDate dateEnd,
                         int monthAmount, int creditSum, int monthlyPayment, int interestRate,
                         Employee employee, PaymentAccount paymentAccount) {
        super(id, user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthsAmount = monthAmount;
        this.creditSum = creditSum;
        this.monthlyPayment = monthlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }
}
