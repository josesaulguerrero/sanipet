package co.com.sanipet.modules.appointments;

import co.com.sanipet.modules.appointments.dao.EmployeeDAO;
import co.com.sanipet.modules.appointments.entities.Animals;
import co.com.sanipet.modules.appointments.entities.Owner;
import co.com.sanipet.modules.appointments.entities.Patient;
import co.com.sanipet.utils.ConsoleMenu;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

public class AppointmentsMain {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    public static void main() {
        Optional<Integer> selectedOption = Optional.empty();
        while (selectedOption.isEmpty()){
            try {
                selectedOption = Optional.of(Integer.parseInt(ConsoleMenu.renderOptionsList("1. Register new appointment", "2. Display " +
                        "history")));
                pickOption(selectedOption.get());
            } catch (Exception e) {
                System.out.println("The supplied information is incorrect.");
            }
        }
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
        // TODO get owner information from user
        // TODO get patient information from user
        // TODO get appointment information form user
    }

    private static Owner getOwnerInformation() {
        System.out.println("Before we start, we need some information from the owner!");
        String DNI  = ConsoleMenu.renderQuestion("Enter your DNI number:");
        String name = ConsoleMenu.renderQuestion("What is your name?");
        String surname = ConsoleMenu.renderQuestion("What is your surname?");
        String cellphone = ConsoleMenu.renderQuestion("What is your cellphone contact number?");
        Optional<Integer> age = Optional.empty();
        while (age.isEmpty()){
            try {
                age = Optional.of(Integer.parseInt(ConsoleMenu.renderOptionsList("")));
            } catch (Exception e) {
                System.out.println("You must be over 18 years old. Please enter a valid age.");
            }
        }
        return new Owner(DNI, name, surname, cellphone, age.get());
    }

    private static Patient patientInfo(Owner owner){
        Animals patientSpecies = Animals.valueOf(ConsoleMenu.renderQuestion("What is the species of the patient? " +
                "(DOG/CAT)"));
        String patientName = ConsoleMenu.renderQuestion("What is the name of the patient?");
        String patientBreed = ConsoleMenu.renderQuestion("What is the breed of the patient?");
        Boolean patientVaccinated =
                ConsoleMenu.renderQuestion("Is the patient vaccinated? (YES/NO)").toUpperCase(Locale.ROOT).equals("YES");
        LocalDate patientLastDeworming = LocalDate.parse(ConsoleMenu.renderQuestion("When the patient was dewormed? " +
                "(YYYY-MM-DD)"));

        return new Patient(patientSpecies, patientName, patientBreed, patientVaccinated, patientLastDeworming, owner);
    }
    private static void displayHistory() {

    }
}
