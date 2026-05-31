import java.util.ArrayList;
import java.util.List;

public class Desarrollador extends Empleado{
    private String lenguajePrincipal;
    private int horasExtra;
    private List<Proyecto> proyectos;

    public Desarrollador(int id, String nombre, double salario, String lenguajePrincipal){
        super(id, nombre, "Desarrollador", salario);
        this.lenguajePrincipal = lenguajePrincipal;
        this.horasExtra = 0;
        this.proyectos = new ArrayList<>();

    }
    /* Getters y Setters */
    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }
    public void setLenguajePrincipal(String lenguajePrincipal) {
        this.lenguajePrincipal = lenguajePrincipal;
    }
    public int getHorasExtra() {
        return horasExtra;
    }
    // Registra horas extra
    public void setHorasExtra(int horas) {
        this.horasExtra += horas;
    }
   
    
    // Manejo de Proyectos
    public void asignarProyecto(Proyecto proy) throws ElementoDuplicadoException {
        for (Proyecto p : proyectos) {
            if (p.getId() == proy.getId()) {
                throw new ElementoDuplicadoException("El desarrollador ya está asignado a este proyecto.");
            }
        }
        proyectos.add(proy);
    }

    public void removerProyecto(int proyectoId) {
        proyectos.removeIf(p -> p.getId() == proyectoId);
    }
    

    @Override
    public double calcularSalario() {
        return super.calcularSalario() + (horasExtra * 15.0);
    }
}