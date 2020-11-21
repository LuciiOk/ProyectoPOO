package modelo;

public class Dispositivo implements Reportable{
    private String tipoDispositivo;
    private String marca; // la marca puede ser opcional
    private String modelo; // el modelo puede ser opcional en el caso de que no se sepa
    private String diagnostico;
    private String modeloRepuesto;
    private int precioRepuesto;
    
    public Dispositivo(String tipoDispositivo, String diagnostico, String modeloRepuesto, int precioRepuesto) { // en el caso que se desconzca la marca y modelo del dispositivo a reparar
        this.tipoDispositivo = tipoDispositivo;
        this.marca = "NO ESPECIFICA";
        this.modelo = "NO ESPECIFICA";
        this.diagnostico = diagnostico;
        this.modeloRepuesto = modeloRepuesto;
        this.precioRepuesto = precioRepuesto;
    }

    public Dispositivo(String tipoDispositivo, String marca, String modelo, String diagnostico, String modeloRepuesto, int precioRepuesto) {
        this.tipoDispositivo = tipoDispositivo;
        this.marca = marca;
        this.modelo = modelo;
        this.diagnostico = diagnostico;
        this.modeloRepuesto = modeloRepuesto;
        this.precioRepuesto = precioRepuesto;
    }

    public Dispositivo(String tipoDispositivo, String modelo, String diagnostico, String modeloRepuesto, int precioRepuesto) { // en el caso de que se desconozca la marca del sispositivos a reparar
        this.tipoDispositivo = tipoDispositivo;
        this.marca = "NO ESPECIFICA";
        this.modelo = modelo;
        this.diagnostico = diagnostico;
        this.modeloRepuesto = modeloRepuesto;
        this.precioRepuesto = precioRepuesto;
    }
    
    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }  

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getModeloRepuesto() {
        return modeloRepuesto;
    }

    public void setModeloRepuesto(String modeloRepuesto) {
        this.modeloRepuesto = modeloRepuesto;
    }

    public int getPrecioRepuesto() {
        return precioRepuesto;
    }

    public void setPrecioRepuesto(int precioRepuesto) {
        this.precioRepuesto = precioRepuesto;
    }
    
    @Override
    public String descripcionMensual() {
        return "Tipo: " + this.tipoDispositivo + " modelo: " + this.modelo + " marca: " +  this.marca + "\n Analisis: " + this.diagnostico + "\n Repuesto: " + this.modeloRepuesto  + " Precio Repuesto: " + this.precioRepuesto ;
    }
    
}
