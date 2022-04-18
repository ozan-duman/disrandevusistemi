
package model;

public class Randevu {

    private int id;
    private String doktor;
    private String hasta;
    private String tedavi;
    private String randevuTarihi;
    private String saat;

    public Randevu(int id, String doktor, String hasta, String tedavi, String randevuTarihi, String saat) {
        this.id = id;
        this.doktor = doktor;
        this.hasta = hasta;
        this.tedavi = tedavi;
        this.randevuTarihi = randevuTarihi;
        this.saat = saat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoktor() {
        return doktor;
    }

    public void setDoktor(String doktor) {
        this.doktor = doktor;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getTedavi() {
        return tedavi;
    }

    public void setTedavi(String tedavi) {
        this.tedavi = tedavi;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    } 

    public String getRandevuTarihi() {
        return randevuTarihi;
    }

    public void setRandevuTarihi(String randevuTarihi) {
        this.randevuTarihi = randevuTarihi;
    }

    @Override
    public String toString() {
        return  getId() + "\t" + getDoktor() + "\t" + getHasta() + "\t" + getTedavi() + "\t" + getRandevuTarihi() + "\t" + getSaat() ;
    }

    
    


    

    

}
