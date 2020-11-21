package modelo;

public abstract class Repuesto {
    private int codigo;
    private String marca;
    private String modelo;
    private int stock;
    private int precio;

    public Repuesto(int codigo, String modelo, String nombre, int stock, int precio) {
        this.codigo = codigo;
        this.marca = modelo;
        this.modelo = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String nombre) {
        this.modelo = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public void aumentarStock(int stock) {
        this.stock += stock;
    }
    
    public abstract String especificar();

    @Override
    public String toString() {
        return  "Modelo: " + modelo + " stock: " + stock + " precio: " + precio ;
    }
    
}
