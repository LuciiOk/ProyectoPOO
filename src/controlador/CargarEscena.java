package controlador;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CargarEscena {
    private String url;
    private String titulo;
    private FXMLLoader loader;
    private Stage stage;
    private Scene escena;
    private Parent root;
    
    public CargarEscena(String url, String titulo) throws IOException {
        this.url = url;
        this.titulo = titulo;
        loader = new FXMLLoader(getClass().getResource(this.url));
        root = loader.load();
        escena = new Scene(root);
        stage = new Stage();
    }
    
    // carga la escena y cierra la ventana anterior recibe un stage.
    public void cargarEscena(Stage btn) throws IOException {
        stage.setScene(escena);
        stage.setTitle(titulo);
        stage.show();
        btn.close();
    }
    // carga y muestra una escena sin parametros
    public void cargarEscena( ) throws IOException {
        stage.setScene(escena);
        stage.setTitle(titulo);
        stage.showAndWait();
    }
    // obtiene el controlador de la vista
    public Object getController() {
        return loader.getController();
    }
}
