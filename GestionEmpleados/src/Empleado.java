import java.io.Serializable;

public class Empleado implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String puesto;
    private double salario;
    

    //Constructor
    public Empleado(int id, String nombre, String puesto, double salario){
    
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;

    }
    /* Getters y Setters */
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getPuesto(){
        return puesto;
    }
    public void setPuesto(String puesto){
        this.puesto = puesto;
    }
    public double getSalario(){
        return salario;
    }
    public void setSalario(Double salario){
        this.salario = salario;
    }
    // Salario base
    public double calcularSalario() {
        return salario;
    }
    // Informacion del empleado
    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Puesto: " + puesto);
        System.out.println("Salario : $" + String.format("%.2f", calcularSalario()));
    }
}





