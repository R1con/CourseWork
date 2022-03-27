package Model;

public class Polyclinic {
    private int polyclinicID;
    private String NamePolyclinic;
    private String adress;

    public Polyclinic(String namePolyclinic, String adress) {
        NamePolyclinic = namePolyclinic;
        this.adress = adress;
    }

    public int getPolyclinicID() {
        return polyclinicID;
    }

    public void setPolyclinicID(int polyclinicID) {
        this.polyclinicID = polyclinicID;
    }

    public String getNamePolyclinic() {
        return NamePolyclinic;
    }

    public void setNamePolyclinic(String namePolyclinic) {
        NamePolyclinic = namePolyclinic;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
