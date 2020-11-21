/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colecciones;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Repuesto;

/**
 *
 * @author lport
 */
public class ColeccionInventario {
    private ArrayList<Repuesto> inventario;
    private HashMap<Integer,Repuesto> inventarioCodigo;
    
    public ColeccionInventario() {
        this.inventario = new ArrayList();
        this.inventarioCodigo = new HashMap();
    }
    // agrega un nuevo repuesto. verifica en el hashmap y lo agrega al hashmap y arraylist
    public boolean agregarRepuesto(Repuesto nuevo) {
        if (this.inventarioCodigo.containsKey(nuevo.getCodigo()))
                return false;
        this.inventario.add(nuevo);
        this.inventarioCodigo.put(nuevo.getCodigo(), nuevo);
        return true;
    }
    // verifica si un producto ya existe.
    public boolean verificarProductoInventario(int codigo) {
        return this.inventarioCodigo.containsKey(codigo);
    }
    // busca producto
    private int buscarProductoInventario(int codigo) {
        if (this.inventarioCodigo.containsKey(codigo))
            for (int i = 0; i < this.inventario.size(); i++)
                if (inventario.get(i).getCodigo() == codigo)
                    return i;
        return -1;
    }
    // elimina producto de hashmap y arraylist
    public boolean eliminarProducto(int codigo) {
        if (inventarioCodigo.containsKey(codigo)) {
            int indice = buscarProductoInventario(codigo);
            inventario.remove(indice);
            inventarioCodigo.remove(codigo);
            return true;
        }
        return false;
    }
    // retorna observablelist de productos
    public ObservableList<Repuesto> getObs() {
        return FXCollections.observableArrayList(this.inventario);
    }
    // describe los productos
    public void describir() {
        for (Repuesto prod : inventario) {
            System.out.println("" + prod.especificar());
        }
    }
    
}

