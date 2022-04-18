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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cerrah;
import model.Doktor;
import model.DosyaIslemleri;
import model.Ortodonti;

public class DoktorController implements Initializable {

    static ObservableList<Doktor> dList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane doktorPanel;

    @FXML
    private Button doktorSil;

    @FXML
    private TableView<Doktor> doktorListele;

    @FXML
    private TableColumn<Doktor, Integer> tID = new TableColumn<>();

    @FXML
    private TableColumn<Doktor, String> tAdSoyad = new TableColumn<>();

    @FXML
    private TableColumn<Doktor, String> tPhone = new TableColumn<>();

    @FXML
    private TableColumn<Doktor, String> tCinsiyet = new TableColumn<>();

    @FXML
    private TableColumn<Doktor, String> tUnvan = new TableColumn<>();

    private ObservableList<Doktor> getDoktorFromFile() {
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("doktor");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                if (satir[4].equals("Cerrah")) {
                    Cerrah c = new Cerrah(Integer.parseInt(satir[0]), satir[1], satir[2], satir[3], satir[4]);
                    dList.add(c);
                } else if (satir[4].equals("Ortodonti")) {
                    Ortodonti o = new Ortodonti(Integer.parseInt(satir[0]), satir[1], satir[2], satir[3], satir[4]);
                    dList.add(o);
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dList;
    }

    @FXML
    void doktorEkleDialog(ActionEvent event) throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("doktorEkle.fxml"));
        Parent parent = fXMLLoader.load();
        DoktorEkleController doktorEkleController = fXMLLoader.<DoktorEkleController>getController();
        doktorEkleController.setDoktorList(dList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Doktor Ekle");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    void doktorDuzenleDialog(ActionEvent event) throws IOException {
        if (doktorListele.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Lütfen Tablodan Kayıt Seçiniz.");
            alert.showAndWait();
        }

        try {
            if (doktorListele.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("doktorDuzenle.fxml"));
                Parent parent = loader.load();
                DoktorDuzenleController doktorDuzenle = loader.<DoktorDuzenleController>getController();
                Doktor d = doktorListele.getSelectionModel().getSelectedItem();
                loader.setController(doktorDuzenle);
                doktorDuzenle.initData(d);
                Stage duzenleStage = new Stage();
                Scene scene = new Scene(parent);
                duzenleStage.setTitle("Doktor Düzenle");
                doktorDuzenle.setDoktorList(dList);

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
        dList.removeAll(dList);
        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tAdSoyad.setCellValueFactory(new PropertyValueFactory<>("isimSoyisim"));
        tPhone.setCellValueFactory(new PropertyValueFactory<>("telefonNo"));
        tCinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));
        tUnvan.setCellValueFactory(new PropertyValueFactory<>("unvan"));
        doktorListele.setItems(getDoktorFromFile());
        doktorListele.setItems(dList);

        doktorSil.setOnAction(e -> {
            Doktor seciliDoktor = doktorListele.getSelectionModel().getSelectedItem();
            doktorListele.getItems().remove(seciliDoktor);
            dList.remove(seciliDoktor);
            DosyaIslemleri.dosyayaYaz(dList, "doktor");
        });
    }

}
