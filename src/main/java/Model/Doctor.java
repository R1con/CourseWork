package Model;

import lombok.Data;

@Data
public class Doctor {
    private String name;
    private String surname;
    private String middleName;
    private String phoneNumber;
    private int office;
//    private int postID;
//    private int polyclinicID;

    public Doctor(String name, String surname, String middleName, String phoneNumber, int office) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.office = office;
    }
}
