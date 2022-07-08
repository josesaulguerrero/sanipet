package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.Owner;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

public class OwnerDAO {
    private final Map<String, Owner> owners = new HashMap<>();

    public Owner create() {
        String DNI  = ConsoleMenu.renderAndRead("Enter your DNI number:");
        String name = ConsoleMenu.renderAndRead("What is your name?");
        String surname = ConsoleMenu.renderAndRead("What is your surname?");
        String cellphone = ConsoleMenu.renderAndRead("What is your cellphone contact number?");
        Optional<Integer> age = Optional.of(Integer.parseInt(ConsoleMenu.renderAndVerify(
                (answer) -> NumberUtils.isParsable(answer) && Integer.parseInt(answer) >= 18,
                "How old are you? You must be over 18"
        )));
        return new Owner(DNI, name, surname, cellphone, age.get());
    }

    public void save(Owner owner) {
        owners.put(owner.getDNI(), owner);
    }
}
