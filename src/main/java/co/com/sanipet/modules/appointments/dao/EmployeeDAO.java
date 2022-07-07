package co.com.sanipet.modules.appointments.dao;

import co.com.sanipet.modules.appointments.entities.Employee;
import co.com.sanipet.modules.appointments.entities.Roles;
import co.com.sanipet.modules.appointments.entities.WorkingDays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDAO {
    List<Employee> employees = List.of(
            new Employee("12312432", "John", "Newman", "1239872348",
                    Roles.DOCTOR, new WorkingDays[]{WorkingDays.MONDAY, WorkingDays.TUESDAY, WorkingDays.WEDNESDAY}),
            new Employee("79872493", "Diana", "Wilson", "9873298472",
                    Roles.DOCTOR, new WorkingDays[]{WorkingDays.MONDAY, WorkingDays.TUESDAY, WorkingDays.WEDNESDAY}),
            new Employee("80927434", "Ivan", "Ivanovich", "9023749025",
                    Roles.DOCTOR, new WorkingDays[]{WorkingDays.THURSDAY, WorkingDays.FRIDAY, WorkingDays.SATURDAY}),
            new Employee("09808908", "Elizabeth", "Mcbride", "9023749025",
                    Roles.STYLIST, new WorkingDays[]{WorkingDays.MONDAY,
                    WorkingDays.TUESDAY, WorkingDays.WEDNESDAY, WorkingDays.THURSDAY,
                    WorkingDays.FRIDAY, WorkingDays.SATURDAY}),
            new Employee("09808908", "Brenda", "Cooper", "9023749025",
                    Roles.STYLIST, new WorkingDays[]{WorkingDays.MONDAY,
                    WorkingDays.TUESDAY, WorkingDays.WEDNESDAY, WorkingDays.THURSDAY,
                    WorkingDays.FRIDAY, WorkingDays.SATURDAY})
    );

    public List<Employee> findAll() {
        return employees;
    }

    public List<Employee> findAvailable() {
        return employees.stream().filter((employee) -> {
            WorkingDays today =
                    WorkingDays.valueOf(LocalDate.now().getDayOfWeek().name());
            return Arrays.asList(employee.getSchedule().getActiveDays()).contains(today);
        }).collect(Collectors.toList());
    }
}
