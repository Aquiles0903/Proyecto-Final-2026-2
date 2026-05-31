import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Nomina {
    private List<Empleado> empleados;
    private LocalDate periodoPago;
    

public Nomina(List<Empleado> empleados, LocalDate periodo){
    this.empleados = new ArrayList<>(empleados);
    this.periodoPago = periodoPago;
    
}
// Manejo de Empleados
public void agregarEmpleado(Empleado emp){
    if(emp != null){
        this.empleados.add(emp);
    }
}
/* Getters y Setters */

public List<Empleado> getEmpleados() {
    return empleados;
}
public void setEmpleados(List<Empleado> empleados) {
    this.empleados = empleados;
}
public LocalDate getPeriodoPago() {
    return periodoPago;
}
public void setPeriodoPago(LocalDate periodoPago) {
    this.periodoPago = periodoPago;
}



// Calculamos Nomina Total
public double getNominaTotal() {
    double nominaTotal = 0.0;
    for (Empleado emp : this.empleados) {
        nominaTotal += emp.getSalario(); 
    }
    return nominaTotal;
}
public void mostrarNomina() {
    System.out.println("\n--- Nómina: " + periodoPago + " ---");
    for (Empleado e : empleados) {
        System.out.println("- " + e.getNombre() + " (" + e.getPuesto() + "): $" 
            + String.format("%.2f", e.calcularSalario()));
    }
    System.out.println("---------------------------------");
    System.out.println("Nómina Total: $" + String.format("%.2f", getNominaTotal()));
}

}