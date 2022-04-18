
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Tedavi;


public class TedaviGoruntuleController implements Initializable {

    @FXML
    private Label tedaviID;
    @FXML
    private Label tedaviAdi;
    @FXML
    private Label tedaviAciklamasi;
   
    
    Tedavi secilenTedavi;
    
    public void initData(Tedavi tedavi){
        secilenTedavi = tedavi;
        tedaviID.setText(String.valueOf(secilenTedavi.getTedavi_id()));
        tedaviAdi.setText(secilenTedavi.getTedavi_adi());
        tedaviAciklamasi.setText(secilenTedavi.getTedaviAciklama());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

}
