package co.com.sanipet.modules.appointments.services;

import co.com.sanipet.modules.appointments.dao.EmployeeDAO;
import co.com.sanipet.modules.appointments.entities.Employee;
import co.com.sanipet.modules.appointments.entities.Roles;
import co.com.sanipet.modules.appointments.entities.WorkingDays;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Employee> findAvailable(Roles role, LocalDate date) {
        WorkingDays day = WorkingDays.fromInteger(date.getDayOfWeek().getValue());
        return employeeDAO.findAll()
                .stream()
                .filter(employee -> employee.getRole() == role)
                .filter(employee -> employee.getSchedule().getActiveDays().containsKey(day))
                .collect(Collectors.toList());
    }
}
