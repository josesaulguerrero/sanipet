package co.com.sanipet;

import co.com.sanipet.modules.appointments.AppointmentsMain;
import co.com.sanipet.modules.appointments.dao.EmployeeDAO;
import co.com.sanipet.utils.ConsoleMenu;

import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        Optional<Integer> selectedOption = Optional.empty();
        while (selectedOption.isEmpty() || selectedOption.get() != 0) {
            try {
                System.out.println("Welcome to Sanipet! What do you want to do today?");
                String[] options = new String[] {"1. Appointments", "2. Medicines stock", "0. exit"};
                selectedOption = Optional.of(Integer.valueOf(ConsoleMenu.renderOptionsList(options)));
                callModule(selectedOption.get());
            } catch (NumberFormatException e) {
                System.out.println("The given option doesn't exist. Please enter one of the listed above.");
            }
        }
    }

    private static void callModule(Integer moduleId) {
        switch (moduleId) {
            case 1:
                AppointmentsMain.main();
                break;
            case 2:
                // TODO call medicines module.
                break;
            default:
                break;
        }
    }
}
