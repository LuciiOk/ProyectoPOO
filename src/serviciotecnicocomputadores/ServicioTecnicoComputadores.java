package serviciotecnicocomputadores;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.OrdenServicio;
import modelo.ServicioTecn;

public class ServicioTecnicoComputadores extends Application  {
    
    public static void main(String[] args)   {
        inicializar();
        launch(args);
    }
    
    // valores iniciales.
    public static void inicializar() {
        ServicioTecn miServ = ServicioTecn.getInstance();
      
        OrdenServicio nueva = new OrdenServicio(1, LocalDate.now(), null, "Luciano", "Portales", "2222-2", "999999", "dfsdf@gmail.com");
        nueva.agregarDispositivo("A20S", "SAMSUNG", "CELULAR", "PANTALLA ROTA", null, false, true);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
        miServ.agregarOrden(nueva);
         nueva = new OrdenServicio(2, LocalDate.now(), null, "Daniel", "Navarrete", "11111-1", "8888888", "bbbbbbbb@gmail.com");
        nueva.agregarDispositivo("A20S", "SAMSUNG", "CELULAR", "PANTALLA ROTA", null, false, true);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
         nueva.agregarDispositivo("PC-ESCRITORIO", "GEAR", "PC-ESCRITORIO", "VIRUS", null, true, false);
        miServ.agregarOrden(nueva);
         nueva = new OrdenServicio(3, LocalDate.now(), null, "Juan", "Perez", "33333-2", "777777", "aaaaaa@gmail.com");
        nueva.agregarDispositivo("A20S", "SAMSUNG", "CELULAR", "PANTALLA ROTA", null, false, true);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
        nueva.agregarDispositivo("S20", "SAMSUNG", "CELULAR", "PANTALLA ROTA", null, false, true);
        miServ.agregarOrden(nueva);
         nueva = new OrdenServicio(4, LocalDate.now(), null, "By", "Staxx", "44444-2", "232332", "xdxdxd@gmail.com");
        nueva.agregarDispositivo("A20S", "SAMSUNG", "CELULAR", "PANTALLA ROTA", null, false, true);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
        nueva.agregarDispositivo("G9", "MOTOROLA", "CELULAR", "BLOQUEADO", null, true, false);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
        miServ.agregarOrden(nueva);
         nueva = new OrdenServicio(5, LocalDate.now(), null, "Luciano", "Portales", "2222-2", "999999", "dfsdf@gmail.com");
        nueva.agregarDispositivo("A20S", "SAMSUNG", "CELULAR", "PANTALLA ROTA", null, false, true);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
        nueva.agregarDispositivo("A20S", "SAMSUNG", "CELULAR", "PANTALLA ROTA", null, false, true);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
        nueva.agregarDispositivo("G9", "MOTOROLA", "CELULAR", "BLOQUEADO", null, true, false);
        nueva.agregarDispositivo("ROG G703GXR-EV037T", "ASUS", "NOTEBOOK", "FORMATEO Y LIMPIEZA", null, true, true);
        miServ.agregarOrden(nueva);
    }
    
    // carga de la primera escena.
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/VistaMain.fxml")); //carga la vista fxml
        
        Scene scene = new Scene(root); // instancia la escena 
        primaryStage.setResizable(false);
        primaryStage.setScene(scene); // setea la escena
        primaryStage.setTitle("Menú principal Servicio tecnico");
        primaryStage.show(); //muestra la escena
        
    }
}
