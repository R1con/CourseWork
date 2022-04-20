package Model;

import lombok.Data;

@Data
public class Polyclinic {
    private int polyclinicID;
    private String NamePolyclinic;
    private String adress;

    public Polyclinic(String namePolyclinic, String adress) {
        NamePolyclinic = namePolyclinic;
        this.adress = adress;
    }
}
