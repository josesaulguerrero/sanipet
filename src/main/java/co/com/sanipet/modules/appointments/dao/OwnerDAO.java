package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.Owner;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

public class OwnerDAO {
    private final Map<String, Owner> owners = new HashMap<>();

    public Owner logIn(String DNI) {
        Optional<Owner> owner = seekByDNI(DNI);
        if(owner.isEmpty()) {
            System.out.println("You don't have an account yet. You will be redirected to Sign Up.");
            owner = Optional.of(create());
        }
        return owner.get();
    }

    public Optional<Owner> seekByDNI(String DNI) {
        Optional<Owner> owner = Optional.empty();
        if(owners.containsKey(DNI)) {
            owner = Optional.of(owners.get(DNI));
        }
        return owner;
    }

    public Owner create() {
        String DNI  = ConsoleMenu.renderAndRead("Enter your DNI number:");
        String name = ConsoleMenu.renderAndRead("What is your name?");
        String surname = ConsoleMenu.renderAndRead("What is your surname?");
        String cellphone = ConsoleMenu.renderAndRead("What is your cellphone contact number?");
        Integer age = Integer.parseInt(ConsoleMenu.renderAndVerify(
                (answer) -> NumberUtils.isParsable(answer) && Integer.parseInt(answer) >= 18,
                "How old are you? You must be over 18"
        ));
        return new Owner(DNI, name, surname, cellphone, age);
    }

    public void save(Owner owner) {
        owners.put(owner.getDNI(), owner);
    }
}