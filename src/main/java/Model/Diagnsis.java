package Model;

import lombok.Data;

@Data
public class Diagnsis {
    private int DiagnsisID;
    private String NameDiagnsis;

    public Diagnsis(int idDiagnsis, String nameDiagnsis) {
        this.DiagnsisID = idDiagnsis;
        NameDiagnsis = nameDiagnsis;
    }
}
