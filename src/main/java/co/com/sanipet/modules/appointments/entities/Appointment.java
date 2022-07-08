package co.com.sanipet.modules.appointments.entities;

/*
* Class that represents an appointment
*/
public class Appointment {
    // Attributes
    Statuses status;
    AppointmentTypes type;
    WorkingDays date;
    Patient patient;
    Employee employeeInCharge;
    Double appointmentCost;

    // Constructor
    public Appointment(AppointmentTypes type, WorkingDays date, Patient patient, Employee employee) {
        this.status = Statuses.NOT_STARTED;
        this.type = type;
        this.date = date;
        this.patient = patient;
        this.employeeInCharge = employee;
        this.appointmentCost = this.type == AppointmentTypes.SURGERY ? 100.0 : 50.0; // surgery is 100 and aesthetic
        // or medical is 50
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
