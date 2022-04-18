package model;

import java.io.BufferedReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Doktor {

    private int id;
    private String isimSoyisim;
    private String telefonNo;
    private String cinsiyet;
    protected final static String dbFileName = "doktor";
    

    public Doktor(int id, String isimSoyisim, String telefonNo, String cinsiyet) {
        this.id = id;
        this.isimSoyisim = isimSoyisim;
        this.telefonNo = telefonNo;
        this.cinsiyet = cinsiyet;
    }
    
    public static ObservableList<String> getDoktorAdiFromFile() {
        ObservableList<String> doktorAdi = FXCollections.observableArrayList();
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir(dbFileName);
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                String doktor = satir[1];
                doktorAdi.add(doktor);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doktorAdi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public void setIsimSoyisim(String isimSoyisim) {
        this.isimSoyisim = isimSoyisim;
    }

    public String getTelefonNo() {
        return telefonNo;
    }

    public void setTelefonNo(String telefonNo) {
        this.telefonNo = telefonNo;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }
    
    public String getUnvan(){
        return getUnvan();
    }


    @Override
    public String toString() {
        return  getId() + "\t"+ getIsimSoyisim() + "\t"+ getTelefonNo() + "\t"+ getCinsiyet();
    }

}
