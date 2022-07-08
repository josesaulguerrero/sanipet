package co.com.sanipet.modules.appointments.entities;

import java.util.Map;

public class Schedule {

    Map<WorkingDays, String> activeDays;

    public Schedule(Map<WorkingDays, String> activeDays) {
        this.activeDays = activeDays;
    }

    public Map<WorkingDays, String> getActiveDays() {
        return activeDays;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "   activeDays=" + activeDays +
                '}';
    }
}
