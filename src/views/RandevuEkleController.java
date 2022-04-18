package views;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Randevu;
import model.Doktor;
import model.DosyaIslemleri;
import model.Hasta;
import model.Tedavi;
import org.controlsfx.control.textfield.TextFields;

public class RandevuEkleController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private Button btnKaydet;
    @FXML
    private Button btnIptal;
    @FXML
    private ComboBox<String> cmbDoktor;
    @FXML
    private ComboBox<String> cmbTedavi;
    @FXML
    private DatePicker dateTarih;
    @FXML
    private TextField txtHasta;
    @FXML
    private ComboBox<String> cmbSaat;

    private static ObservableList<Randevu> randevuList;
    ObservableList<String> hastaAdi = FXCollections.observableArrayList();
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE-MMM dd- yyyy");
    
    
    

    public static void setRandevuList(ObservableList<Randevu> randevuList) {
        RandevuEkleController.randevuList = randevuList;
    }

    private ObservableList<String> saatListesi = FXCollections.observableArrayList("9:00", "9:30", "10:00", "10:30", "11:00", "11:30",
            "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbDoktor.setValue("Lütfen Doktor Seçiniz.");
        cmbDoktor.setItems(Doktor.getDoktorAdiFromFile());
        cmbTedavi.setValue("Lütfen Tedavi Seçiniz.");
        cmbTedavi.setItems(Tedavi.getTedaviAdiFromFile());
        cmbSaat.setValue("Lütfen Saat Seçiniz");
        cmbSaat.setItems(saatListesi);

        hastaAdi.addAll(Hasta.getHastaAdiFromFile());
        TextFields.bindAutoCompletion(txtHasta, hastaAdi);
        
        

    }

    @FXML
    private void randevuKaydet(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String doktorAdi = cmbDoktor.getValue();
        String hastaAdi = txtHasta.getText();
        String tedavi = cmbTedavi.getValue();
        String randevuTarihi = dateTarih.getValue().format(formatter);
        String saat = cmbSaat.getValue();
    
        Randevu r = new Randevu(id, doktorAdi, hastaAdi, tedavi, randevuTarihi, saat);
        randevuList.add(r);
        DosyaIslemleri.dosyayaYaz(randevuList, "randevu");
        
        closeStage(event);
    }

    @FXML
    private void randevuEkleKapat(ActionEvent event) {
        closeStage(event);
        
    }

    
    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
