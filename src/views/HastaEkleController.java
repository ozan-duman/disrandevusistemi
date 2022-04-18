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

/**
 * FXML Controller class
 *
 * @author tolga
 */
public class HastaEkleController implements Initializable {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtAdSoyad;

    @FXML
    private TextField txtTelNo;

    @FXML
    private TextField txtMail;

    @FXML
    private Button btnİptal;

    @FXML
    private Button btnKaydet;

    private static ObservableList<Hasta> hastaList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void hastaEkle(ActionEvent event) {
        int hastaId = Integer.parseInt(txtId.getText().trim());
        String hastaAdSoyad = txtAdSoyad.getText().trim();
        String hastaTeleonNo = txtTelNo.getText().trim();
        String hastaMail = txtMail.getText().trim();
        Hasta h = new Hasta(hastaId, hastaAdSoyad, hastaTeleonNo, hastaMail);
        hastaList.add(h);
        DosyaIslemleri.dosyayaYaz(hastaList, "hasta");
        
        
        
        closeStage(event);
    }

    public void setHastaList(ObservableList<Hasta> hastaList) {
        this.hastaList = hastaList;
    }

    @FXML
    void hastaEkleClose(ActionEvent event) {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
