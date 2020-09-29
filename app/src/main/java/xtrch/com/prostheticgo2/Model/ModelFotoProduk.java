package xtrch.com.prostheticgo2.Model;

public class ModelFotoProduk {

    String idFotoProduk, fotoProduk, idProduk;

    public ModelFotoProduk(String idFotoProduk, String fotoProduk, String idProduk) {
        this.idFotoProduk = idFotoProduk;
        this.fotoProduk = fotoProduk;
        this.idProduk = idProduk;
    }

    public String getIdFotoProduk() {
        return idFotoProduk;
    }

    public void setIdFotoProduk(String idFotoProduk) {
        this.idFotoProduk = idFotoProduk;
    }

    public String getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(String fotoProduk) {
        this.fotoProduk = fotoProduk;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }
}
