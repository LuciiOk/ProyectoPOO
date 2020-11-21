package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.ServicioTecn;


public class MostrarClienteController implements Initializable {

    @FXML
    private Button volver;
    @FXML
    private Label labelCliente;
    @FXML
    private Label labelCantOrdDisp;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicioTecn miser = ServicioTecn.getInstance();
        
        Cliente cliente = miser.clienteMayorDispositivos();
        
        labelCliente.setText(cliente.toString());
        labelCliente.autosize();
        labelCantOrdDisp.setText(miser.cantOrdYDisp(cliente.getRut()));
        labelCantOrdDisp.autosize();
    }    

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) this.volver.getScene().getWindow();
        stage.close();
    }
}
