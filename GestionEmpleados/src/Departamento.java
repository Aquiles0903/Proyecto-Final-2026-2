import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Departamento implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombreDep;
    private String descripcion;
    private List<Empleado> empleados;
    // Atributo extra para poder asignarle un presupuesto.
    private double presupuesto;
    //Atributo extra para mejorar el manejo de jefes de departamento
    private JefeDepartamento jefe;
    
    

    // Constructor
    public Departamento(int id, String nombreDep, String descripcion, double pres){
        this.id = id;
        this.nombreDep = nombreDep;
        this.descripcion = descripcion;
        this.empleados = new ArrayList<>();
        this.presupuesto = 0.0;
        

    }
    //Gestion de Empleados, verifica que el objeto exista y que no este ya en la lista
    public void agregarEmpleado(Empleado emp) {
        if (emp != null && !empleados.contains(emp)) {
            empleados.add(emp);
        }
    }
    /* Getters y Setters */
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombreDep(){
        return nombreDep;
    }
    public void setNombreDep(String nombreDep){
        this.nombreDep = nombreDep;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public List<Empleado> getEmpleados(){
        return empleados;
    }
    public double getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
    public JefeDepartamento getJefe() {
        return jefe;
    }
    public void setJefe(JefeDepartamento jefe) {
        this.jefe = jefe;
    }
   
    
}

