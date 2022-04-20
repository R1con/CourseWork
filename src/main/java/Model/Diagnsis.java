package Model;

import lombok.Data;

@Data
public class Diagnsis {
    private int diagnsisID;
    private String nameDiagnsis;

    public Diagnsis(int idDiagnsis, String nameDiagnsis) {
        diagnsisID = idDiagnsis;
        this.nameDiagnsis = nameDiagnsis;
    }
}
