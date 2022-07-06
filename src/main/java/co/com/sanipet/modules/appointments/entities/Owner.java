package co.com.sanipet.modules.appointments.entities;

public class Owner extends Person {
    private final Integer age;

    public Owner(String DNI, String name, String surname, String cellphone,  Integer age) {
        this.age = age;
        this.DNI = DNI;
        this.cellphone = cellphone;
        this.name = name;
        this.surname = surname;
    }
}
