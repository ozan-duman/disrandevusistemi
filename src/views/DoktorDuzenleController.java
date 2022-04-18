package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cerrah;
import model.Doktor;
import model.DosyaIslemleri;
import model.Endodonti;
import model.Ortodonti;
import model.Pedodonti;

public class DoktorDuzenleController implements Initializable {

    @FXML
    private TextField txtDuzenleId;

    @FXML
    private Button btnDuzenleKaydet;

    @FXML
    private Button btnIptal;

    @FXML
    private TextField txtDuzenleAdSoyad;

    @FXML
    private TextField txtDuzenleTelefon;

    @FXML
    private ComboBox<String> cmbDuzenleCinsiyet;

    @FXML
    private ComboBox<String> cmbDuzenleUnvan;

    ObservableList<String> cinsiyet = FXCollections.observableArrayList("Kadın", "Erkek");
    ObservableList<String> unvan = FXCollections.observableArrayList("Cerrah", "Ortodonti", "Pedodonti", "Endodonti");
    private static ObservableList<Doktor> doktorList;

    public static void setDoktorList(ObservableList<Doktor> doktorList) {
        DoktorDuzenleController.doktorList = doktorList;
    }

    private Doktor secilenDoktor;

    public void initData(Doktor doktor) {
        secilenDoktor = doktor;
        txtDuzenleId.setText(String.valueOf(secilenDoktor.getId()));
        txtDuzenleAdSoyad.setText(secilenDoktor.getIsimSoyisim());
        txtDuzenleTelefon.setText(secilenDoktor.getTelefonNo());
        cmbDuzenleCinsiyet.setValue(secilenDoktor.getCinsiyet());
        cmbDuzenleUnvan.setValue(secilenDoktor.getUnvan());
    }

    @FXML
    void doktorDuzenle(ActionEvent event) {
        int doktorId = Integer.parseInt(txtDuzenleId.getText().trim());
        String doktorAdSoyad = txtDuzenleAdSoyad.getText().trim();
        String doktorTelefon = txtDuzenleTelefon.getText().trim();
        String doktorCinsiyet = cmbDuzenleCinsiyet.getSelectionModel().getSelectedItem().toString();
        String doktorUnvan = (String) cmbDuzenleUnvan.getValue();
        if ("Cerrah".equals(doktorUnvan)) {
            doktorList.remove(secilenDoktor);
            Cerrah c = new Cerrah(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(c);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        } else if ("Ortodonti".equals(doktorUnvan)) {
            doktorList.remove(secilenDoktor);
            Ortodonti o = new Ortodonti(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(o);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        } else if ("Pedodonti".equals(doktorUnvan)) {
            doktorList.remove(secilenDoktor);
            Pedodonti p = new Pedodonti(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(p);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        } else if ("Endodonti".equals(doktorUnvan)) {
            doktorList.remove(secilenDoktor);
            Endodonti e = new Endodonti(doktorId, doktorAdSoyad, doktorTelefon, doktorCinsiyet, doktorUnvan);
            doktorList.add(e);
            DosyaIslemleri.dosyayaYaz(doktorList, "doktor");
        }
        closeStage(event);
    }

    @FXML
    void doktorDuzenleClose(ActionEvent event) {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbDuzenleCinsiyet.setValue("Kadın");
        cmbDuzenleCinsiyet.setItems(cinsiyet);
        cmbDuzenleUnvan.setValue("Cerrah");
        cmbDuzenleUnvan.setItems(unvan);
    }

}
