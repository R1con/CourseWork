package Model;

public class Diagnsis {
    private int DiagnsisID;
    private String NameDiagnsis;

    public Diagnsis(int idDiagnsis, String nameDiagnsis) {
        this.DiagnsisID = idDiagnsis;
        NameDiagnsis = nameDiagnsis;
    }
    public int getDiagnsisID() {
        return DiagnsisID;
    }

    public void setIdDiagnsis(int idDiagnsis) {
        this.DiagnsisID = idDiagnsis;
    }

    public String getNameDiagnsis() {
        return NameDiagnsis;
    }

    public void setNameDiagnsis(String nameDiagnsis) {
        NameDiagnsis = nameDiagnsis;
    }
}
