package Model;


public class Polyclinic {
    private String namePolyclinic;
    private String adress;

    public Polyclinic(String namePolyclinic, String adress) {
        this.namePolyclinic = namePolyclinic;
        this.adress = adress;
    }

    public String getNamePolyclinic() {
        return namePolyclinic;
    }

    public String getAdress() {
        return adress;
    }
}
