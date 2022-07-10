package co.com.sanipet.modules.appointments.services;

import co.com.sanipet.modules.appointments.entities.Appointment;
import co.com.sanipet.modules.appointments.entities.Invoice;
import co.com.sanipet.modules.stock.entities.Medicine;
import co.com.sanipet.modules.stock.services.MedicineService;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class InvoiceService {
    private final MedicineService medicineService = new MedicineService();

    public Invoice startBilling(Appointment appointment) {
        Map<Medicine, Integer> medicines = new HashMap<>();
        boolean addMoreItems = true;
        while (addMoreItems) {
            addMoreItems = ConsoleMenu.renderAndVerify(
                    (text) -> List.of("YES", "NO").contains(text.trim().toUpperCase(Locale.ROOT)),
                    "Do need any other medicine? (YES/NO)"
            ).trim().toUpperCase(Locale.ROOT).equals("YES");
            if (addMoreItems) {
                Pair<Medicine, Integer> pair = addNewItem();
                medicines.put(pair.getLeft(), pair.getRight());
            }
        }
        return new Invoice(
                medicines,
                appointment,
                calcSubtotal(appointment.getAppointmentCost(), getMedicinesSubtotal(medicines))
        );
    }

    private Double getMedicinesSubtotal(Map<Medicine, Integer> medicines) {
        double result = 0;
        for (Map.Entry<Medicine, Integer> pair : medicines.entrySet()) {
            result += pair.getKey().getPricePerUnit() * pair.getValue();
        }
        return result;
    }

    private Double calcSubtotal(Double ...values) {
        return Arrays.stream(values).reduce(Double::sum).get();
    }

    public Pair<Medicine, Integer> addNewItem() {
        String id = ConsoleMenu.renderAndRead("What is the id of the medicine?");
        Medicine medicine = medicineService.findById(id);
        Integer amount = Integer.parseInt(ConsoleMenu.renderAndVerify(
                (text) -> NumberUtils.isParsable(text) && Integer.parseInt(text.trim()) <= medicine.getStock().getAmount(),
                "How many units of this medicine do you need?").trim()
        );
        return Pair.of(medicine, amount);
    }
}
