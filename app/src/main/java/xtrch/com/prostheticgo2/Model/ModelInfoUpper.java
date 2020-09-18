package xtrch.com.prostheticgo2.Model;

public class ModelInfoUpper {

    String idInfo, judulInfo, isiInfo, fotoInfo, jenisInfo, idUser, tglInput, namaUser, fotoUser, emailUser;

    public ModelInfoUpper(){}

    public ModelInfoUpper(String idInfo, String judulInfo, String isiInfo, String fotoInfo, String jenisInfo, String idUser, String tglInput, String namaUser, String fotoUser, String emailUser) {
        this.idInfo = idInfo;
        this.judulInfo = judulInfo;
        this.isiInfo = isiInfo;
        this.fotoInfo = fotoInfo;
        this.jenisInfo = jenisInfo;
        this.idUser = idUser;
        this.tglInput = tglInput;
        this.namaUser = namaUser;
        this.fotoUser = fotoUser;
        this.emailUser = emailUser;
    }

    public String getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(String idInfo) {
        this.idInfo = idInfo;
    }

    public String getJudulInfo() {
        return judulInfo;
    }

    public void setJudulInfo(String judulInfo) {
        this.judulInfo = judulInfo;
    }

    public String getIsiInfo() {
        return isiInfo;
    }

    public void setIsiInfo(String isiInfo) {
        this.isiInfo = isiInfo;
    }

    public String getFotoInfo() {
        return fotoInfo;
    }

    public void setFotoInfo(String fotoInfo) {
        this.fotoInfo = fotoInfo;
    }

    public String getJenisInfo() {
        return jenisInfo;
    }

    public void setJenisInfo(String jenisInfo) {
        this.jenisInfo = jenisInfo;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTglInput() {
        return tglInput;
    }

    public void setTglInput(String tglInput) {
        this.tglInput = tglInput;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getFotoUser() {
        return fotoUser;
    }

    public void setFotoUser(String fotoUser) {
        this.fotoUser = fotoUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
