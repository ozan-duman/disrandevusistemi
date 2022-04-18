package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DosyaIslemleri;
import model.Randevu;

public class RandevuController implements Initializable {

    @FXML
    private TableView<Randevu> randevuListele;
    @FXML
    private TableColumn<Randevu, Integer> tblClID;
    @FXML
    private TableColumn<Randevu, String> tblClDoktorAdi;
    @FXML
    private TableColumn<Randevu, String> tblClHastaAdi;
    @FXML
    private TableColumn<Randevu, String> tblClTedavi;
    @FXML
    private TableColumn<Randevu, String> tblClTarihi;
    @FXML
    private TableColumn<Randevu, String> tblClSaati;
    @FXML
    private Button randevuSil;

    private ObservableList rList = FXCollections.observableArrayList();
    @FXML
    private TextField txtArama;

    private final static String dbFileName = "randevu";

    public ObservableList<Randevu> getRandevuFromFile() {
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir(dbFileName);
            String line;
            String[] s;
            while ((line = br.readLine()) != null) {
                s = line.split("\t");
                Randevu r = new Randevu(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5]);
                rList.add(r);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rList.removeAll(rList);
        tblClID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClDoktorAdi.setCellValueFactory(new PropertyValueFactory<>("doktor"));
        tblClHastaAdi.setCellValueFactory(new PropertyValueFactory<>("hasta"));
        tblClTedavi.setCellValueFactory(new PropertyValueFactory<>("tedavi"));
        tblClTarihi.setCellValueFactory(new PropertyValueFactory<>("randevuTarihi"));
        tblClSaati.setCellValueFactory(new PropertyValueFactory<>("saat"));
        randevuListele.setItems(getRandevuFromFile());
        randevuListele.setItems(rList);

        //Arama
        FilteredList<Randevu> filteredRandevu = new FilteredList<>(rList, b -> true);

        txtArama.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredRandevu.setPredicate(randevu -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (randevu.getDoktor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (randevu.getHasta().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (randevu.getTedavi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
            SortedList<Randevu> sortedRandevu = new SortedList<>(filteredRandevu);
            sortedRandevu.comparatorProperty().bind(randevuListele.comparatorProperty());
            randevuListele.setItems(sortedRandevu);
        }));

        randevuSil.setOnAction(e -> {
            Randevu seciliRandevu = randevuListele.getSelectionModel().getSelectedItem();
            randevuListele.getItems().remove(seciliRandevu);
            rList.remove(seciliRandevu);
            DosyaIslemleri.dosyayaYaz(rList, "randevu");
        });
    }

    @FXML
    private void randevuEkleDialog(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("randevuEkle.fxml"));
        Parent parent = fXMLLoader.load();
        RandevuEkleController randevuEkle = fXMLLoader.<RandevuEkleController>getController();
        randevuEkle.setRandevuList(rList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void randevuDuzenleDialog(ActionEvent event) {
        if (randevuListele.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("OOPS!!! Bir Hata İle Karşıldı.");
            alert.setContentText("Lütfen Tablodan Kayıt Seçiniz.");
            alert.showAndWait();
        }
        try {
            if (randevuListele.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("randevuDuzenle.fxml"));
                Parent parent = loader.load();
                RandevuDuzenleController randevuDuzenle = loader.<RandevuDuzenleController>getController();
                Randevu r = randevuListele.getSelectionModel().getSelectedItem();
                randevuDuzenle.initData(r);
                loader.setController(randevuDuzenle);
                Stage duzenleStage = new Stage();
                Scene scene = new Scene(parent);
                duzenleStage.setTitle("Randevu Düzenle");
                randevuDuzenle.setRandevuList(rList);
                duzenleStage.initModality(Modality.APPLICATION_MODAL);
                duzenleStage.setScene(scene);
                duzenleStage.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
