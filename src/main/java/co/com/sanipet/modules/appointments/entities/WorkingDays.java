package co.com.sanipet.modules.appointments.entities;

import java.util.Arrays;

public enum WorkingDays {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private final Integer weekday;

    WorkingDays(int weekday) {
        this.weekday = weekday;
    }

    public static WorkingDays fromInteger(Integer integer) {
        if(integer.equals(7)) {
            throw new IllegalArgumentException("There's no service on sundays, we're sorry!");
        }
        return Arrays.stream(WorkingDays.values())
                .filter(day -> day.getWeekday().equals(integer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("We can't set an appointment for the given date!"));
    }

    public Integer getWeekday() {
        return weekday;
    }
}
