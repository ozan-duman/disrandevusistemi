package views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DisCekimi;
import model.Dolgu;
import model.DosyaIslemleri;
import model.Tedavi;

/**
 * FXML Controller class
 *
 * @author RecepOrkun
 */
public class TedaviController implements Initializable {

    static ObservableList<Tedavi> tList = FXCollections.observableArrayList();

    @FXML
    private TableView<Tedavi> tedaviListesi;
    @FXML
    private Button tedaviSil;
    @FXML
    private TableColumn<Tedavi, Integer> tabloId = new TableColumn<>();
    @FXML
    private TableColumn<Tedavi, String> tabloTedaviAdi = new TableColumn<>();
    private TableColumn<Tedavi, String> tabloTedaviAciklama = new TableColumn<>();

    private ObservableList<Tedavi> getTedaviFromFile() {
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("tedavi");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                Dolgu d = new Dolgu(Integer.parseInt(satir[0]), satir[1], satir[2]);
                tList.add(d);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tList.removeAll(tList);
        tedaviListesi.getItems().clear();
        tedaviListesi.refresh();
        tabloId.setCellValueFactory(new PropertyValueFactory<>("tedavi_id"));
        tabloTedaviAdi.setCellValueFactory(new PropertyValueFactory<>("tedavi_adi"));
        tabloTedaviAciklama.setCellValueFactory(new PropertyValueFactory<>("tedaviAciklama"));
        tedaviListesi.setItems(getTedaviFromFile());
        tedaviListesi.setItems(tList);

        tedaviSil.setOnAction(e -> {
            Tedavi seciliTedavi = tedaviListesi.getSelectionModel().getSelectedItem();
            tedaviListesi.getItems().remove(seciliTedavi);
            tList.remove(seciliTedavi);
            DosyaIslemleri.dosyayaYaz(tList, "tedavi");
        });
    }

    @FXML
    void tedaviEkleDialog(ActionEvent event) throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("tedaviEkle.fxml"));
        Parent parent = fXMLLoader.load();
        TedaviEkleController tedaviEkleController = fXMLLoader.<TedaviEkleController>getController();
        tedaviEkleController.setTedaviList(tList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Tedavi Ekle");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void tedaviDuzenleDialog(ActionEvent event) {

        if (tedaviListesi.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Lütfen Tablodan Kayıt Seçiniz.");
            alert.showAndWait();
        }

        try {
            if (tedaviListesi.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tedaviDuzenle.fxml"));
                Parent parent = loader.load();
                TedaviDuzenleController tedaviDuzenle = loader.<TedaviDuzenleController>getController();
                Tedavi t = tedaviListesi.getSelectionModel().getSelectedItem();
                loader.setController(tedaviDuzenle);
                tedaviDuzenle.initData(t);
                Stage duzenleStage = new Stage();
                Scene scene = new Scene(parent);
                duzenleStage.setTitle("Tedavi Düzenle");
                tedaviDuzenle.setTedaviList(tList);

                duzenleStage.initModality(Modality.APPLICATION_MODAL);
                duzenleStage.setScene(scene);
                duzenleStage.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tedaviGoruntuleDialog(ActionEvent event) { 
        if (tedaviListesi.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Lütfen Tablodan Kayıt Seçiniz.");
            alert.showAndWait();
        }

        try {
            if (tedaviListesi.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tedaviGoruntule.fxml"));
                Parent parent = loader.load();
                TedaviGoruntuleController tedaviGoruntule = loader.<TedaviGoruntuleController>getController();
                Tedavi t = tedaviListesi.getSelectionModel().getSelectedItem();
                loader.setController(tedaviGoruntule);
                tedaviGoruntule.initData(t);
                Stage duzenleStage = new Stage();
                Scene scene = new Scene(parent);
                duzenleStage.setTitle("Tedavi Görüntüle");

                duzenleStage.initModality(Modality.APPLICATION_MODAL);
                duzenleStage.setScene(scene);
                duzenleStage.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
