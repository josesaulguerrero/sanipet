package co.com.sanipet.modules.appointments.services;

import co.com.sanipet.modules.appointments.dao.*;
import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import com.google.gson.*;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class AppointmentService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final OwnerDAO ownerDAO = new OwnerDAO();
    private final PatientDAO patientDAO = new PatientDAO();

    public void registerNewAppointment() {
        Predicate<String> validator =
                option -> NumberUtils.isParsable(option) && Range.between(1, 2).contains(Integer.parseInt(option.trim()));
        int ownerSelectedOption = Integer.parseInt(ConsoleMenu.renderAndVerify(
                validator,
                "1. are you registered yet? Log in.", "2. You don't have an account? Sign up"
        ));
        Owner owner = getOwnerBasedOnUserInput(ownerSelectedOption);
        int petSelectedOption = Integer.parseInt(ConsoleMenu.renderAndVerify(
                validator,
                "1. is your pet registered? Log in.", "2. don't they have an account yet? Sign up"
        ));
        Patient patient = getPatientBasedOnUserInput(petSelectedOption, owner);
        Appointment appointment = getAppointmentInformation(patient);
        appointmentDAO.save(appointment);
        System.out.printf("Your appointment has been successfully created with the id: %s \n", appointment.getId());
        System.out.println("--------------------------------------------------");
    }

    private Owner getOwnerBasedOnUserInput(Integer selectedOption) {
        Owner owner;
        if (selectedOption.equals(1)) {
            String DNI = ConsoleMenu.renderAndRead("What is you DNI?");
            owner = (ownerDAO.logIn(DNI));
        } else {
            owner = ownerDAO.create();
            ownerDAO.save(owner);
        }
        return owner;
    }

    private Patient getPatientBasedOnUserInput(Integer selectedOption, Owner owner) {
        Patient patient;
        if (selectedOption.equals(1)) {
            String ClinicalHistoryId = ConsoleMenu.renderAndRead("What is their clinical history Id?");
            patient = patientDAO.logIn(ClinicalHistoryId, owner);
        } else {
            patient = patientDAO.create(owner);
            patientDAO.save(patient);
        }
        return patient;
    }

    private Appointment getAppointmentInformation(Patient patient) {
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

    public void updateAppointment() {
        String appointmentId = ConsoleMenu.renderAndRead("Please enter the Id of the appointment: ").trim();
        Optional<Appointment> appointment = Optional.of(appointmentDAO.findById(appointmentId));
        if (appointmentDAO.exists(appointmentId)) {
            String stringifiedStatus = ConsoleMenu.renderAndVerify(
                    option -> List.of("ABSENT", "FINISHED", "CANCELLED").contains(option.trim().toUpperCase()),
                    "Please enter the new status for the appointment: ", "Absent", "Finished", "Cancelled"
            );
            Statuses status = Statuses.valueOf(stringifiedStatus.trim().toUpperCase(Locale.ROOT));
            modifyStatus(appointmentId, status);
        }
    }

    private void modifyStatus(String appointmentId, Statuses status) {
        if (isAppointmentCancellable(appointmentId) || !Statuses.CANCELLED.equals(status)){
            appointmentDAO.update(appointmentId, status);
        } else {
            throw new IllegalStateException("The appointment status can't be updated.");
        }
    }

    public boolean isAppointmentCancellable(String Id) {
        int now = LocalDate.now().getDayOfWeek().getValue();
        int appointmentDay = appointmentDAO.findById(Id).getDate().getWeekday();
        return appointmentDay - now > 1;
    }
    public  void displayHistory() {
        List<Appointment> appointments = appointmentDAO.findAll();
        for (Appointment appointment : appointments) {
            System.out.println("----------------------------------");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement prettyJSON = JsonParser.parseString(gson.toJson(appointment));
            System.out.println(gson.toJson(prettyJSON));
            System.out.println("----------------------------------");
        }
    }
}
