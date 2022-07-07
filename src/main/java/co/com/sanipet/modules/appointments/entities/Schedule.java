package co.com.sanipet.modules.appointments.entities;

import java.util.Arrays;

public class Schedule {
    WorkingDays[] activeDays;

    public Schedule(WorkingDays[] activeDays) {
        this.activeDays = activeDays;
    }

    public WorkingDays[] getActiveDays() {
        return activeDays;
    }

    @Override
    public String toString() {
        return "Schedule{" + "activeDays=" + Arrays.toString(activeDays) + '}';
    }
}
