package model;

import java.io.BufferedReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tedavi {

    private int tedavi_id = 1;
    private String tedavi_adi;

    public Tedavi(int tedavi_id, String tedavi_adi) {
        this.tedavi_id =tedavi_id;
        this.tedavi_adi = tedavi_adi;
    }
    
    
    public static ObservableList<String> getTedaviAdiFromFile() {
        ObservableList<String> tedaviAdi = FXCollections.observableArrayList();
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("tedavi");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                String tedavi = satir[1];
                tedaviAdi.add(tedavi);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tedaviAdi;
    }
    

    public int getTedavi_id() {
        return tedavi_id;
    }

    public void setTedavi_id(int tedavi_id) {
        this.tedavi_id = tedavi_id;
    }

    public String getTedavi_adi() {
        return tedavi_adi;
    }

    public void setTedavi_adi(String tedavi_adi) {
        this.tedavi_adi = tedavi_adi;
    }
    

    @Override
    public String toString() {
        return + getTedavi_id() + "\t" + getTedavi_adi();
    }
    
    
    public String getTedaviAciklama(){
        return getTedaviAciklama();
    }

}
