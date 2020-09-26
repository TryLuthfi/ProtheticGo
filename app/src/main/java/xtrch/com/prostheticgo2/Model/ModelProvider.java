package xtrch.com.prostheticgo2.Model;

public class ModelProvider {

    String idProvider, namaProvider, fotoProvider, noHpProvider;

    public ModelProvider(String idProvider, String namaProvider, String fotoProvider, String noHpProvider) {
        this.idProvider = idProvider;
        this.namaProvider = namaProvider;
        this.fotoProvider = fotoProvider;
        this.noHpProvider = noHpProvider;
    }

    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    public String getNamaProvider() {
        return namaProvider;
    }

    public void setNamaProvider(String namaProvider) {
        this.namaProvider = namaProvider;
    }

    public String getFotoProvider() {
        return fotoProvider;
    }

    public void setFotoProvider(String fotoProvider) {
        this.fotoProvider = fotoProvider;
    }

    public String getNoHpProvider() {
        return noHpProvider;
    }

    public void setNoHpProvider(String noHpProvider) {
        this.noHpProvider = noHpProvider;
    }
}
