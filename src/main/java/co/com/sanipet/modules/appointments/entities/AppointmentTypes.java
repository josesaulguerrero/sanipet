package co.com.sanipet.modules.appointments.entities;

public enum AppointmentTypes {
    MEDICAL,
    SURGERY,
    AESTHETIC;

    public Roles findAssociatedRole() {
        return this.name().equals("AESTHETIC") ? Roles.STYLIST : Roles.DOCTOR;
    }
}
