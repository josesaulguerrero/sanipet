package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.Employee;
import co.com.sanipet.modules.appointments.entities.Roles;
import co.com.sanipet.modules.appointments.entities.Schedule;
import co.com.sanipet.modules.appointments.entities.WorkingDays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDAO {

    HashMap<WorkingDays, String> firstHalfSchedule = new HashMap<>();
    HashMap<WorkingDays, String> secondHalfSchedule = new HashMap<>();
    HashMap<WorkingDays, String> wholeWeekSchedule = new HashMap<>();

    List<Employee> employees = List.of(
            new Employee("12312432", "John", "Newman", "1239872348",
                    Roles.DOCTOR, new Schedule(firstHalfSchedule)),
            new Employee("79872493", "Diana", "Wilson", "9873298472",
                    Roles.DOCTOR, new Schedule(firstHalfSchedule)),
            new Employee("80927434", "Ivan", "Ivanovich", "9023749025",
                    Roles.DOCTOR, new Schedule(secondHalfSchedule)),
            new Employee("09808908", "Elizabeth", "Mcbride", "9023749025",
                    Roles.STYLIST, new Schedule(wholeWeekSchedule)),
            new Employee("09808908", "Brenda", "Cooper", "9023749025",
                    Roles.STYLIST, new Schedule(wholeWeekSchedule))
    );

    public EmployeeDAO() {
        String normalOperationHours = "08:00 - 19:00";
        String specialOperationHours = "09:00 - 15:00";
        firstHalfSchedule.put(WorkingDays.MONDAY, normalOperationHours);
        firstHalfSchedule.put(WorkingDays.TUESDAY, normalOperationHours);
        firstHalfSchedule.put(WorkingDays.WEDNESDAY, normalOperationHours);
        // -----------------------
        secondHalfSchedule.put(WorkingDays.THURSDAY, normalOperationHours);
        secondHalfSchedule.put(WorkingDays.FRIDAY, normalOperationHours);
        secondHalfSchedule.put(WorkingDays.SATURDAY, specialOperationHours);
        // ------------------------
        wholeWeekSchedule.putAll(firstHalfSchedule);
        wholeWeekSchedule.putAll(secondHalfSchedule);
    }

    public List<Employee> findAll() {
        return employees;
    }
}
