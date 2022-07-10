package co.com.sanipet.modules.stock.dao;

import co.com.sanipet.modules.stock.entities.Medicine;
import co.com.sanipet.modules.stock.entities.MedicinePresentation;
import co.com.sanipet.modules.stock.entities.Stock;

import java.util.*;

public class MedicineDAO {
    private final static Map<String, Medicine> stock = new HashMap<String, Medicine>();

    public List<Medicine> findAll() {
        return new ArrayList<>(stock.values());
    }

    public Medicine findById(String id) {
        return Optional
                .ofNullable(stock.get(id))
                .orElseThrow(() -> new IllegalArgumentException("The medicine with the given id does not exist!"));
    }

    public boolean exists(String id) {
        return stock.containsKey(id);
    }

    public void saveElement(Medicine medicine) {
        stock.put(medicine.getId(), medicine);
    }

    public void removeElement(String id) {
        stock.remove(id, findById(id));
    }

    public void updateAvailableUnits(String id, Integer newAmount) {
        findById(id).getStock().setAmount(newAmount);
    }
}