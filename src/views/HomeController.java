package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class HomeController implements Initializable{
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button randevuBtn;
    @FXML
    private Button hastaBtn;
    @FXML
    private Button doktorBtn;
    @FXML
    private Button tedaviBtn;
    @FXML
    private AnchorPane centerAnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        try {
            borderPane.setCenter(centerAnchorPane);
            loadPage("randevu");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }  

    @FXML
    private void randevu(MouseEvent event) throws IOException {
        borderPane.setCenter(centerAnchorPane);
        loadPage("randevu");
    }

    @FXML
    private void hasta(MouseEvent event) throws IOException {
        borderPane.setCenter(centerAnchorPane);
        loadPage("hasta");
    }

    @FXML
    private void doktor(MouseEvent event) throws IOException {
         borderPane.setCenter(centerAnchorPane);
         loadPage("doktor");
    }

    @FXML
    private void tedavi(MouseEvent event) throws IOException {
        borderPane.setCenter(centerAnchorPane);
         loadPage("tedavi");
    }
    
    private void loadPage(String page) throws IOException{
        Parent root = null;
        
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        
        borderPane.setCenter(root);
        
                
        
    }
}
