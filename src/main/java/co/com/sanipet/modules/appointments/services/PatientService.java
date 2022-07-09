package co.com.sanipet.modules.appointments.services;

import co.com.sanipet.modules.appointments.dao.PatientDAO;
import co.com.sanipet.modules.appointments.entities.Animals;
import co.com.sanipet.modules.appointments.entities.Owner;
import co.com.sanipet.modules.appointments.entities.Patient;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.EnumUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class PatientService {
    private final PatientDAO patientDAO = new PatientDAO();
    public Patient registerNewPatient(Owner owner) {
        Animals species = Animals.valueOf(ConsoleMenu.renderAndVerify(
                (option) -> EnumUtils.isValidEnum(Animals.class, option.toUpperCase(Locale.ROOT)),
                "What is the species of the patient? (DOG/CAT)"
        ).toUpperCase(Locale.ROOT));

        String name = ConsoleMenu.renderAndRead("What is the name of the patient?");
        String breed = ConsoleMenu.renderAndRead("What is the breed of the patient?");
        Boolean isVaccinated = ConsoleMenu.renderAndVerify(
                (option) -> List.of("YES", "NO").contains(option.toUpperCase(Locale.ROOT)),
                "Is the patient vaccinated? (YES/NO)"
        ).toUpperCase(Locale.ROOT).equals("YES");
        LocalDate lastDeworming = ConsoleMenu.renderAndVerifyDate("When was the patient last dewormed? " +
                "(YYYY-MM-DD)");
        Patient patient = new Patient(species, name, breed, isVaccinated, lastDeworming, owner);
        patientDAO.save(patient);
        return patient;
    }

    public Boolean exists(String clinicalHistoryId) {
        return patientDAO.exists(clinicalHistoryId);
    }

    public Patient logIn(String clinicalHistoryId) {
        if (!exists(clinicalHistoryId)) {
            throw new IllegalArgumentException("There's no patient registered with the given clinical history id.");
        }
        return patientDAO.findByClinicalHistoryId(clinicalHistoryId).get();
    }
}