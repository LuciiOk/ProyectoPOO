package controlador;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CargarEscena {
    String url;
    String titulo;
    FXMLLoader loader;
    Stage stage;
    Scene escena;
    Parent root;
    
    public CargarEscena(String url, String titulo) throws IOException {
        this.url = url;
        this.titulo = titulo;
        loader = new FXMLLoader(getClass().getResource(url));
        root = loader.load();
        escena = new Scene(root);
        stage = new Stage();
    }
    
    public CargarEscena() {
        
    }
    
    public void cargarEscena(String urlEscena, String titulo, Stage btn) throws IOException {
        loader = new FXMLLoader(getClass().getResource(urlEscena));
        Parent addOrderRoot = loader.load();
        Scene addOrderScene = new Scene(addOrderRoot);

        Stage addOrderStage = new Stage(); 
        addOrderStage.setResizable(false);
        addOrderStage.setTitle(titulo);
    
        addOrderStage.setScene(addOrderScene);
        addOrderStage.show();
        
        btn.close();
    }
    
    public void cargarEscena(Stage btn) throws IOException {
        stage.setScene(escena);
        stage.setTitle(titulo);
        stage.show();
        btn.close();
    }
    
    public void cargarEscena( ) throws IOException {
        stage.setScene(escena);
        stage.setTitle(titulo);
        stage.showAndWait();
    }
    
    public Object getController() {
        return loader.getController();
    }
}
