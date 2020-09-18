package xtrch.com.prostheticgo2.Model;

public class ModelRehabLower {

    String idRehab, judulRehab, isiRehab, fotoRehab, jenisRehab, idUser, tglInput, namaUser, fotoUser, emailUser;

    public ModelRehabLower(){}

    public ModelRehabLower(String idRehab, String judulRehab, String isiRehab, String fotoRehab, String jenisRehab, String idUser, String tglInput, String namaUser, String fotoUser, String emailUser) {
        this.idRehab = idRehab;
        this.judulRehab = judulRehab;
        this.isiRehab = isiRehab;
        this.fotoRehab = fotoRehab;
        this.jenisRehab = jenisRehab;
        this.idUser = idUser;
        this.tglInput = tglInput;
        this.namaUser = namaUser;
        this.fotoUser = fotoUser;
        this.emailUser = emailUser;
    }

    public String getIdRehab() {
        return idRehab;
    }

    public void setIdRehab(String idRehab) {
        this.idRehab = idRehab;
    }

    public String getJudulRehab() {
        return judulRehab;
    }

    public void setJudulRehab(String judulRehab) {
        this.judulRehab = judulRehab;
    }

    public String getIsiRehab() {
        return isiRehab;
    }

    public void setIsiRehab(String isiRehab) {
        this.isiRehab = isiRehab;
    }

    public String getFotoRehab() {
        return fotoRehab;
    }

    public void setFotoRehab(String fotoRehab) {
        this.fotoRehab = fotoRehab;
    }

    public String getJenisRehab() {
        return jenisRehab;
    }

    public void setJenisRehab(String jenisRehab) {
        this.jenisRehab = jenisRehab;
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
