package tech.reliab.course.toropchinda.bank.entity;

public class Bank {
    private String id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int clientCount;
    private int ratingOfBank;
    private double totalMoney;
    private double interestRate;

    public Bank(String id, String name, int ratingOfBank, double totalMoney) {
        this.id = id;
        this.name = name;
        this.ratingOfBank = ratingOfBank;
        this.totalMoney = totalMoney;
        this.officeCount = 0;
        this.atmCount = 0;
        this.employeeCount = 0;
        this.clientCount = 0;
        this.interestRate =  calculateInterestRate(rating);

    }
}
//12. Описание сущностей банка (7 штук):
//Bank – объект банк, содержит поля:
//        • Id банка
//• Имя банка
//• Кол-во офисов (заполняется и считается автоматически при добавлении
//        нового офиса), по умолчанию 0
//        • Кол-во банкоматов (заполняется и считается автоматически при
//        добавлении нового банкомата), по умолчанию 0
//        3
//        • Кол-во сотрудников (заполняется и считается автоматически при
//        добавлении нового сотрудника), по умолчанию 0
//        • Кол-во клиентов (заполняется и считается автоматически при добавлении
//        нового клиента в банк), по умолчанию 0
//        • Рейтинг банка (генерируется рандомно, от 0 до 100, где 100 наивысший
//        балл, чем выше рейтинг банка, тем меньше должна быть процентная ставка)
//• Всего денег в банке (генерируется рандомно, но не более 1 000 000)
//• Процентная ставка (генерируется рандомно, но не более 20%, однако
//        нужно учитывать рейтинг банка, чем он выше, тем ставка должна
//                             сгенерироваться меньше