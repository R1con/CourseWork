package Model;

import lombok.Data;

@Data
public class Pacient {
    private String name;
    private String surname;
    private String middleName;
    private String gender;
    private String birthday;
    private String phoneNumber;
    private String adress;
    private int insurancepPolicy;

    public Pacient(String name, String surname, String middleName, String gender, String birthday,
                   String phoneNumber, String adress, int insurancepPolicy) {

        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.insurancepPolicy = insurancepPolicy;
    }
}
