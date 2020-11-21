package Colecciones;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Dispositivo;
import modelo.Repuesto;

public class ColeccionDispositivos {
    private ArrayList<Dispositivo> dispositivos;
    
    public ColeccionDispositivos() {
        this.dispositivos = new ArrayList();
    }
    
    // calcula el precio por dispositivo.
    private int calcularPrecio(Repuesto repuesto, boolean formateo, boolean limpieza) {
        int precio = 0;
            if (repuesto != null) {
                precio += repuesto.getPrecio();
            }
            
            if (formateo) {
                precio += 10000;
            }
            
            if (limpieza) {
                precio += 7500;
            }
            return precio;
    }
    
    private Dispositivo crearDispositivo(String modeloD, String marcaD, String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza) {
        String nomRepuesto = "";
            if (repuesto != null) {
                nomRepuesto = repuesto.getModelo();
            }
            
        return new Dispositivo(tipoD, marcaD, modeloD,  analisisD, nomRepuesto, calcularPrecio(repuesto, formateo, limpieza));
    }
    
    private Dispositivo crearDispositivo(String modeloD, String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza) {
        String nomRepuesto = "";
            if (repuesto != null) {
                nomRepuesto = repuesto.getModelo();
            }
            
            return new Dispositivo(tipoD, modeloD,  analisisD, nomRepuesto, calcularPrecio(repuesto, formateo, limpieza));
    }
    
    private Dispositivo crearDispositivo(String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza) {
        System.out.println("1");
        String nomRepuesto = "";
            if (repuesto != null) {
                nomRepuesto = repuesto.getModelo();
            }

            return new Dispositivo(tipoD,  analisisD, nomRepuesto, calcularPrecio(repuesto, formateo, limpieza) );
    }
    
    public void agregarDispositivo(String modeloD, String marcaD, String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza) {
        Dispositivo dispo = crearDispositivo(modeloD, marcaD, tipoD,  analisisD, repuesto, formateo, limpieza);
        this.dispositivos.add(dispo);
    }
    
    public void agregarDispositivo(String modeloD, String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza) {
        Dispositivo dispo = crearDispositivo(modeloD, tipoD,  analisisD, repuesto, formateo, limpieza);
        this.dispositivos.add(dispo);
    }
    
    public void agregarDispositivo(String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza) {
        Dispositivo dispo = crearDispositivo(tipoD,  analisisD, repuesto, formateo, limpieza);
        this.dispositivos.add(dispo);
    }
    
    public ObservableList<Dispositivo> getDispositivos() {
        return FXCollections.observableArrayList(dispositivos);
    }
    
    // elimina dispositivos
    public boolean eliminarDispositivo(Dispositivo dispo) {
        if (dispositivos.isEmpty())
            return false;
        for (Dispositivo dispositivo : dispositivos) {
            dispositivos.remove(dispo);
            return true;
        }
        return false;
    }
    

// modifica los dispositivos    
   public void modificarDispositivo(Dispositivo disp, String tipo, String marca, String modelo, String analisis) {
       for (int i = 0; i < dispositivos.size(); i++) { // recorre la lista de dispositivos
           if (dispositivos.get(i) == disp) { // valida que el dispositivo se el que se quiere modificar
               dispositivos.get(i).setTipoDispositivo(tipo);
               dispositivos.get(i).setMarca(marca);
               dispositivos.get(i).setModelo(modelo);
               dispositivos.get(i).setDiagnostico(analisis);
           }
       }
   }
   
   // calcula el precio total de los repuestos
   public int precioTotalRepuestos() {
       int sumaTotal = 0;
        for (Dispositivo dispositivo : dispositivos) {
            sumaTotal += dispositivo.getPrecioRepuesto();
        }
       return sumaTotal;
   }
   
   // obtiene la cantidad de dispositivos
   public int cantidadDispositivos() {
       return dispositivos.size();
   }
   
   // obtiene lista de dispositivos
   public ArrayList listaDispo() {
       if (!dispositivos.isEmpty()) {
           ArrayList<Dispositivo> copiaDispo = new ArrayList<>();
           for (Dispositivo copia : dispositivos) {
               copiaDispo.add(copia);
           }
           return copiaDispo;
       }
       return null;
   }
}
