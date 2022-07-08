package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> findAll() {
        return appointments;
    }

    public void create(Appointment appointment) {
        appointments.add(appointment);
    }
}