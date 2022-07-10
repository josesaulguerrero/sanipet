package co.com.sanipet.modules.appointments.entities;

import co.com.sanipet.modules.stock.entities.Medicine;

import java.util.Map;

public class Invoice {
    // Attributes
    private final Map<Medicine, Integer> medicines;
    private final Appointment appointment;
    private static final Double taxPercentage = 0.19;
    private final Double subtotal;
    private final Double total;

    public Invoice(Map<Medicine, Integer> medicines, Appointment appointment, Double total) {
        this.medicines = medicines;
        this.appointment = appointment;
        this.subtotal = total;
        this.total = this.subtotal + (this.subtotal * taxPercentage);
    }

    public Map<Medicine, Integer> getMedicine() {
        return medicines;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "medicines=" + medicines +
                ", appointment=" + appointment +
                ", subtotal=" + subtotal +
                ", total=" + total +
                '}';
    }
}
