package co.com.sanipet.modules.appointments.entities;

public enum AppointmentTypes {
    MEDICAL,
    SURGERY,
    AESTHETIC;

    public Roles findAssociatedRole(AppointmentTypes type) {
        return type == AppointmentTypes.AESTHETIC ? Roles.STYLIST : Roles.DOCTOR;
    }
}
