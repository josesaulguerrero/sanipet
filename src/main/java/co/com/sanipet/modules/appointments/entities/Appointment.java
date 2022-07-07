package co.com.sanipet.modules.appointments.entities;

import java.time.LocalDate;

public class Appointment {
    Statuses status = Statuses.NOT_STARTED;
    AppointmentTypes type;
    WorkingDays date;
    Patient patient;
    Employee employee;

    public Appointment(AppointmentTypes type, WorkingDays date, Patient patient, Employee employee) {
        this.type = type;
        this.date = date;
        this.patient = patient;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }
}
