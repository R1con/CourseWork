package Model;

import lombok.Data;

@Data
public class Polyclinic {
    private String namePolyclinic;
    private String adress;

    public Polyclinic(String namePolyclinic, String adress) {
        this.namePolyclinic = namePolyclinic;
        this.adress = adress;
    }
}
