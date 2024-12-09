package tech.reliab.course.nikiforovda.bank.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Person {
    private static int counterId;
    protected int id = 0;
    protected String name = "-";
    protected LocalDate birthdayDate = null;

    // special initialization with incrementing global counter
    private void initId() {
        id = counterId++;
    }

    public Person() {
        initId();
    }

    public Person(Person person) {
        id = person.id;
        name = person.name;
        birthdayDate = person.birthdayDate;
    }

    public Person(String name) {
        initId();
        this.name = name;
    }

    public Person(String name, LocalDate birthdayDate) {
        initId();
        this.name = name;
        this.birthdayDate = birthdayDate;
    }
}