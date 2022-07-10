package co.com.sanipet.modules.appointments.entities;

import co.com.sanipet.modules.stock.entities.Medicine;

import java.util.Map;

public class Invoice {
    // Attributes
    private final Map<Medicine, Integer> medicines;
    private final Appointment appointment;
    private static final Double taxPercentage = 0.19;
    private final Double subtotal;

    public Invoice(Map<Medicine, Integer> medicines, Appointment appointment, Double total) {
        this.medicines = medicines;
        this.appointment = appointment;
        this.subtotal = total;
    }

    public Map<Medicine, Integer> getMedicine() {
        return medicines;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public Double getSubtotal() {
        return subtotal;
    }
}
