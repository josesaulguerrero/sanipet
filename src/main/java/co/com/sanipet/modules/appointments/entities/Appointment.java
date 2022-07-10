package co.com.sanipet.modules.appointments.entities;

import co.com.sanipet.utils.HashGenerator;

import java.time.LocalDate;

/*
* Class that represents an appointment
*/
public class Appointment {
    // Attributes
    String id;
    Statuses status;
    AppointmentTypes type;
    LocalDate date;
    Patient patient;
    Employee employeeInCharge;
    Double appointmentCost;

    // Constructor
    public Appointment(AppointmentTypes type, LocalDate date, Patient patient, Employee employee) {
        this.id = HashGenerator.generateRandomAlphanumericString(10);
        this.status = Statuses.PENDING;
        this.type = type;
        this.date = date;
        this.patient = patient;
        this.employeeInCharge = employee;
        this.appointmentCost = this.type == AppointmentTypes.SURGERY ? 100.0 : 50.0; // surgery is 100 and aesthetic
        // or medical is 50
    }

    public String getId() {
        return id;
    }

    public Statuses getStatus() {
        return status;
    }

    public AppointmentTypes getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Employee getEmployeeInCharge() {
        return employeeInCharge;
    }

    public Double getAppointmentCost() {
        return appointmentCost;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "status=" + status +
                ", type=" + type +
                ", date=" + date +
                ", patient=" + patient +
                ", employeeInCharge=" + employeeInCharge +
                '}';
    }
}
