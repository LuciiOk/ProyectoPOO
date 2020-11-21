package modelo;


public class DiscoDuro  extends Repuesto {
    private int capacidad;
    private int rpm;
    private int tamaño;

    public DiscoDuro(int capacidad, int rpm, int tamaño, int codigo, String modelo, String nombre, int stock, int precio) {
        super(codigo, modelo, nombre, stock, precio);
        this.capacidad = capacidad;
        this.rpm = rpm;
        this.tamaño = tamaño;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    @Override
    public String especificar() {
        return "Codigo: " + this.getCodigo() + " Modelo: " + this.getModelo() + " Marca: " + this.getMarca() + " Stock " + this.getStock() + " Precio: " + this.getPrecio() + "\n Capacidad: " + this.capacidad + " velocidad: " + this.rpm + " Tamaño:" + this.tamaño;
    }
    
}
