package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public Appointment create(Patient patient) {
        System.out.println("What kind of appointment do you need? (MEDICAL/SURGERY/AESTHETIC)");
        AppointmentTypes type = AppointmentTypes.valueOf(ConsoleMenu.renderAndVerify(
                option -> EnumUtils.isValidEnum(AppointmentTypes.class, option.trim().toUpperCase(Locale.ROOT)),
                "Medical", "Surgery", "Aesthetic"
        ).trim().toUpperCase(Locale.ROOT));
        WorkingDays day = WorkingDays.valueOf(ConsoleMenu.renderAndVerify(
                option -> EnumUtils.isValidEnum(WorkingDays.class, option.trim().toUpperCase(Locale.ROOT)),
                "Which day of the week do you prefer the appointment in?"
        ).trim().toUpperCase(Locale.ROOT));
        Employee employee = employeeDAO.findAvailable(type.findAssociatedRole(), day).get(0);

        return new Appointment(type, day, patient, employee);
    }

    public void update(, Statuses status) {
        
    }

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }
}