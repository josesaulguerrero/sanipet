package co.com.sanipet.modules.appointments.entities;

import co.com.sanipet.utils.HashGenerator;

import java.time.LocalDate;
import java.util.Date;

public class Patient {
    private final Animals species;
    private final String name;
    private final String breed;
    private final String clinicalHistoryId;
    private final Boolean isVaccinated;
    private final LocalDate lastDateOfDeworming;
    private final Owner owner;

    public Patient(Animals species, String name, String breed, Boolean isVaccinated, LocalDate lastDateOfDeworming, Owner owner) {
        this.species = species;
        this.name = name;
        this.breed = breed;
        this.clinicalHistoryId = generateHistoryId();
        this.isVaccinated = isVaccinated;
        this.lastDateOfDeworming = lastDateOfDeworming;
        this.owner = owner;
    }

    private String generateHistoryId() {
        return HashGenerator.generateRandomAlphanumericString(6);
    }

    public Animals getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getClinicalHistoryId() {
        return clinicalHistoryId;
    }

    public Boolean getVaccinated() {
        return isVaccinated;
    }

    public LocalDate getLastDateOfDeworming() {
        return lastDateOfDeworming;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "species=" + species +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", clinicalHistoryId='" + clinicalHistoryId + '\'' +
                ", isVaccinated=" + isVaccinated +
                ", lastDateOfDeworming=" + lastDateOfDeworming +
                ", owner=" + owner +
                '}';
    }
}
