import java.util.ArrayList;
import java.util.List;

public class Gerente extends Empleado{

    private double bonoAnual;
    private List<Departamento> deptoSupervisados;
    private double presupAsignado;
    //Atributo extra para mejorar el manejo del presupuesto
    private double presupUtilizado;
    //Atributo extra para manejar proyectos
    private List<Proyecto> proyectos;

    // Constructor
    public Gerente(int id, String nombre, String puesto, double salario, double bonoAnual, double presupAsignado) {
        super(id, nombre,"Gerente", salario);
        this.bonoAnual = 0.0;
        this.presupAsignado = presupAsignado;
        this.deptoSupervisados = new ArrayList<>();
    }
    
    public void agregarDepto(Departamento depto){
        this.deptoSupervisados.add(depto);
    }

    /* Getters y Setters */
    public double getBonoAnual(){
        return bonoAnual;
    }
    public void setBonoAnual(double bonoAnual){
        this.bonoAnual = bonoAnual;
    }
    public double getPresupAsignado(){
        return presupAsignado;
    }
    public void setPresupAsig(double presupAsignado){
        this.presupAsignado = presupAsignado;
    }
    public double getPresupUtilizado(){
        return presupUtilizado;
    }
    public void setPresupUtilizado(double presupUtilizado){
        this.presupUtilizado = presupUtilizado;
    }
    /* Métodos propios de Gerente */
    @Override
    public double calcularSalario() {
        return super.calcularSalario() + bonoAnual;
    }
    
    //Asigna el presupuesto a cada departamento verificando que no exceda el presupuesto asignado
    public void asignarPresupADepto(Departamento depto, double presupDepto) throws PresupuestoExcedidoException {
        if (presupUtilizado + presupDepto > presupAsignado) {
            throw new PresupuestoExcedidoException("No hay suficiente presupuesto disponible.");
        }
        presupUtilizado += presupDepto;
        depto.setPresupuesto(depto.getPresupuesto() + presupDepto);
    }
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Bono Anual: $" + String.format("%.2f", bonoAnual));
        System.out.println("Presupuesto Asignado: $" + String.format("%.2f", presupAsignado));
        System.out.println("Presupuesto Utilizado: $" + String.format("%.2f", presupUtilizado));
        System.out.println("Salario Total: $" + String.format("%.2f", calcularSalario()));
    }
    /* Manejo de proyectos */
    public void agregarProyecto(Proyecto proy) throws ElementoDuplicadoException {
        for (Proyecto p : proyectos) {
            if (p.getId() == proy.getId()) {
                throw new ElementoDuplicadoException("El proyecto ya está asignado a este gerente.");
            }
        }
        proyectos.add(proy);
    }
    public void asignarPresupuestoProyecto(Proyecto proy, double cantidad) throws PresupuestoExcedidoException {
        if (presupUtilizado + cantidad > presupAsignado) {
            throw new PresupuestoExcedidoException("No hay suficiente presupuesto disponible.");
        }
        presupUtilizado += cantidad;
        proy.setPresupuesto(proy.getPresupuesto() + cantidad);
    }

   
}