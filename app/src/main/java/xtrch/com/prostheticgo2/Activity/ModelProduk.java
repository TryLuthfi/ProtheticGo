package xtrch.com.prostheticgo2.Activity;

public class ModelProduk {

    String idProduk, namaProduk, deskProduk, beratProduk, stokProduk, hargaProduk, idProvider, tgl_input, noHpProvider, fotoProduk;

    public ModelProduk(String idProduk, String namaProduk, String deskProduk, String beratProduk, String stokProduk, String hargaProduk, String idProvider, String tgl_input, String noHpProvider, String fotoProduk) {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.deskProduk = deskProduk;
        this.beratProduk = beratProduk;
        this.stokProduk = stokProduk;
        this.hargaProduk = hargaProduk;
        this.idProvider = idProvider;
        this.tgl_input = tgl_input;
        this.noHpProvider = noHpProvider;
        this.fotoProduk = fotoProduk;
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

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getDeskProduk() {
        return deskProduk;
    }

    public void setDeskProduk(String deskProduk) {
        this.deskProduk = deskProduk;
    }

    public String getBeratProduk() {
        return beratProduk;
    }

    public void setBeratProduk(String beratProduk) {
        this.beratProduk = beratProduk;
    }

    public String getStokProduk() {
        return stokProduk;
    }

    public void setStokProduk(String stokProduk) {
        this.stokProduk = stokProduk;
    }

    public String getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(String hargaProduk) {
        this.hargaProduk = hargaProduk;
    }

    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    public String getTgl_input() {
        return tgl_input;
    }

    public void setTgl_input(String tgl_input) {
        this.tgl_input = tgl_input;
    }

    public String getNoHpProvider() {
        return noHpProvider;
    }

    public void setNoHpProvider(String noHpProvider) {
        this.noHpProvider = noHpProvider;
    }
}
