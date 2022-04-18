package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

    String username = "admin";
    String pass = "123";

    @FXML
    private AnchorPane LoginPane;

    @FXML
    private TextField txtKullanici;

    @FXML
    private PasswordField txtSifre;

    @FXML
    private Button btnGiris;

    @FXML
    void girisYap(ActionEvent event) throws IOException {
        String kullaniciAdi = txtKullanici.getText();
        String sifre = txtSifre.getText();
        if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("OOPS!!! Bir Hata İle Karşıldı.");
            alert.setContentText("Alanlar Boş Geçilemez");
            alert.showAndWait();
        } else {
            if (kullaniciAdi.equals(username) && sifre.equals(pass)) {
                ((Node)event.getSource()).getScene().getWindow().hide();
                loadWindow("home.fxml", "Randevu Sistemi");
                    
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("OOPS!!! Bir Hata İle Karşıldı.");
                alert.setContentText("Kullanıcı Adı ve/veya Şifre Yanlış!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void loadWindow(String location,String title) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    

}
