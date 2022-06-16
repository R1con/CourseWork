package Model;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
