package Colecciones;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Cliente;
import modelo.Dispositivo;
import modelo.OrdenServicio;

public class ColeccionOrdenes {
    private ArrayList<OrdenServicio> ordenes;
    private HashMap<Integer, OrdenServicio> ordenesCodigo;
    
    public ColeccionOrdenes() {
        ordenes = new ArrayList();
        ordenesCodigo = new HashMap();
    }
    
    public boolean agregarOrden(OrdenServicio nuevaOrden) {
        if (ordenesCodigo.containsKey(nuevaOrden.getCodigo())) {
            return false;
        }
        nuevaOrden.totalPrecio();
        ordenesCodigo.put(nuevaOrden.getCodigo(), nuevaOrden);
        this.ordenes.add(nuevaOrden);
        return true;
    }
    
    public boolean agregarOrden(String nombre, String apellido, String rut, String num, String email) {
        OrdenServicio nuevaOrden = new OrdenServicio(ordenes.size() + 1, LocalDate.now(), LocalDate.now(), nombre, apellido, rut, num, email);
        
        if (ordenesCodigo.containsKey(nuevaOrden.getCodigo())) {
            return false;
        }
        ordenesCodigo.put(nuevaOrden.getCodigo(), nuevaOrden);
        this.ordenes.add(nuevaOrden);

        return true;
    }
    
    public int cantidadOrdenes() {
        return ordenes.size();
    }
    
    private int buscarIndiceOrden(int codigo) {
        for (int i = 0; i < ordenes.size(); i++)
            if (ordenes.get(i).getCodigo() == codigo)
                return i;
        return -1;
    }
    
    // elimina orden de servicio. Recibe el codigo de la orden.
    public boolean eliminarOrdenServicio(int codigo) {
        if (ordenesCodigo.containsKey(codigo)) {
            ordenesCodigo.remove(codigo);
            int indice = buscarIndiceOrden(codigo);
            ordenes.remove(indice);
            return true;
        }
        return false;
    }
    
    // elimina el dispositivo de una orden. recibe el codigo de la orden y el dispositivo a eliminar.
    public boolean eliminarDispo(int codigo, Dispositivo dispo) {
        if (ordenesCodigo.containsKey(codigo)) {
            ordenesCodigo.get(codigo).eliminarDispositivo(dispo);
            int indice = buscarIndiceOrden(codigo);
            ordenes.get(indice).eliminarDispositivo(dispo);
            return true;
        }
        return false;
    }
    
    public ObservableList<OrdenServicio> getOrdenes() { // obtener el arraylist en observablelist
        return FXCollections.observableArrayList(ordenes);
    }
    
    // funcion modificar orden recibe el codigo de la orden y la orden modificada.
    public boolean modificarOrden(int codigoOrdenMod, OrdenServicio modOrden) {
        if (ordenesCodigo.containsKey(codigoOrdenMod)) {
            int indice = buscarIndiceOrden(codigoOrdenMod); // se busca el indice para cambiar los valores por el indice del arraylist.
            // cambio de los valores de la orden en el arraylist
            ordenes.get(indice).setCodigo(modOrden.getCodigo());
            ordenes.get(indice).setFechaRecepcion(modOrden.getFechaRecepcion());
            ordenes.get(indice).setFechaEntrega(modOrden.getFechaEntrega());
            ordenes.get(indice).setCliente(modOrden.getCliente());
            
            // se cambian los valores de la orden en el hashmap
            ordenesCodigo.get(codigoOrdenMod).setCodigo(modOrden.getCodigo());
            ordenesCodigo.get(codigoOrdenMod).setFechaRecepcion(modOrden.getFechaRecepcion());
            ordenesCodigo.get(codigoOrdenMod).setFechaEntrega(modOrden.getFechaEntrega());
            ordenesCodigo.get(codigoOrdenMod).setCliente(modOrden.getCliente());
            return true;
        }
        return false;
    }
    
    // funcion modificar dispositivos entrega la id de orden a modificar, el dispositivoque se quiere modificar y el dispositivo modificado.
    public boolean modificarDispo(int orden, Dispositivo modDisp, Dispositivo mod) {
        if (ordenesCodigo.containsKey(orden)) { // verifica q esté la orden q se está buscando
            int indice = buscarIndiceOrden(orden); // obtiene el indice del la orden a buscar
            
            ordenes.get(indice).modificarDispositivo(mod, modDisp.getTipoDispositivo(), modDisp.getMarca(), modDisp.getModelo(), modDisp.getDiagnostico()); // modifica dispositivos del arrayList
            
            ordenesCodigo.get(orden).modificarDispositivo(mod, modDisp.getTipoDispositivo(), modDisp.getMarca(), modDisp.getModelo(), modDisp.getDiagnostico()); // modifica dispositivos del hashmap
            return true;
        }
        return false;
    }
    // filtra las ordenes  de servicio respecto una fecha determinanda (sobrecarga de metodos)
    public ArrayList<OrdenServicio> filtrar(LocalDate desde, LocalDate hasta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        desde.format(formatter);
        hasta.format(formatter);
        
        ArrayList<OrdenServicio> ordenesFiltradas = new ArrayList();
        
        for (OrdenServicio orden : ordenes) {
            if ((orden.getFechaRecepcion().isAfter(desde) || orden.getFechaRecepcion().isEqual(desde)) && (orden.getFechaRecepcion().isBefore(hasta) || orden.getFechaRecepcion().isEqual(hasta))) {
                ordenesFiltradas.add(orden);
            } 
        }
        
        return ordenesFiltradas;
    }
    
    // filtra las ordenes  de servicio respecto a la cantidad de dispositivos (sobrecarga de metodos)
    public ArrayList<OrdenServicio> filtrar(int desde, int hasta) {
        
        ArrayList<OrdenServicio> ordenesFiltradas = new ArrayList();
        
        for (OrdenServicio orden : ordenes) {
            if(orden.cantidadDispo() >= desde && orden.cantidadDispo() <= hasta ) {
                ordenesFiltradas.add(orden);
            } 
    }
        
        return ordenesFiltradas;
    }
    
    // retorna una copia de las ordenes de servicio.
    public ArrayList<OrdenServicio> listaOrdenes() {
        if (!ordenes.isEmpty()) {
            ArrayList<OrdenServicio> copiaOrdenes = new ArrayList<>();
            for (OrdenServicio copia : ordenes) {
                copiaOrdenes.add(copia);
            }
            return copiaOrdenes;
        }
        return null;
    }
    
    // obtiene la cantidad de ordenes registradas con su rut y dispositivos.
    public String cantidadOrdenesyDispo(String rut) {
        int cantOrd = 0, dispositivos = 0;
        for (OrdenServicio orden : ordenes) {
            if (rut.equals(orden.getCliente().getRut())) {
                cantOrd++;
                dispositivos += orden.cantidadDispo();
            }
        }
        return "Cantidad Ordenes: " +  cantOrd + " Cantidad Dispositivos: " + dispositivos;
    }
    
    public int cantidadDispoC(String rut) {
        int suma = 0;
        for (OrdenServicio orden : ordenes) {
            if (rut.equals(orden.getCliente().getRut())) {
                suma += orden.cantidadDispo();
            }
        }
        return suma;
    }
    
    
    // Buesca en todas las ordenes al cliente con mayor cantidad de dispositivos en sus ordenes.
   public Cliente getClienteMayorPrecio() {
       Cliente cliente = null;
       int aux = 0;
       for (OrdenServicio orden : ordenes) {
            if (cantidadDispoC(orden.getCliente().getRut()) > aux ) {
                    aux = cantidadDispoC(orden.getCliente().getRut()) ;
                    cliente = orden.getCliente();
            }
       }
       return cliente;
   }
}
