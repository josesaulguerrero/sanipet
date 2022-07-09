package co.com.sanipet.modules.appointments.services;

import co.com.sanipet.modules.appointments.dao.OwnerDAO;
import co.com.sanipet.modules.appointments.entities.Owner;
import co.com.sanipet.modules.appointments.entities.Patient;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.math.NumberUtils;

public class OwnerService {
    private final OwnerDAO ownerDAO = new OwnerDAO();

    public Owner registerNewOwner() {
        String DNI  = ConsoleMenu.renderAndRead("Enter your DNI number:");
        String name = ConsoleMenu.renderAndRead("What is your name?");
        String surname = ConsoleMenu.renderAndRead("What is your surname?");
        String cellphone = ConsoleMenu.renderAndRead("What is your cellphone contact number?");
        Integer age = Integer.parseInt(ConsoleMenu.renderAndVerify(
                (answer) -> NumberUtils.isParsable(answer) && Integer.parseInt(answer) >= 18,
                "How old are you? You must be over 18"
        ));
        Owner owner = new Owner(DNI, name, surname, cellphone, age);
        ownerDAO.save(owner);
        return owner;
    }

    public Boolean exists(String DNI) {
        return ownerDAO.exists(DNI);
    }

    public Owner logIn(String DNI) {
        if (!exists(DNI)) {
            throw new IllegalArgumentException("There's no owner registered with the given DNI.");
        }
        return ownerDAO.logIn(DNI).get();
    }
}
