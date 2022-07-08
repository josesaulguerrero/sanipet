package co.com.sanipet.modules.appointments;

import co.com.sanipet.modules.appointments.dao.*;
import co.com.sanipet.modules.appointments.entities.*;
import co.com.sanipet.utils.ConsoleMenu;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

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
        Optional<Owner> owner;
        if(selectedOption.equals(1)) {
            String DNI = ConsoleMenu.renderAndRead("What is you DNI?");
            owner = Optional.of(ownerDAO.logIn(DNI));
        } else {
            owner = Optional.of(ownerDAO.create());
            ownerDAO.save(owner.get());
        }
        return owner.get();
    }

    private static Patient getPatientBasedOnUserInput(Integer selectedOption, Owner owner) {
            Optional<Patient> patient;
            if(selectedOption.equals(1)) {
                String ClinicalHistoryId = ConsoleMenu.renderAndRead("What is their clinical history Id?");
                patient = Optional.of(patientDAO.logIn(ClinicalHistoryId, owner));
            } else {
                patient = Optional.of(patientDAO.create(owner));
                patientDAO.save(patient.get());
            }
            return patient.get();
        }

    /*private static void registerNewAppointment() {
        Owner owner = getOwnerInformation();
        Patient patient = getPatientInformation(owner);
        Appointment appointment =  getAppointmentInformation(patient);
        appointmentDAO.create(appointment);
    }

    private static Owner getOwnerInformation() {
        System.out.println("Before we start, we need some information from the owner!");
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

    private static Patient getPatientInformation(Owner owner){
        Animals patientSpecies = Animals.valueOf(ConsoleMenu.renderAndRead("What is the species of the patient? " +
                "(DOG/CAT)").toUpperCase(Locale.ROOT));
        String patientName = ConsoleMenu.renderAndRead("What is the name of the patient?");
        String patientBreed = ConsoleMenu.renderAndRead("What is the breed of the patient?");
        Boolean patientVaccinated =
                ConsoleMenu.renderAndRead("Is the patient vaccinated? (YES/NO)").toUpperCase(Locale.ROOT).equals("YES");
        LocalDate patientLastDeworming = LocalDate.parse(ConsoleMenu.renderAndRead("When the patient was dewormed? " +
                "(YYYY-MM-DD)"));
        return new Patient(patientSpecies, patientName, patientBreed, patientVaccinated, patientLastDeworming, owner);
    }

    private static Appointment getAppointmentInformation(Patient patient){
        System.out.println("What kind of appointment do you need?");
        int appointmentOption = Integer.parseInt(ConsoleMenu.renderAndVerify(
                (option) -> NumberUtils.isParsable(option) && Range.between(1, 3).contains(Integer.parseInt(option)),
                "1. Medical", "2. Surgery", "3. Aesthetic"
        ));
        AppointmentTypes type = getAppointmentType(appointmentOption);
        WorkingDays day = WorkingDays.valueOf(ConsoleMenu.renderAndRead("Which day of the week do you prefer the " +
                "appointment in?").toUpperCase(Locale.ROOT).trim());
        Employee employee = employeeDAO.findAvailable(type.findAssociatedRole(), day).get(0);
        return new Appointment(type, day, patient, employee);
    }

    private static AppointmentTypes getAppointmentType(int option) {
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
