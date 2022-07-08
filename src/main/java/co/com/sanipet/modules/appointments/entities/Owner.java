package co.com.sanipet.modules.appointments.entities;

public class Owner extends Person {
    private final Integer age;

    public Owner(String DNI, String name, String surname, String cellphone, Integer age) {
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
        this.cellphone = cellphone;
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getDNI() {
        return this.DNI;
    }

    @Override
    public String toString() {
        return "Owner{" + "age=" + age + ", DNI='" + DNI + '\'' + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", cellphone='" + cellphone + '\'' + '}';
    }
}
