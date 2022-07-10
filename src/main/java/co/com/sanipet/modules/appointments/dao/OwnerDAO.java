package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.Owner;

import java.util.*;

public class OwnerDAO {
    private final Map<String, Owner> owners = new HashMap<>();

    public Optional<Owner> logIn(String DNI) {
        return findByDNI(DNI);
    }

    public Boolean exists(String DNI) {
        return this.owners.containsKey(DNI);
    }

    public Optional<Owner> findByDNI(String DNI) {
        return Optional.ofNullable(this.owners.get(DNI));
    }

    public void save(Owner owner) {
        owners.put(owner.getDNI(), owner);
    }
}
