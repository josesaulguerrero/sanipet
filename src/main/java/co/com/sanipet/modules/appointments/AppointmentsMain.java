package co.com.sanipet.modules.appointments;

import co.com.sanipet.modules.appointments.entities.Animals;
import co.com.sanipet.modules.appointments.entities.Owner;
import co.com.sanipet.modules.appointments.entities.Patient;
import co.com.sanipet.utils.ConsoleMenu;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class AppointmentsMain {
    public static void main() {
        int selectedOption = Integer.parseInt(ConsoleMenu.renderOptionsList("1. Register a new appointment", "2. Display the history"));
        callMethod(selectedOption);
    }

    private static void callMethod(int selectedOption) {
        if (selectedOption == 1) registerNewAppointment();
        else displayHistory();
    }

    private static void registerNewAppointment() {
        Optional<Owner> owner = Optional.of(getOwnerInformation());
        Optional<Patient> patient = Optional.empty();
        while (patient.isEmpty()) {
            try {
                patient = Optional.of(getPatientInformation(owner.get()));
            } catch (Exception e) {
                System.out.println("We're sorry, the information you supplied is not right. Please try again.");
            }
        }
    }

    private static Owner getOwnerInformation() {
        System.out.println("Alright! At first we need some information about the owner.");
        String ownerDNI = ConsoleMenu.renderQuestion("What's your DNI?");
        String ownerName = ConsoleMenu.renderQuestion("What's your name?");
        String ownerSurname = ConsoleMenu.renderQuestion("What's your surname?");
        String ownerCellphone = ConsoleMenu.renderQuestion("What's your contact cellphone?");
        int ownerAge = Integer.parseInt(ConsoleMenu.renderQuestion("What's your age?"));
        return new Owner(ownerDNI, ownerName, ownerSurname, ownerCellphone, ownerAge);
    }

    private static Patient getPatientInformation(Owner owner) throws Exception {
        System.out.println("That's great! now let's check your cute pet's information.");
        Animals patientSpecies = Animals.valueOf(ConsoleMenu.renderQuestion("Is your pet a cat or a dog? pick one and write it in ALL-CAPS").toUpperCase());
        String patientName = ConsoleMenu.renderQuestion("What's their name?");
        String patientBreed = ConsoleMenu.renderQuestion("What's their breed?");
        boolean isPatientVaccinated = Boolean.parseBoolean(ConsoleMenu.renderQuestion("Are they vaccinated? (true/false) "));
        LocalDate dateLastDeworming = LocalDate.parse(ConsoleMenu.renderQuestion("When were they last dewormed? (yyyy-mm-dd)"));
        return new Patient(patientSpecies, patientName, patientBreed, isPatientVaccinated, dateLastDeworming, owner);
    }

    private static void displayHistory() {
    }
}
