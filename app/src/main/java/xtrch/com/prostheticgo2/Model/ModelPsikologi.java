package xtrch.com.prostheticgo2.Model;

public class ModelPsikologi {

    String idPsiko, judulPsiko, isiPsiko, fotoPsiko, idUser, tglInput, namaDepanUser, namaBelakangUser, emailUser;

    public ModelPsikologi() {
    }

    public ModelPsikologi(String idPsiko, String judulPsiko, String isiPsiko, String fotoPsiko, String idUser, String tglInput, String namaDepanUser, String namaBelakangUser, String emailUser) {
        this.idPsiko = idPsiko;
        this.judulPsiko = judulPsiko;
        this.isiPsiko = isiPsiko;
        this.fotoPsiko = fotoPsiko;
        this.idUser = idUser;
        this.tglInput = tglInput;
        this.namaDepanUser = namaDepanUser;
        this.namaBelakangUser = namaBelakangUser;
        this.emailUser = emailUser;
    }

    public String getIdPsiko() {
        return idPsiko;
    }

    public String getJudulPsiko() {
        return judulPsiko;
    }

    public String getIsiPsiko() {
        return isiPsiko;
    }

    public String getFotoPsiko() {
        return fotoPsiko;
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
