package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.EnumUtils;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;


public class PatientDAO {
    //Attributes

    private final Map<String, Patient> patients = new HashMap<>();

    /*
    * Method to log in the user
    * @param clinicalHistoryId
    * */
    public Optional<Patient> logIn(String clinicalHistoryId) {
        return findByClinicalHistoryId(clinicalHistoryId);
    }

    public Boolean exists(String clinicalHistoryId) {
        return this.patients.containsKey(clinicalHistoryId);
    }

    /*
     * Method to seek a patient by Clinical History ID
     * @param clinicalHistoryId
     * */
    public Optional<Patient> findByClinicalHistoryId(String clinicalHistoryId) {
        return Optional
                .ofNullable(patients.get(clinicalHistoryId));
    }

    /*
     * Method to save the patient in DAO.
     * @param DNI
     * */
    public void save(Patient patient) {
        this.patients.put(patient.getClinicalHistoryId(), patient);
    }
}
