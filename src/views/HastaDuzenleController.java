/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DosyaIslemleri;
import model.Hasta;

public class HastaDuzenleController implements Initializable {

    @FXML
    private TextField txtDuzenleId;

    @FXML
    private TextField txtDuzenleAdSoyad;

    @FXML
    private TextField txtDuzenleTelNo;

    @FXML
    private TextField txtDuzenleMail;

    @FXML
    private Button btnİptal;

    @FXML
    private Button btnKaydet;
    private static ObservableList<Hasta> hastaList;

    public static void setHastaList(ObservableList<Hasta> hastaList) {
        HastaDuzenleController.hastaList = hastaList;
    }
    private Hasta secilenHasta;

    public void initData(Hasta hasta) {
        secilenHasta = hasta;
        txtDuzenleId.setText(String.valueOf(secilenHasta.getHasta_id()));
        txtDuzenleAdSoyad.setText(secilenHasta.getHasta_adSoyad());
        txtDuzenleMail.setText(secilenHasta.getHasta_email());
        txtDuzenleTelNo.setText(secilenHasta.getHasta_telefonNo());
    }

    @FXML
    void hastaDuzenle(ActionEvent event) {
        int hastaId = Integer.parseInt(txtDuzenleId.getText().trim());
        String hastaAdSoyad = txtDuzenleAdSoyad.getText().trim();
        String hastaTelefon = txtDuzenleTelNo.getText().trim();
        String hastaMail = txtDuzenleMail.getText().trim();
        hastaList.remove(secilenHasta);
        Hasta h = new Hasta(hastaId, hastaAdSoyad, hastaTelefon, hastaMail);
        hastaList.add(h);
        DosyaIslemleri.dosyayaYaz(hastaList, "hasta");
        closeStage(event);
    }

    @FXML
    void hastaDuzenleClose(ActionEvent event) {
        closeStage(event);

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
