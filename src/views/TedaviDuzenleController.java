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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DisCekimi;
import model.Tedavi;
import model.Dolgu;
import model.DosyaIslemleri;
import model.KanalTedavi;
import model.ProtezDis;
import org.controlsfx.control.textfield.TextFields;

public class TedaviDuzenleController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private Button btnKaydet;
    @FXML
    private Button btnIptal;
    @FXML
    private TextField txtAd;
    @FXML
    private TextArea txtAciklama;

    private static ObservableList<Tedavi> tedaviList;
    ObservableList<String> tedaviAdi = FXCollections.observableArrayList("Dolgu", "Diş Çekimi", "Protez Diş", "Kanal Tedavi");

    private Tedavi secilenTedavi;

    public void initData(Tedavi tedavi) {
        secilenTedavi = tedavi;
        txtId.setText(String.valueOf(secilenTedavi.getTedavi_id()));
        txtAd.setText(secilenTedavi.getTedavi_adi());
        txtAciklama.setText(secilenTedavi.getTedaviAciklama());

        TextFields.bindAutoCompletion(txtAd, tedaviAdi);
    }

    public static void setTedaviList(ObservableList<Tedavi> tedaviList) {
        TedaviDuzenleController.tedaviList = tedaviList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void tedaviDuzenle(ActionEvent event) {
        int tedaviID = Integer.parseInt(txtId.getText().trim());
        String tedaviAdi = txtAd.getText().trim();
        String tedaviAciklama = txtAciklama.getText().trim();
        if ("Dolgu".equals(tedaviAdi)) {
            tedaviList.remove(secilenTedavi);
            Dolgu d = new Dolgu(tedaviID, tedaviAdi, tedaviAciklama);
            tedaviList.add(d);
            DosyaIslemleri.dosyayaYaz(tedaviList, "tedavi");
        } else if ("Dis Cekimi".equals(tedaviAdi)) {
            tedaviList.remove(secilenTedavi);
            DisCekimi dc = new DisCekimi(tedaviID, tedaviAdi, tedaviAciklama);
            tedaviList.add(dc);
            DosyaIslemleri.dosyayaYaz(tedaviList, "tedavi");
        } else if ("Protez Diş".equals(tedaviAdi)) {
            tedaviList.remove(secilenTedavi);
            ProtezDis pd = new ProtezDis(tedaviID, tedaviAdi, tedaviAciklama);
            tedaviList.add(pd);
            DosyaIslemleri.dosyayaYaz(tedaviList, "tedavi");
        } else if ("Kanal Tedavi".equals(tedaviAdi)) {
            tedaviList.remove(secilenTedavi);
            KanalTedavi kt = new KanalTedavi(tedaviID, tedaviAdi, tedaviAciklama);
            tedaviList.add(kt);
            DosyaIslemleri.dosyayaYaz(tedaviList, "tedavi");
        }else{
            tedaviList.remove(secilenTedavi);
            Tedavi t = new Dolgu(tedaviID, tedaviAdi,tedaviAciklama);
            tedaviList.add(t);
            DosyaIslemleri.dosyayaYaz(tedaviList,  "tedavi");
        }
        closeStage(event);
    }

    @FXML
    private void tedaviDuzenleClose(ActionEvent event) {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
