package Model;

public class Doctor {
    private int DoctorID;
    private String Name;
    private String Surname;
    private String MiddleName;
    private String PhoneNumber;
    private int Office;
    private int PostID;
    private int PolyclinicID;

    public Doctor(int doctorID, String name, String surname, String middleName, String phoneNumber, int office, int postID, int polyclinicID) {
        DoctorID = doctorID;
        Name = name;
        Surname = surname;
        MiddleName = middleName;
        PhoneNumber = phoneNumber;
        Office = office;
        PostID = postID;
        PolyclinicID = polyclinicID;
    }

    public Doctor(int doctorID, String name, String surname, String middleName, String phoneNumber, int office) {
        DoctorID = doctorID;
        Name = name;
        Surname = surname;
        MiddleName = middleName;
        PhoneNumber = phoneNumber;
        Office = office;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getOffice() {
        return Office;
    }

    public void setOffice(int office) {
        Office = office;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public int getPolyclinicID() {
        return PolyclinicID;
    }

    public void setPolyclinicID(int polyclinicID) {
        PolyclinicID = polyclinicID;
    }


}
