package co.com.sanipet.modules.stock.services;

import co.com.sanipet.modules.stock.dao.MedicineDAO;
import co.com.sanipet.modules.stock.entities.Medicine;
import co.com.sanipet.modules.stock.entities.MedicinePresentation;
import co.com.sanipet.modules.stock.entities.Stock;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;

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
        int amount = Integer.parseInt(ConsoleMenu.renderAndVerify(
                NumberUtils::isParsable,
                "What is the amount of the product to add?"
        ).trim());
        return new Medicine(name, presentation, new Stock(amount));
    }

    public void addElementToStock() {
        medicineDAO.saveElement(create());
    }
}
