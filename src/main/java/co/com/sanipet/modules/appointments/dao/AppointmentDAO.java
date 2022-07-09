package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.EnumUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/*
*Class that represents the appointments in memory
*/
public class AppointmentDAO {

    // Attributes
    private static final List<Appointment> appointments = new ArrayList<>();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Appointment> findAll() {
        return appointments;
    }

    public Appointment findById(String id) {
        return appointments
                .stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The appointment with the given id doesn't exist."));
    }

    public boolean exists(String id) {
        return Optional.ofNullable(findById(id)).isPresent();
    }

    public void update(String id, Statuses status) {
        Appointment appointment = findById(id);
        appointment.setStatus(status);
    }

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }
}