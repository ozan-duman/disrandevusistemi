package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Cerrah;
import model.Doktor;
import model.DosyaIslemleri;
import model.Endodonti;
import model.Ortodonti;
import model.Pedodonti;

public class DoktorEkleController implements Initializable {

    @FXML
    private TextField txtId;

    @FXML
    private Button btnKaydet;

    @FXML
    private Button btnIptal;

    @FXML
    private TextField txtAdSoyad;

    @FXML
    private TextField txtTelefon;

    @FXML
    private ComboBox cmbCinsiyet;

    @FXML
    private ComboBox cmbUnvan;

    ObservableList<String> cinsiyet = FXCollections.observableArrayList("Kadın", "Erkek");
    ObservableList<String> unvan = FXCollections.observableArrayList("Cerrah", "Ortodonti", "Pedodonti", "Endodonti");
    private static ObservableList<Doktor> doktorList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbCinsiyet.setValue("Kadın");
        cmbCinsiyet.setItems(cinsiyet);
        cmbUnvan.setValue("Cerrah");
        cmbUnvan.setItems(unvan);

    }

    @FXML
    void doktorEkle(ActionEvent event) {

        int doktorId = Integer.parseInt(txtId.getText().trim());
        String doktorAdSoyad = txtAdSoyad.getText().trim();
        String doktorTelefon = txtTelefon.getText().trim();
        String doktorCinsiyet = cmbCinsiyet.getSelectionModel().getSelectedItem().toString();
        String doktorUnvan = (String) cmbUnvan.getValue();
        if ("Cerrah".equals(doktorUnvan)) {
            Cerrah c = new Cerrah(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(c);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        } else if ("Pedodonti".equals(doktorUnvan)) {
            Pedodonti p = new Pedodonti(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(p);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        } else if ("Endodonti".equals(doktorUnvan)) {
            Endodonti e = new Endodonti(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(e);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        } else if ("Ortodonti".equals(doktorUnvan)) {
            Ortodonti o = new Ortodonti(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(o);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        }
        closeStage(event);
    }

    public void setDoktorList(ObservableList<Doktor> doktorList) {
        this.doktorList = doktorList;
    }

    @FXML
    void doktorEkleClose(ActionEvent event) {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
