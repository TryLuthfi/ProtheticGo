package xtrch.com.prostheticgo2.Model;

public class ModelRehabUpper {

    String idRehab, judulRehab, isiRehab, fotoRehab, jenisRehab, idUser, tglInput, namaDepanUser, namaBelakangUser, emailUser;

    public ModelRehabUpper() {
    }

    public ModelRehabUpper(String idRehab, String judulRehab, String isiRehab, String fotoRehab, String jenisRehab, String idUser, String tglInput, String namaDepanUser, String namaBelakangUser, String emailUser) {
        this.idRehab = idRehab;
        this.judulRehab = judulRehab;
        this.isiRehab = isiRehab;
        this.fotoRehab = fotoRehab;
        this.jenisRehab = jenisRehab;
        this.idUser = idUser;
        this.tglInput = tglInput;
        this.namaDepanUser = namaDepanUser;
        this.namaBelakangUser = namaBelakangUser;
        this.emailUser = emailUser;
    }

    public String getIdRehab() {
        return idRehab;
    }

    public String getJudulRehab() {
        return judulRehab;
    }

    public String getIsiRehab() {
        return isiRehab;
    }

    public String getFotoRehab() {
        return fotoRehab;
    }

    public String getJenisRehab() {
        return jenisRehab;
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
