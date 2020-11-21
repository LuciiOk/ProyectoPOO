package modelo;

import Colecciones.ColeccionDispositivos;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class OrdenServicio implements Reportable {
    private int codigo;
    private LocalDate fechaRecepcion;
    private LocalDate fechaEntrega;
    private Cliente cliente;
    private ColeccionDispositivos dispositivos;
    private int precioTotal;

    public OrdenServicio(int codigo, LocalDate fechaRecepcion, LocalDate fechaEntrega, String nombre, String apellido, String rut, String num, String email) {
        this.codigo = codigo;
        this.fechaRecepcion = fechaRecepcion;
        this.cliente = new Cliente(rut, nombre, apellido, email, num);
        this.fechaEntrega = fechaEntrega;
        dispositivos = new ColeccionDispositivos();
        this.precioTotal = 0 ;
    }  
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void agregarDispositivo(String modeloD, String marcaD, String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza)  {
        this.dispositivos.agregarDispositivo(modeloD, marcaD, tipoD,  analisisD, repuesto, formateo, limpieza);
    }
    
    public void agregarDispositivo(String modeloD, String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza)  {
        this.dispositivos.agregarDispositivo(modeloD, tipoD,  analisisD, repuesto, formateo, limpieza);
    }
    
    public void agregarDispositivo(String tipoD, String analisisD, Repuesto repuesto, boolean formateo, boolean limpieza)  {
        this.dispositivos.agregarDispositivo(tipoD,  analisisD, repuesto, formateo, limpieza);
    }

    public ObservableList<Dispositivo> getDispositivos() {
        return this.dispositivos.getDispositivos();
    }
    
    public boolean eliminarDispositivo(Dispositivo dispo) {
        return this.dispositivos.eliminarDispositivo(dispo);
    }
    
   public void modificarDispositivo(Dispositivo disp, String tipo, String marca, String modelo, String analisis) {
       this.dispositivos.modificarDispositivo(disp, tipo, marca, modelo, analisis);
   }
   
   public void totalPrecio() {
       this.precioTotal = dispositivos.precioTotalRepuestos();
   }
   
   public int cantidadDispo() {
       return dispositivos.cantidadDispositivos();
   }
   
   public ArrayList listaDispo() {
       return dispositivos.listaDispo();
   }
   
    @Override
   public String descripcionMensual() {
       return "Codigo: " + codigo + " fecha recepcion: " + this.fechaRecepcion + " fecha estimada entrega: " + this.fechaEntrega;
   }
   
}
