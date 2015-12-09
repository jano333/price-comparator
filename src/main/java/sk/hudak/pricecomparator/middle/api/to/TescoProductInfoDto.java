package sk.hudak.pricecomparator.middle.api.to;

/**
 * Created by jan on 6. 12. 2015.
 */
public class TescoProductInfoDto {
    private String nazov;
    private String url;
    private String kategoria;
    private String imagePath;

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
