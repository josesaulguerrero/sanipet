package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.EnumUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

    public Optional<Appointment> findById(String Id) {
        return appointments.stream().reduce((accumulator, element) -> {
            if (element.getId().equals(Id)) {
                accumulator = element;
            }
            return accumulator;
        });
    }

    public boolean appointmentExists(String Id) {
        return appointments.stream().anyMatch(appointment -> appointment.getId().equals(Id));
    }

    public boolean isAppointmentCancellable(String Id) {
        int now = LocalDate.now().getDayOfWeek().getValue() - 1;
        int appointmentDay = findById(Id).get().getDate().getWeekday();
        return appointmentDay - now > 1;
    }

    public void update(String Id, Statuses status) {
        if (isAppointmentCancellable(Id) || !status.equals(Statuses.CANCELLED)){
            findById(Id).get().setStatus(status);
            System.out.println("The appointment has been updated successfully");
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println("The appointment couldn't be updated.");
            System.out.println("-----------------------------------------------" +
                    "-----");
        }
    }

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }
}