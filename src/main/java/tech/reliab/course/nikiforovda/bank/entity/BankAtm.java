package tech.reliab.course.nikiforovda.bank.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class BankAtm {
    public enum Status {NOT_WORKING, WORKING, NO_MONEY}

    public static int counterId;

    private int id = 0;
    private String name = "-";
    private String address = "-";
    private Status status = Status.NOT_WORKING;
    private Bank bank = null;
    private BankOffice bankOffice = null;
    private Employee employee = null;
    private boolean isCashOutputAvailable = false;
    private boolean isCashInputAvailable = false;
    private long totalMoney = 0;
    private int maintenancePrice = 0;

    // special initialization with incrementing global counter
    private void initId() {
        id = counterId++;
    }

    public BankAtm() {
        initId();
    }

    public BankAtm(String name, String address) {
        initId();
        this.name = name;
        this.address = address;
    }

    public BankAtm(String name, String address, Status status, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashOutputAvailable, boolean isCashInputAvailable,
                   long totalMoney, int maintenancePrice) {
        initId();
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashOutputAvailable = isCashOutputAvailable;
        this.isCashInputAvailable = isCashInputAvailable;
        this.totalMoney = totalMoney;
        this.maintenancePrice = maintenancePrice;
    }

    public BankAtm(BankAtm bankAtm) {
        this.id = bankAtm.id;
        this.name = bankAtm.name;
        this.address = bankAtm.address;
        this.status = bankAtm.status;
        this.bank = new Bank(bankAtm.bank);
        this.bankOffice = new BankOffice(bankAtm.bankOffice);
        this.employee = new Employee(bankAtm.employee);
        this.isCashOutputAvailable = bankAtm.isCashOutputAvailable;
        this.isCashInputAvailable = bankAtm.isCashInputAvailable;
        this.totalMoney = bankAtm.totalMoney;
        this.maintenancePrice = bankAtm.maintenancePrice;
    }
}
