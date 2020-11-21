
package controlador;

import javafx.scene.control.Alert;

public class Alerta {
    public void mostrarAlerta(String msg, Alert.AlertType tipo) {
            Alert alert = new Alert(tipo);
            alert.setTitle("Alerta");
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
    }
}
