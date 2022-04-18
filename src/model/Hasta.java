package model;

import java.io.BufferedReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hasta {

    private int hasta_id;
    private String hasta_adSoyad;
    private String hasta_telefonNo;
    private String hasta_email;

    public Hasta(int hasta_id, String hasta_adSoyad, String hasta_telefonNo, String hasta_email) {
        this.hasta_id = hasta_id;
        this.hasta_adSoyad = hasta_adSoyad;
        this.hasta_telefonNo = hasta_telefonNo;
        this.hasta_email = hasta_email;

    }
    
    public static ObservableList<String> getHastaAdiFromFile() {
        ObservableList<String> hastaAdi = FXCollections.observableArrayList();
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("hasta");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                String hasta = satir[1];
                hastaAdi.add(hasta);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hastaAdi;
    }

    public int getHasta_id() {
        return hasta_id;
    }

    public void setHasta_id(int hasta_id) {
        this.hasta_id = hasta_id;
    }

    public String getHasta_adSoyad() {
        return hasta_adSoyad;
    }

    public void setHasta_adSoyad(String hasta_adSoyad) {
        this.hasta_adSoyad = hasta_adSoyad;
    }

    public String getHasta_telefonNo() {
        return hasta_telefonNo;
    }

    public void setHasta_telefonNo(String hasta_telefonNo) {
        this.hasta_telefonNo = hasta_telefonNo;
    }

    public String getHasta_email() {
        return hasta_email;
    }

    public void setHasta_email(String hasta_email) {
        this.hasta_email = hasta_email;
    }

    @Override
    public String toString() {
        return getHasta_id() + "\t" + getHasta_adSoyad() + "\t" + getHasta_telefonNo() + "\t" + getHasta_email();
    }

}
