package xtrch.com.prostheticgo2.Model;

public class ModelInfoLower {

    String idInfo, judulInfo, isiInfo, fotoInfo, jenisInfo, idUser, tglInput, namaDepanUser, namaBelakangUser, emailUser;

    public ModelInfoLower(String idInfo, String judulInfo, String isiInfo, String fotoInfo, String jenisInfo, String idUser, String tglInput, String namaDepanUser, String namaBelakangUser, String emailUser) {
        this.idInfo = idInfo;
        this.judulInfo = judulInfo;
        this.isiInfo = isiInfo;
        this.fotoInfo = fotoInfo;
        this.jenisInfo = jenisInfo;
        this.idUser = idUser;
        this.tglInput = tglInput;
        this.namaDepanUser = namaDepanUser;
        this.namaBelakangUser = namaBelakangUser;
        this.emailUser = emailUser;
    }

    public ModelInfoLower(){}

    public String getIdInfo() {
        return idInfo;
    }

    public String getJudulInfo() {
        return judulInfo;
    }

    public String getIsiInfo() {
        return isiInfo;
    }

    public String getFotoInfo() {
        return fotoInfo;
    }

    public String getJenisInfo() {
        return jenisInfo;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getTglInput() {
        return tglInput;
    }

    public String getNamaDepanUser() {
        return namaDepanUser;
    }

    public String getNamaBelakangUser() {
        return namaBelakangUser;
    }

    public String getEmailUser() {
        return emailUser;
    }
}
