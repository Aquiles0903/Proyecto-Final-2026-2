import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Proyecto implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombreProyecto;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private List<Empleado> empleadosAsoc;
    //Atributo extra para asignarle un presupuesto
    private double presupuesto;

    // Constructor
    public Proyecto(int id, String nombreProyecto, String descripcion, LocalDate fechaInicio, LocalDate fechaFinal){
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.empleadosAsoc = new ArrayList<>();
        this.presupuesto = 0.0;
    }

    //Agrega Empleados, verifica que el objeto exista y que no este ya en la lista
    public void agregarEmpleado(Empleado emp) {
        if (emp != null && !empleadosAsoc.contains(emp)) {
            empleadosAsoc.add(emp);
        }
    }
    // Método para remover empleados del proyecto
    public void removerEmpleado(int empleadoId) {
        empleadosAsoc.removeIf(e -> e.getId() == empleadoId);
    }
    
    
    /* Getters y Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getPresupuesto(){
        return presupuesto;
    }
    public void setPresupuesto(double presupuesto){
        this.presupuesto = presupuesto;
    }
    public List<Empleado> getEmpleados(){
        return new ArrayList<>(empleadosAsoc);
    }
    
}