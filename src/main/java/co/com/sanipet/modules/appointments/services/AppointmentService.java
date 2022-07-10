package co.com.sanipet.modules.appointments.services;

import co.com.sanipet.modules.appointments.dao.*;
import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import co.com.sanipet.utils.DateUtils;
import com.google.gson.*;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppointmentService {
    private final EmployeeService employeeService = new EmployeeService();
    private final PatientService patientService = new PatientService();
    private final OwnerService ownerService = new OwnerService();

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

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
            try {
                String DNI = ConsoleMenu.renderAndRead("What is you DNI?");
                owner = ownerService.logIn(DNI);
            } catch (Exception e) {
                System.out.printf("%s You will be redirected to Sign Up \n", e.getMessage());
                owner = ownerService.registerNewOwner();
            }
        } else {
            owner = ownerService.registerNewOwner();
        }
        return owner;
    }

    private Patient getPatientBasedOnUserInput(Integer selectedOption, Owner owner) {
        Patient patient;
        if (selectedOption.equals(1)) {
            try {
                String clinicalHistoryId = ConsoleMenu.renderAndRead("What is your pet clinical history Id?").trim();
                patient = patientService.logIn(clinicalHistoryId);
            } catch (Exception e) {
                System.out.printf("%s You will be redirected to Sign Up \n", e.getMessage());
                patient = patientService.registerNewPatient(owner);
            }
        } else {
            patient = patientService.registerNewPatient(owner);
        }
        return patient;
    }

    private Appointment getAppointmentInformation(Patient patient) {
        System.out.println("What kind of appointment do you need? (MEDICAL/SURGERY/AESTHETIC)");
        AppointmentTypes type = AppointmentTypes.valueOf(ConsoleMenu.renderAndVerify(
                option -> EnumUtils.isValidEnum(AppointmentTypes.class, option.trim().toUpperCase(Locale.ROOT)),
                "Medical", "Surgery", "Aesthetic"
        ).trim().toUpperCase(Locale.ROOT));
        String stringifiedDate = ConsoleMenu.renderAndVerify(
                option -> DateUtils.isValidDate(option) && LocalDate.parse(option).isAfter(LocalDate.now()),
                "What date do you need the appointment for? (YYYY-MM-DD)"
        );
        LocalDate date = LocalDate.parse(stringifiedDate);
        Employee employee = employeeService.findAvailable(type.findAssociatedRole(), date).get(0);

        return new Appointment(type, date, patient, employee);
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
            modifyAppointmentStatus(appointmentId, status);
        }
    }

    private void modifyAppointmentStatus(String appointmentId, Statuses status) {
        if (isAppointmentCancellable(appointmentId) || !Statuses.CANCELLED.equals(status)){
            appointmentDAO.update(appointmentId, status);
        } else {
            throw new IllegalStateException("The appointment status can't be updated.");
        }
    }

    public boolean isAppointmentCancellable(String Id) {
        LocalDate now = LocalDate.now();
        LocalDate appointmentDay = appointmentDAO.findById(Id).getDate();
        return now.isBefore(appointmentDay.minusDays(1));
    }

    private void printRegisters(List<Appointment> registers) {
        for (Appointment appointment : registers) {
            System.out.println("----------------------------------");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement prettyJSON = JsonParser.parseString(gson.toJson(appointment));
            System.out.println(gson.toJson(prettyJSON));
            System.out.println("----------------------------------");
        }
    }

    private void showFilteredByDay(){
        LocalDate dateToCheck = ConsoleMenu.renderAndVerifyDate(
                "Which day in the history do you want to check? (YYYY-MM-DD)"
        );
        List<Appointment> registers = this.appointmentDAO.findAll()
            .stream().filter(appointment -> appointment.getDate().equals(dateToCheck)).collect(Collectors.toList());
        printRegisters(registers);
    }

    public  void displayHistory() {
        Integer selectedOption = Integer.valueOf(ConsoleMenu.renderAndVerify(
            (option) -> NumberUtils.isCreatable(option) && Range.between(1,2).contains(Integer.parseInt(option.trim())),
            "1. Check the whole history", "2. Check history by day."
        ).trim());

        if (selectedOption.equals(1)) printRegisters(appointmentDAO.findAll());
        else showFilteredByDay();
    }
}
