package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DosyaIslemleri {

    static String path;
    static String directory = "dosyalar";

    private DosyaIslemleri() {
    }

    public static void dosyayaYaz(ObservableList list, String p) {
        setDosyalarIcinAnaDizin(p);
        try (BufferedWriter yazici = new BufferedWriter(new FileWriter(path))) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                yazici.write(list.get(i).toString());
                yazici.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static BufferedReader dosyayiCagir(String dosyaAdi){
        try {
            setDosyalarIcinAnaDizin(dosyaAdi);
            return new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList dosyadanOku(String p) {
        setDosyalarIcinAnaDizin(p);
        ObservableList<Object> geciciList = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String oAnkiSatir;
            while ((oAnkiSatir = br.readLine()) != null) {
                geciciList.add(oAnkiSatir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return geciciList;
    }
    
    private static void setDosyalarIcinAnaDizin (String fileName)
    {
        String basePath = System.getProperty("user.dir") + "/"+ directory + "/" + fileName + ".txt";
        try {
        File file= new File(basePath);
            if (!file.exists()) {
                File dir = new File(directory);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        path = basePath;
    }

}
