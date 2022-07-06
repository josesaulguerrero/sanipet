package co.com.sanipet.modules.appointments.entities;

import co.com.sanipet.utils.HashGenerator;

import java.util.Date;

public class Patient {
    private final Animals species;
    private final String name;
    private final String breed;
    private final String clinicalHistoryId;
    private final Boolean isVaccinated;
    private final Date lastDateOfDeworming;
    private final Owner owner;

    public Patient(Animals species, String name, String breed, String clinicalHistoryId, Boolean isVaccinated, Date lastDateOfDeworming, Owner owner) {
        this.species = species;
        this.name = name;
        this.breed = breed;
        this.clinicalHistoryId = clinicalHistoryId;
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

    public Date getLastDateOfDeworming() {
        return lastDateOfDeworming;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Patient{" + "species=" + species + ", name='" + name + '\'' + ", breed='" + breed + '\'' + ", clinicalHistoryId='" + clinicalHistoryId + '\'' + ", isVaccinated=" + isVaccinated + ", lastDateOfDeworming=" + lastDateOfDeworming + '}';
    }
}
