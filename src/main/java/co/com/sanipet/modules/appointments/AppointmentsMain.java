package co.com.sanipet.modules.appointments;

import co.com.sanipet.modules.appointments.services.AppointmentService;
import co.com.sanipet.utils.ConsoleMenu;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;

public class AppointmentsMain {

    private final static AppointmentService service = new AppointmentService();

    public static void main() {
        int selectedOption = Integer.parseInt(
                ConsoleMenu.renderAndVerify(
                        (option) -> NumberUtils.isParsable(option) && Range.between(1, 3).contains(Integer.parseInt(option)),
                        "1. Register new appointment", "2. Update appointment", "3. Display History"
                )
        );
        try {
            pickOption(selectedOption);
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    private static void pickOption(Integer option) {
        if (option.equals(1)) {
            service.registerNewAppointment();
        } else if(option.equals(2)) {
            service.updateAppointment();
        } else {
            service.displayHistory();
        }
    }
}