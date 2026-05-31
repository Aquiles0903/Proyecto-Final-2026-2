import java.util.ArrayList;
import java.util.List;

public class JefeDepartamento extends Empleado{

    private Departamento depto;
    private List<Empleado> equipo;
    private double bonoProductividad;

    /**
     * @param id
     * @param nombre
     * @param salario
     */
    public JefeDepartamento(int id, String nombre, double salario, double bonoProductividad){
        super(id, nombre, "Jefe de departamento", salario);
        this.bonoProductividad = bonoProductividad;
        this.equipo = new ArrayList<>();
    }

    public double getBonoProductividad() { 
        return bonoProductividad; 
    }
    public void setBonoProductividad(double bonoProductividad) {
         this.bonoProductividad = bonoProductividad; 
    }
    
    public Departamento getDepartamento() { 
        return depto; 
    }
    public void setDepartamento(Departamento depto) {
         this.depto = depto; 
    }

    
    /* Métodos propios del Jefe de departamento */

    //Agrega empleados al equipo
    public void agregarEmpleadoEquipo(Empleado emp) throws ElementoDuplicadoException {
        for (Empleado e : equipo) {
            if (e.getId() == emp.getId()) {
                throw new ElementoDuplicadoException("El empleado ya está en el equipo de este jefe.");
            }
        }
        equipo.add(emp);
    }
    // Remueve empleados del equipo
    public void removerEmpleadoEquipo(int empleadoId) {
        equipo.removeIf(e -> e.getId() == empleadoId);
    }
    // Remueve emppleados del departamento
    public void removerEmpleadoDeDepartamento(int empleadoId) {
        if (depto != null) {
            depto.getEmpleados().removeIf(e -> e.getId() == empleadoId);
        }
    }
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Bono de Productividad: $" + String.format("%.2f", bonoProductividad));
        System.out.println("Salario Total: $" + String.format("%.2f", calcularSalario()));
        if (depto != null) {
            System.out.println("Departamento a cargo: " + depto.getNombreDep());
        }
    }


    
}