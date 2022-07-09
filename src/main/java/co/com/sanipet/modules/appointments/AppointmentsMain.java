package co.com.sanipet.modules.appointments;

import co.com.sanipet.modules.appointments.dao.*;
import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;

public class AppointmentsMain {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private static final OwnerDAO ownerDAO = new OwnerDAO();
    private static final PatientDAO patientDAO = new PatientDAO();

    public static void main() {
        int selectedOption = Integer.parseInt(
                ConsoleMenu.renderAndVerify(
                        (option) -> NumberUtils.isParsable(option) && Range.between(1, 4).contains(Integer.parseInt(option)),
                        "1. Register new appointment", "2. Update appointment", "3. Cancel appointment", "4. Display History"
                )
        );
        pickOption(selectedOption);
    }

    private static void pickOption(Integer option) {
        if (option.equals(1)) {
            registerNewAppointment();
        } else if(option.equals(2)) {
            // updateAppointment();
        } else if (option.equals(3)) {
            // cancelAppointment();
        } else {
            // displayHistory();
        }
    }

    private static void registerNewAppointment(){
        int ownerSelectedOption = Integer.parseInt(ConsoleMenu.renderAndVerify(
                (option) -> NumberUtils.isParsable(option) && Range.between(1,2).contains(Integer.parseInt(option)),
                "1. are you registered yet? Log in.", "2. You don't have an account? Sign up"
        ));
        Owner owner = getOwnerBasedOnUserInput(ownerSelectedOption);
        int petSelectedOption = Integer.parseInt(ConsoleMenu.renderAndVerify(
                (option) -> NumberUtils.isParsable(option) && Range.between(1,2).contains(Integer.parseInt(option)),
                "1. is your pet registered? Log in.", "2. don't they have an account yet? Sign up"
        ));
        Patient patient = getPatientBasedOnUserInput(petSelectedOption, owner);
    }

    private static Owner getOwnerBasedOnUserInput(Integer selectedOption) {
        Owner owner;
        if(selectedOption.equals(1)) {
            String DNI = ConsoleMenu.renderAndRead("What is you DNI?");
            owner = (ownerDAO.logIn(DNI));
        } else {
            owner = ownerDAO.create();
            ownerDAO.save(owner);
        }
        return owner;
    }

    private static Patient getPatientBasedOnUserInput(Integer selectedOption, Owner owner) {
        Patient patient;
        if(selectedOption.equals(1)) {
            String ClinicalHistoryId = ConsoleMenu.renderAndRead("What is their clinical history Id?");
            patient = patientDAO.logIn(ClinicalHistoryId, owner);
        } else {
            patient = patientDAO.create(owner);
            patientDAO.save(patient);
        }
        return patient;
    }

    /*private static AppointmentTypes getAppointmentType(int option) {
        switch (option){
            case 1:
            default:
                return AppointmentTypes.MEDICAL;
            case 2:
                return AppointmentTypes.SURGERY;
            case 3:
                return AppointmentTypes.AESTHETIC;
        }
    }

    private static void displayHistory() {
        List<Appointment> appointments = appointmentDAO.findAll();
        for (Appointment appointment : appointments) {
            System.out.println("----------------------------------");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement prettyJSON = JsonParser.parseString(gson.toJson(appointment));
            System.out.println(gson.toJson(prettyJSON));
            System.out.println("----------------------------------");
        }
    }*/
}
