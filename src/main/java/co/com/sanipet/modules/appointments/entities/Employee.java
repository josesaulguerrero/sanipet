package co.com.sanipet.modules.appointments.entities;

public class Employee extends Person {
    Roles role;
    Schedule schedule;

    public Employee(String DNI, String name, String surname, String cellphone, Roles role, WorkingDays[] activeDays) {
        this.DNI = DNI;
        this.cellphone = cellphone;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.schedule = new Schedule(activeDays);
    }

    public String getDNI() {
        return this.DNI;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Roles getRole() {
        return role;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Employee{" + "role=" + role + ", schedule=" + schedule + ", DNI='" + DNI + '\'' + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", cellphone='" + cellphone + '\'' + '}';
    }
}
