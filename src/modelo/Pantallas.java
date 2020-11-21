
package modelo;


public class Pantallas extends Repuesto {
    private int pulgadas;
    private String resolucion;
    private String panel;

    public Pantallas(int pulgadas, String resolucion, String panel, int codigo , String modelo, String nombre, int stock, int precio) {
       super(codigo, modelo, nombre, stock, precio);
        this.pulgadas = pulgadas;
        this.resolucion = resolucion;
        this.panel = panel;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }
    
    @Override
    public String especificar() {
        return "Codigo: " + this.getCodigo() + " Modelo: " + this.getModelo() + " Marca: " + this.getMarca() + " Stock " + this.getStock() + " Precio: " + this.getPrecio() + "\nPulgadas: " + this.pulgadas + " Resolucion: " + this.resolucion + " Tipo Panel: " + this.panel;
    }
}
