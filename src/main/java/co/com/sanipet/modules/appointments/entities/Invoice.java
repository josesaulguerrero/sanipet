package co.com.sanipet.modules.appointments.entities;

import co.com.sanipet.modules.stock.entities.Medicine;

public class Invoice {
    // Attributes
    private final Medicine medicine;
    private final Appointment appointment;
    private static final Double taxPercentage = 0.19;
    private final Integer total;

    public Invoice(Medicine medicine, Appointment appointment, Integer total) {
        this.medicine = medicine;
        this.appointment = appointment;
        this.total = total;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public Integer getTotal() {
        return total;
    }
}
