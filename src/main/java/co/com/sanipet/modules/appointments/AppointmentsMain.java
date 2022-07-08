package co.com.sanipet.modules.appointments;

import co.com.sanipet.modules.appointments.dao.EmployeeDAO;
import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

public class AppointmentsMain {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    public static void main() {
        Optional<Integer> selectedOption = Optional.of(
                Integer.valueOf(ConsoleMenu.renderAndVerify(
                        (option) -> NumberUtils.isParsable(option) && Range.between(1, 2).contains(Integer.parseInt(option)),
                        "1. Register new appointment", "2. Display history"
                ))
        );
        pickOption(selectedOption.get());
    }

    private static void pickOption(int option) {
        switch (option) {
            case 1:
                registerNewAppointment();
                break;
            case 2:
                displayHistory();
                break;
        }
    }

    private static void registerNewAppointment() {
        Owner owner = getOwnerInformation();
        Patient patient = getPatientInformation(owner);
        Appointment appointment =  getAppointmentInformation(patient);
    }

    private static Owner getOwnerInformation() {
        System.out.println("Before we start, we need some information from the owner!");
        String DNI  = ConsoleMenu.renderAndRead("Enter your DNI number:");
        String name = ConsoleMenu.renderAndRead("What is your name?");
        String surname = ConsoleMenu.renderAndRead("What is your surname?");
        String cellphone = ConsoleMenu.renderAndRead("What is your cellphone contact number?");
        Optional<Integer> age = Optional.of(Integer.parseInt(ConsoleMenu.renderAndVerify(
                (answer) -> NumberUtils.isParsable(answer) && Integer.parseInt(answer) >= 18,
                "How old are you? You must be over 18"
        )));
        return new Owner(DNI, name, surname, cellphone, age.get());
    }

    private static Patient getPatientInformation(Owner owner){
        Animals patientSpecies = Animals.valueOf(ConsoleMenu.renderAndRead("What is the species of the patient? " +
                "(DOG/CAT)").toUpperCase(Locale.ROOT));
        String patientName = ConsoleMenu.renderAndRead("What is the name of the patient?");
        String patientBreed = ConsoleMenu.renderAndRead("What is the breed of the patient?");
        Boolean patientVaccinated =
                ConsoleMenu.renderAndRead("Is the patient vaccinated? (YES/NO)").toUpperCase(Locale.ROOT).equals("YES");
        LocalDate patientLastDeworming = LocalDate.parse(ConsoleMenu.renderAndRead("When the patient was dewormed? " +
                "(YYYY-MM-DD)"));
        return new Patient(patientSpecies, patientName, patientBreed, patientVaccinated, patientLastDeworming, owner);
    }

    private static Appointment getAppointmentInformation(Patient patient){
        System.out.println("What kind of appointment do you need?");
        int selectedOption = Integer.parseInt(ConsoleMenu.renderAndVerify(
                (option) -> NumberUtils.isParsable(option) && Range.between(1, 3).contains(Integer.parseInt(option)),
                "1. Medical", "2. Surgery", "3. Aesthetic"
        ));
        AppointmentTypes type = getAppointmentType(selectedOption);
        WorkingDays day = WorkingDays.valueOf(ConsoleMenu.renderAndRead("Which day of the week do you prefer the " +
                "appointment in?").toUpperCase(Locale.ROOT));
        System.out.println(employeeDAO.findAvailable(type.findAssociatedRole(), day));
        return null;
    }

    private static AppointmentTypes getAppointmentType(int option) {
        switch (option){
            case 1:
            default:
                return AppointmentTypes.MEDICAL;
            case 2:
                return AppointmentTypes.SURGERY;
            case 3:
                return AppointmentTypes.AESTHETIC;
        }
    }


    private static void displayHistory() {

    }
}
