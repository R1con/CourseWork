package Model;

public class Pacient {
    private String name;
    private String surname;
    private String middleName;
    private String gender;
    private String birthday;
    private String phoneNumber;
    private String adress;
    private int insurancepPolicy;

    public Pacient(String name, String surname, String middleName, String gender, String birthday, String phoneNumber, String adress, int insurancepPolicy) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.insurancepPolicy = insurancepPolicy;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getInsurancepPolicy() {
        return insurancepPolicy;
    }

    public void setInsurancepPolicy(int insurancepPolicy) {
        this.insurancepPolicy = insurancepPolicy;
    }
}
