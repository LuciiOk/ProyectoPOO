package modelo;

import Colecciones.ColeccionInventario;
import Colecciones.ColeccionOrdenes;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class ServicioTecn {
    
    private static ServicioTecn instancia;
    
    private String nombre;
    private String direccion;
    private int telefonoContaco;
    private String emailContacto;
    private ColeccionOrdenes ordenes;
    private ColeccionInventario inventario;

    private ServicioTecn(String nombre, String direccion, int telefonoContaco, String emailContacto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonoContaco = telefonoContaco;
        this.emailContacto = emailContacto;
        this.ordenes = new ColeccionOrdenes();
        this.inventario = new ColeccionInventario();
    }
    
    public static ServicioTecn getInstance() {
        if (instancia == null) {
            instancia = new ServicioTecn("ffdsf", "fdfdsf", 4343, "gffgfd");
        }
        return instancia;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {  
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefonoContaco() {
        return telefonoContaco;
    }

    public void setTelefonoContaco(int telefonoContaco) {
        this.telefonoContaco = telefonoContaco;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }
    
    public boolean agregarOrden( String nombre, String apellido, String rut, String num, String email) {
        return ordenes.agregarOrden(nombre, apellido, rut, num, email);
    }
    
    public boolean agregarOrden( OrdenServicio nuevo) {
        return ordenes.agregarOrden(nuevo);
    }
    
    public boolean agregarRepuesto(Repuesto nuevo) {
        return this.inventario.agregarRepuesto(nuevo);
    }
    
    public int cantidadOrdnes() {
        return this.ordenes.cantidadOrdenes();
    }

    public ObservableList<OrdenServicio> getOrdenes() { // obtener el arraylist en observablelist
        return ordenes.getOrdenes();
    }
    
    // se eliminan las ordenes
    public void eliminarOrdenServicio(int codigo) {
        ordenes.eliminarOrdenServicio(codigo);
    }
    
    public boolean eliminarProducto(int codigo) {
        return this.inventario.eliminarProducto(codigo);
    }
    
    public boolean eliminarDispo(int codigo, Dispositivo dispo) {
        return ordenes.eliminarDispo(codigo, dispo);
    }
    
    public boolean modificarOrden(int codigoOrdenMod, OrdenServicio modOrden) {
        return ordenes.modificarOrden(codigoOrdenMod, modOrden);
    }
    
    public void modificarDispo(int orden, Dispositivo modDisp, Dispositivo mod) {
         ordenes.modificarDispo(orden, modDisp, mod);
    }
    
    public ObservableList<Repuesto> getInventario() {
        return inventario.getObs();
    }
    
    public ArrayList<OrdenServicio> filtrar(LocalDate desde, LocalDate Hasta) {
        return ordenes.filtrar(desde, Hasta);
    }
    
    public ArrayList<OrdenServicio> filtrar(int desde, int hasta) {
        return ordenes.filtrar(desde, hasta);
    }
    
    public ArrayList listaOrdenes() {
        return ordenes.listaOrdenes();
    }
    
    public void generarReporteMensual(LocalDate desde, LocalDate Hasta) throws IOException {
        Reportes rep = new Reportes();
        rep.recReporte(ordenes, desde, Hasta);
    }
    
    public void describirProd() {
        this.inventario.describir();
    }

    public Cliente clienteMayorDispositivos() {
        return ordenes.getClienteMayorPrecio();
    }
    
    public String cantOrdYDisp(String rut) {
        return ordenes.cantidadOrdenesyDispo(rut);
    }
}

