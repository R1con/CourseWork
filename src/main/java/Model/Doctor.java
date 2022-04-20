package Model;

import lombok.Data;

@Data
public class Doctor {
    private String Name;
    private String Surname;
    private String MiddleName;
    private String PhoneNumber;
    private int Office;
    private int PostID;
    private int PolyclinicID;

    public Doctor(String name, String surname, String middleName, String phoneNumber, int office) {
        Name = name;
        Surname = surname;
        MiddleName = middleName;
        PhoneNumber = phoneNumber;
        Office = office;
    }
}
