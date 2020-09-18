package xtrch.com.prostheticgo2.Model;

public class ModelPsikologi {

    String idPsiko, judulPsiko, isiPsiko, fotoPsiko, idUser, tglInput, namaUser, fotoUser, emailUser;

    public ModelPsikologi(){}

    public ModelPsikologi(String idPsiko, String judulPsiko, String isiPsiko, String fotoPsiko, String idUser, String tglInput, String namaUser, String fotoUser, String emailUser) {
        this.idPsiko = idPsiko;
        this.judulPsiko = judulPsiko;
        this.isiPsiko = isiPsiko;
        this.fotoPsiko = fotoPsiko;
        this.idUser = idUser;
        this.tglInput = tglInput;
        this.namaUser = namaUser;
        this.fotoUser = fotoUser;
        this.emailUser = emailUser;
    }

    public String getIdPsiko() {
        return idPsiko;
    }

    public void setIdPsiko(String idPsiko) {
        this.idPsiko = idPsiko;
    }

    public String getJudulPsiko() {
        return judulPsiko;
    }

    public void setJudulPsiko(String judulPsiko) {
        this.judulPsiko = judulPsiko;
    }

    public String getIsiPsiko() {
        return isiPsiko;
    }

    public void setIsiPsiko(String isiPsiko) {
        this.isiPsiko = isiPsiko;
    }

    public String getFotoPsiko() {
        return fotoPsiko;
    }

    public void setFotoPsiko(String fotoPsiko) {
        this.fotoPsiko = fotoPsiko;
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
