import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestionDatos implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Empleado> empleados;
    private List<Proyecto> proyectos;
    private List<Departamento> departamentos;

    private int maxIdEmpleado;
    private int maxIdProyecto;
    private int maxIdDepartamento;

    public GestionDatos() {
        empleados = new ArrayList<>();
        proyectos = new ArrayList<>();
        departamentos = new ArrayList<>();

        maxIdEmpleado = 0;
        maxIdProyecto = 0;
        maxIdDepartamento = 0;
    }
    /* Se generan los ID */
    public int generarIdEmpleado() {
        return ++maxIdEmpleado;
    }
    public int generarIdProyecto() {
        return ++maxIdProyecto;
    }
    public int generarIdDepartamento() {
        return ++maxIdDepartamento;
    }

    /* Manejo de empleados */
    public void agregarEmpleado(Empleado emp) {
        empleados.add(emp);
    }
    

    public Empleado buscarEmpleado(int id) throws ElementoNoEncontradoException {
        for (Empleado e : empleados) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new ElementoNoEncontradoException("No se encontró ningún empleado con ID " + id);
    }

    public Empleado buscarEmpleadoPorNombre(String nombre) throws ElementoNoEncontradoException {
        for (Empleado e : empleados) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        throw new ElementoNoEncontradoException("No se encontró ningún empleado con nombre " + nombre);
    }

    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }

    public void eliminarEmpleado(int id) throws ElementoNoEncontradoException {
        boolean removido = empleados.removeIf(e -> e.getId() == id);
        if (!removido) {
            throw new ElementoNoEncontradoException("No se encontró ningún empleado con ID " + id);
        }
        // Limpiar referencias en departamentos y proyectos
        for (Departamento d : departamentos) {
            if (d.getJefe() != null) {
                d.getJefe().removerEmpleadoDeDepartamento(id);
                d.getJefe().removerEmpleadoEquipo(id);
            } else {
                d.getEmpleados().removeIf(e -> e.getId() == id);
            }
        }
        for (Proyecto p : proyectos) {
            p.removerEmpleado(id);
        }
    }

    // Métodos para manejo de proyectos
    public void agregarProyecto(Proyecto proy) {
        proyectos.add(proy);
    }

    public List<Proyecto> getProyectos() {
        return new ArrayList<>(proyectos);
    }
    
    public Proyecto buscarProyecto(int id) throws ElementoNoEncontradoException {
        for (Proyecto p : proyectos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ElementoNoEncontradoException("No se encontró proyecto con ID " + id);
    }

    // Métodos para manejo de departametos
    public void agregarDepartamento(Departamento dep) {
        departamentos.add(dep);
    }

    public List<Departamento> getDepartamentos() {
        return new ArrayList<>(departamentos);
    }
    
    public Departamento buscarDepartamento(int id) throws ElementoNoEncontradoException {
        for (Departamento d : departamentos) {
            if (d.getId() == id) {
                return d;
            }
        }
        throw new ElementoNoEncontradoException("No se encontró departamento con ID " + id);
    }
}
