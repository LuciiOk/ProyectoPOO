
package modelo;

public class Memoria extends Repuesto  {
    private int frecuencia;
    private String tipoMemoria;
    private int capacidad;

    public Memoria(int frecuencia, String tipoMemoria, int capacidad, int codigo , String modelo, String nombre, int stock, int precio) {
        super(codigo, modelo, nombre, stock, precio);
        this.frecuencia = frecuencia;
        this.tipoMemoria = tipoMemoria;
        this.capacidad = capacidad;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipoMemoria() {
        return tipoMemoria;
    }

    public void setTipoMemoria(String tipoMemoria) {
        this.tipoMemoria = tipoMemoria;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String especificar() {
        return "Codigo: " + this.getCodigo() + " Modelo: " + this.getModelo() + " Marca: " + this.getMarca() + " Stock " + this.getStock() + " Precio: " + this.getPrecio() + "\n Tipo Memoria : " + this.tipoMemoria + " Frecuencia: " + this.frecuencia + " Capacidad: " + this.capacidad ;
    }
}
