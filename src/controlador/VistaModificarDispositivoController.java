package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Dispositivo;

public class VistaModificarDispositivoController implements Initializable {
    @FXML
    private ChoiceBox<String> tipoD;
    @FXML
    private TextArea analisisD;
    @FXML
    private TextField marcaD;
    @FXML
    private TextField modeloD;
    @FXML
    private Button btnModificar;
    
    private Dispositivo modDisp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<String> tipos = FXCollections.observableArrayList("Notebook", "Celular", "PC Escritorio", "Otros"); // inicializacion lista de tipos de dispositivos permitidos
          tipoD.setItems(tipos);
    }    
    
    @FXML
    private void modificar(ActionEvent event) {
        modDisp = new Dispositivo(tipoD.getValue(), marcaD.getText(), modeloD.getText(), analisisD.getText(), 0);
        
        Stage stage = (Stage) this.btnModificar.getScene().getWindow();
        stage.close();
    }
    
    public void recibe (Dispositivo dispo) {
        analisisD.setText(dispo.getDiagnostico());
        marcaD.setText(dispo.getMarca());
        modeloD.setText(dispo.getModelo());
    }
    
    public Dispositivo getDispositivo() {
        return modDisp;
    }
}
