package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.EnumUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;


public class PatientDAO {
    //Attributes

    private final Map<String, Patient> patients = new HashMap<>();

    /*
    * Method to log in the user
    * @param clinicalHistoryId
    * */
    public Patient logIn(String clinicalHistoryId, Owner owner) {
        Optional<Patient> patient = seekByClinicalHistoryId(clinicalHistoryId);
        if(patient.isEmpty()) {
            System.out.println("Your pet doesn't have an account yet. You will be redirected to Sign Up.");
            patient = Optional.of(create(owner));
        }
        return patient.get();
    }

    /*
     * Method to seek a patient by Clinical History ID
     * @param clinicalHistoryId
     * */
    public Optional<Patient> seekByClinicalHistoryId(String clinicalHistoryId) {
        Optional<Patient> patient = Optional.empty();
        if(patients.containsKey(clinicalHistoryId)) {
            patient = Optional.of(patients.get(clinicalHistoryId));
        }
        return patient;
    }

    /*
     * Method to create a patient
     * */
    public Patient create(Owner owner) {
        Animals patientSpecies = Animals.valueOf(ConsoleMenu.renderAndVerify(
            (option) -> EnumUtils.isValidEnum(Animals.class, option.toUpperCase(Locale.ROOT)),
            "What is the species of the patient? (DOG/CAT)"
        ).toUpperCase(Locale.ROOT));

        String patientName = ConsoleMenu.renderAndRead("What is the name of the patient?");
        String patientBreed = ConsoleMenu.renderAndRead("What is the breed of the patient?");
        Boolean patientVaccinated = ConsoleMenu.renderAndVerify(
                (option) -> List.of("YES", "NO").contains(option.toUpperCase(Locale.ROOT)),
                "Is the patient vaccinated? (YES/NO)"
        ).toUpperCase(Locale.ROOT).equals("YES");
        LocalDate patientLastDeworming = ConsoleMenu.renderAndVerifyDate("When was the patient last dewormed? " +
                "(YYYY-MM-DD)");
        return new Patient(patientSpecies, patientName, patientBreed, patientVaccinated, patientLastDeworming, owner);
    }

    /*
     * Method to save the patient in DAO.
     * @param DNI
     * */
    public void save(Patient patient) {
        this.patients.put(patient.getClinicalHistoryId(), patient);
    }
}
