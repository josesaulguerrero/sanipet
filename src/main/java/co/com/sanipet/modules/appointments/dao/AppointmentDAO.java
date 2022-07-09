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

    public Optional<Appointment> findById(String Id) {
        return Optional.of(
                appointments.stream().filter(appointment -> appointment.getId().equals(Id)).collect(Collectors.toList()).get(0)
        );
    }

    public void update(String Id, Statuses status) {
        Optional<Appointment> appointment = findById(Id);
        if(appointment.isPresent()) {
            appointment.get().setStatus(status);
        } else {
            throw new IllegalArgumentException("The appointment with the given id doesn't exist");
        }
    }

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }
}