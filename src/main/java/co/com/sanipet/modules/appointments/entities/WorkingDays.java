package co.com.sanipet.modules.appointments.entities;

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

    public Integer getWeekday() {
        return weekday;
    }
}
