package co.com.sanipet.modules.appointments.entities;

import co.com.sanipet.utils.HashGenerator;

import java.util.Date;

public class Patient {
    private final Animal species;
    private final String name;
    private final String breed;
    private final String clinicalHistoryId;
    private final Boolean isVaccinated;
    private final Date lastDateOfDeworming;

    public Patient(Animal species, String name, String breed, String clinicalHistoryId, Boolean isVaccinated, Date lastDateOfDeworming) {
        this.species = species;
        this.name = name;
        this.breed = breed;
        this.clinicalHistoryId = clinicalHistoryId;
        this.isVaccinated = isVaccinated;
        this.lastDateOfDeworming = lastDateOfDeworming;
    }

    private String generateHistoryId() {
        return HashGenerator.generateRandomAlphanumericString(6);
    }

    public Animal getSpecies() {
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

    public Date getLastDateOfDeworming() {
        return lastDateOfDeworming;
    }
}
