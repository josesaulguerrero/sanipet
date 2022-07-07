package co.com.sanipet.modules.appointments.entities;

public class Appointment {
    Statuses status = Statuses.NOT_STARTED;
    AppointmentTypes type;
    WorkingDays date;
    Patient patient;
    Employee employeeInCharge;

    public Appointment(AppointmentTypes type, WorkingDays date, Patient patient, Employee employee) {
        this.type = type;
        this.date = date;
        this.patient = patient;
        this.employeeInCharge = employee;
    }

    public Statuses getStatus() {
        return status;
    }

    public AppointmentTypes getType() {
        return type;
    }

    public WorkingDays getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Employee getEmployeeInCharge() {
        return employeeInCharge;
    }
}
