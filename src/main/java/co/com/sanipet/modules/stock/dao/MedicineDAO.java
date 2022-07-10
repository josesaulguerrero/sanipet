package co.com.sanipet.modules.stock.dao;

import co.com.sanipet.modules.stock.entities.Medicine;

import java.util.*;

public class MedicineDAO {
    private final Map<String, Medicine> stock = new HashMap<String, Medicine>();

    public List<Medicine> findAll() {
        return new ArrayList<>(stock.values());
    }

    public Medicine findById(String id) {
        return findAll()
                .stream()
                .filter(medicine -> medicine.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The element with the given id doesn't exist in stock."));
    }

    public boolean exists(String id) {
        return this.stock.containsKey(id);
    }

    public void saveElement(Medicine medicine) {
        this.stock.put(medicine.getId(), medicine);
    }

    public void removeElement(String id) {
        stock.remove(id, findById(id));
    }

    public void updateAvailableUnits(String id, Integer newAmount) {
        findById(id).getStock().setAmount(newAmount);
    }
}