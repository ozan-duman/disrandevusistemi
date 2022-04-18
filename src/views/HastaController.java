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
import model.DosyaIslemleri;
import model.Hasta;

public class HastaController implements Initializable {

    public static ObservableList<Hasta> hList = FXCollections.observableArrayList();
    @FXML
    private TableView<Hasta> hastaListele;

    @FXML
    private TableColumn<Hasta, Integer> tID = new TableColumn<>();

    @FXML
    private TableColumn<Hasta, String> tAdıSoyadı = new TableColumn<>();

    @FXML
    private TableColumn<Hasta, String> tTelNo = new TableColumn<>();

    @FXML
    private TableColumn<Hasta, String> tEmail = new TableColumn<>();

    @FXML
    private Button hastaSil;

    public static ObservableList<Hasta> getHastaFromFile() {
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("hasta");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                Hasta h = new Hasta(Integer.parseInt(satir[0]), satir[1], satir[2], satir[3]);
                hList.add(h);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hList;
    }

    @FXML
    private void hastaEkleDialog(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("hastaEkle.fxml"));
        Parent parent = fXMLLoader.load();
        HastaEkleController hastaEkleController = fXMLLoader.<HastaEkleController>getController();
        hastaEkleController.setHastaList(hList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Hasta Ekle");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    private void hastaDuzenleDialog(ActionEvent event) {
        if (hastaListele.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Lütfen Tablodan Kayıt Seçiniz.");
            alert.showAndWait();
        }
        try {
            if (hastaListele.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hastaDuzenle.fxml"));
                Parent parent = loader.load();
                HastaDuzenleController hastaDuzenle = loader.<HastaDuzenleController>getController();
                Hasta h = hastaListele.getSelectionModel().getSelectedItem();
                loader.setController(hastaDuzenle);
                hastaDuzenle.initData(h);
                Stage duzenleStage = new Stage();
                Scene scene = new Scene(parent);
                duzenleStage.setTitle("Hasta Düzenle");
                hastaDuzenle.setHastaList(hList);

                duzenleStage.initModality(Modality.APPLICATION_MODAL);
                duzenleStage.setScene(scene);
                duzenleStage.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hList.removeAll(hList);
        tID.setCellValueFactory(new PropertyValueFactory<>("hasta_id"));
        tAdıSoyadı.setCellValueFactory(new PropertyValueFactory<>("hasta_adSoyad"));
        tTelNo.setCellValueFactory(new PropertyValueFactory<>("hasta_telefonNo"));
        tEmail.setCellValueFactory(new PropertyValueFactory<>("hasta_email"));

        hastaListele.setItems(getHastaFromFile());
        hastaListele.setItems(hList);

        hastaSil.setOnAction(e -> {
            Hasta seciliDoktor = hastaListele.getSelectionModel().getSelectedItem();
            hastaListele.getItems().remove(seciliDoktor);
            hList.remove(seciliDoktor);
            DosyaIslemleri.dosyayaYaz(hList, "hasta");
        });

    }
}
