package Model;

import lombok.Data;

@Data
public class Doctor {
    private int doctorId;
    private String name;
    private String surname;
    private String middleName;
    private String phoneNumber;
    private int office;
    private String post;

    public Doctor(int doctorId, String name, String surname, String middleName, String phoneNumber, int office, String post) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.office = office;
        this.post = post;
        this.doctorId = doctorId;
    }

    public int getDoctorId() {
        return doctorId;
    }
}
