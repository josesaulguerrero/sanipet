package co.com.sanipet.modules.stock.services;

import co.com.sanipet.modules.stock.dao.MedicineDAO;
import co.com.sanipet.modules.stock.entities.Medicine;
import co.com.sanipet.modules.stock.entities.MedicinePresentation;
import co.com.sanipet.modules.stock.entities.Stock;
import co.com.sanipet.utils.ConsoleMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Locale;

public class MedicineService {
    private final MedicineDAO medicineDAO = new MedicineDAO();

    private Medicine create() {
        String name = ConsoleMenu.renderAndRead("What is the name of the medicine?").trim();
        String stringifiedPresentation = ConsoleMenu.renderAndVerify(
                (option) -> EnumUtils.isValidEnum(MedicinePresentation.class, option.trim().toUpperCase(Locale.ROOT)),
                "Enter the presentation of the medicine: ", "Pills", "Syrup"
        ).trim().toUpperCase(Locale.ROOT);
        MedicinePresentation presentation = MedicinePresentation.valueOf(stringifiedPresentation);
        Integer pricePerUnit = Integer.parseInt(ConsoleMenu.renderAndVerify(
                NumberUtils::isParsable,
                "What is the price per unit to the element to add?"
        ).trim());
        int amount = Integer.parseInt(ConsoleMenu.renderAndVerify(
                NumberUtils::isParsable,
                "What is the amount of the product to add?"
        ).trim());
        return new Medicine(name, presentation, pricePerUnit, new Stock(amount));
    }

    public List<Medicine> findAll() {
        return medicineDAO.findAll();
    }

    public Medicine findById(String id) {
        return medicineDAO.findById(id);
    }

    public void addElementToStock() {
        medicineDAO.saveElement(create());
    }

    public void removeElementFromStock() {
        String id = ConsoleMenu.renderAndRead("What is the id of the element?").trim();
        medicineDAO.removeElement(id);
    }

    private boolean exists(String id) {
        return medicineDAO.exists(id);
    }

    public void modifyAvailableUnits() {
        String id = ConsoleMenu.renderAndRead("What is the id of the element?").trim();
        if(!exists(id)) {
            throw new IllegalArgumentException("The element with the given id does not exist.");
        }
        int newAmount = Integer.parseInt(ConsoleMenu.renderAndVerify(
                NumberUtils::isParsable,
                "What is the new amount for the selected element?"
        ).trim());
        medicineDAO.updateAvailableUnits(id, newAmount);
    }

    public void modifyAvailableUnits(String id, Integer newAmount) {
        medicineDAO.updateAvailableUnits(id, newAmount);
    }

    public void printStock() {
        ConsoleMenu.printPrettifiedList(findAll());
    }
}