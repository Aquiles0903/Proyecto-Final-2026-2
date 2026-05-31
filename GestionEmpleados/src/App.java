import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class App {
    private static final String ARCHIVO_DATOS = "datos.dat";
    private static GestionDatos datosEmpresa;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        mostrarMenu();
    }

    private static void cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                datosEmpresa = (GestionDatos) ois.readObject();
                System.out.println("Datos cargados correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cargar los datos: " + e.getMessage());
                inicializarDatosPorDefecto();
            }
        } else {
            System.out.println("Archivo de datos no encontrado. Inicializando datos por defecto...");
            inicializarDatosPorDefecto();
        }
    }

    private static void inicializarDatosPorDefecto() {
        datosEmpresa = new GestionDatos();
        try {
            // Crear Gerente
            Gerente gerente = new Gerente(datosEmpresa.generarIdEmpleado(), "Carlos ", "Gerente", 5000, 1000, 50000);
            datosEmpresa.agregarEmpleado(gerente);

            // Crear Jefe
            JefeDepartamento jefe = new JefeDepartamento(datosEmpresa.generarIdEmpleado(), "Ana", 4000, 500);
            datosEmpresa.agregarEmpleado(jefe);

            // Crear Departamento
            Departamento depto = new Departamento(datosEmpresa.generarIdDepartamento(), "IT", "Departamento de Tecnología", 0); 
            depto.setJefe(jefe);
            jefe.setDepartamento(depto);
            datosEmpresa.agregarDepartamento(depto);
            gerente.agregarDepto(depto);

            // Crear 3 Desarrolladores
            Desarrollador d1 = new Desarrollador(datosEmpresa.generarIdEmpleado(), "Luis", 3000, "Java");
            Desarrollador d2 = new Desarrollador(datosEmpresa.generarIdEmpleado(), "Marta", 3200, "Python");
            Desarrollador d3 = new Desarrollador(datosEmpresa.generarIdEmpleado(), "Pedro", 2800, "C++");
            datosEmpresa.agregarEmpleado(d1);
            datosEmpresa.agregarEmpleado(d2);
            datosEmpresa.agregarEmpleado(d3);

            depto.agregarEmpleado(d1);
            depto.agregarEmpleado(d2);
            depto.agregarEmpleado(d3);

            jefe.agregarEmpleadoEquipo(d1);
            jefe.agregarEmpleadoEquipo(d2);
            jefe.agregarEmpleadoEquipo(d3);

            // Crear Proyecto
            Proyecto proy = new Proyecto(datosEmpresa.generarIdProyecto(), "Sistema Nuevo", "Migracion de sistema", LocalDate.of(2026, 1, 1), LocalDate.of(2026, 12, 31));
            proy.agregarEmpleado(d1);
            proy.agregarEmpleado(d2);
            gerente.agregarProyecto(proy);
            d1.asignarProyecto(proy);
            d2.asignarProyecto(proy);
            datosEmpresa.agregarProyecto(proy);

        } catch (Exception e) {
            System.out.println("Error al crear datos iniciales: " + e.getMessage());
        }
    }

    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(datosEmpresa);
            System.out.println("Datos guardados exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Buscar empleado por nombre o ID");
            System.out.println("3. Listar todos los empleados");
            System.out.println("4. Actualizar datos de un empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("6. Crear proyecto");
            System.out.println("7. Listar proyectos");
            System.out.println("8. Asignar presupuesto a proyecto");
            System.out.println("9. Crear departamento");
            System.out.println("10. Listar departamentos");
            System.out.println("11. Calcular la nómina total");
            System.out.println("12. Guardar información");
            System.out.println("13. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        } while (opcion != 13);
    }

    private static void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1: agregarEmpleado(); break;
                case 2: buscarEmpleado(); break;
                case 3: listarEmpleados(); break;
                case 4: actualizarEmpleado(); break;
                case 5: eliminarEmpleado(); break;
                case 6: crearProyecto(); break;
                case 7: listarProyectos(); break;
                case 8: asignarPresupuesto(); break;
                case 9: crearDepartamento(); break;
                case 10: listarDepartamentos(); break;
                case 11: calcularNominaTotal(); break;
                case 12: guardarDatos(); break;
                case 13: 
                    guardarDatos();
                    System.out.println("Saliendo del sistema...");
                    break;
                default: System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error en la operación: " + e.getMessage());
        }
    }

    private static void agregarEmpleado() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Salario Base: ");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.println("Tipo de Puesto: 1. Desarrollador  2. Jefe de Departamento  3. Gerente");
        int tipo = Integer.parseInt(scanner.nextLine());

        int id = datosEmpresa.generarIdEmpleado();
        if (tipo == 1) {
            System.out.print("Lenguaje Principal: ");
            String lang = scanner.nextLine();
            Desarrollador d = new Desarrollador(id, nombre, salario, lang);
            datosEmpresa.agregarEmpleado(d);
            System.out.println("Desarrollador agregado con ID: " + id);
        } else if (tipo == 2) {
            System.out.print("Bono de Productividad: ");
            double bono = Double.parseDouble(scanner.nextLine());
            JefeDepartamento j = new JefeDepartamento(id, nombre, salario, bono);
            datosEmpresa.agregarEmpleado(j);
            System.out.println("Jefe agregado con ID: " + id);
        } else if (tipo == 3) {
            System.out.print("Bono Anual: ");
            double bono = Double.parseDouble(scanner.nextLine());
            System.out.print("Presupuesto Asignado: ");
            double pres = Double.parseDouble(scanner.nextLine());
            Gerente g = new Gerente(id, nombre, "Gerente", salario, bono, pres);
            datosEmpresa.agregarEmpleado(g);
            System.out.println("Gerente agregado con ID: " + id);
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void buscarEmpleado() {
        System.out.println("Buscar por: 1. ID  2. Nombre");
        int tipo = Integer.parseInt(scanner.nextLine());
        try {
            Empleado emp = null;
            if (tipo == 1) {
                System.out.print("Ingrese ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                emp = datosEmpresa.buscarEmpleado(id);
            } else {
                System.out.print("Ingrese Nombre: ");
                String nombre = scanner.nextLine();
                emp = datosEmpresa.buscarEmpleadoPorNombre(nombre);
            }
            emp.mostrarInformacion();
        } catch (ElementoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarEmpleados() {
        List<Empleado> emps = datosEmpresa.getEmpleados();
        if (emps.isEmpty()) {
            System.out.println("No hay empleados.");
            return;
        }
        for (Empleado e : emps) {
            System.out.println("---");
            e.mostrarInformacion();
        }
    }

    private static void actualizarEmpleado() throws ElementoNoEncontradoException {
        System.out.print("Ingrese ID del empleado a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Empleado emp = datosEmpresa.buscarEmpleado(id);
        
        System.out.print("Nuevo Nombre (dejar vacío para no cambiar): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) emp.setNombre(nom);
        
        System.out.print("Nuevo Salario Base (0 para no cambiar): ");
        double sal = Double.parseDouble(scanner.nextLine());
        if (sal > 0) emp.setSalario(sal);

        if (emp instanceof Desarrollador) {
            Desarrollador d = (Desarrollador) emp;
            System.out.print("Nuevo Lenguaje (dejar vacío para no cambiar): ");
            String lang = scanner.nextLine();
            if (!lang.isEmpty()) d.setLenguajePrincipal(lang);
            System.out.print("Horas extra a añadir (0 para ninguna): ");
            int horas = Integer.parseInt(scanner.nextLine());
            if (horas > 0) d.setHorasExtra(horas);
        } else if (emp instanceof JefeDepartamento) {
            System.out.print("Nuevo Bono Productividad (0 para no cambiar): ");
            double bono = Double.parseDouble(scanner.nextLine());
            if (bono > 0) ((JefeDepartamento) emp).setBonoProductividad(bono);
        } else if (emp instanceof Gerente) {
            System.out.print("Nuevo Bono Anual (0 para no cambiar): ");
            double bono = Double.parseDouble(scanner.nextLine());
            if (bono > 0) ((Gerente) emp).setBonoAnual(bono);
            System.out.print("Nuevo Presupuesto Asignado (0 para no cambiar): ");
            double pres = Double.parseDouble(scanner.nextLine());
            if (pres > 0) ((Gerente) emp).setPresupAsig(pres);
        }
        System.out.println("Empleado actualizado.");
    }

    private static void eliminarEmpleado() {
        System.out.print("Ingrese ID del empleado a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            datosEmpresa.eliminarEmpleado(id);
            System.out.println("Empleado eliminado con éxito.");
        } catch (ElementoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void crearProyecto() {
        System.out.print("Nombre del Proyecto: ");
        String nom = scanner.nextLine();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            System.out.print("Fecha Inicio (DD-MM-YYYY): ");
            fechaInicio = LocalDate.parse(scanner.nextLine(), formatter);
            System.out.print("Fecha Fin (DD-MM-YYYY): ");
            fechaFin = LocalDate.parse(scanner.nextLine(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Debe ser DD-MM-YYYY. Operación cancelada.");
            return;
        }

        int id = datosEmpresa.generarIdProyecto();
        Proyecto p = new Proyecto(id, nom, desc, fechaInicio, fechaFin);
        
        System.out.print("Ingrese ID del Gerente a cargo: ");
        int idGerente = Integer.parseInt(scanner.nextLine());
        try {
            Empleado emp = datosEmpresa.buscarEmpleado(idGerente);
            if (emp instanceof Gerente) {
                ((Gerente) emp).agregarProyecto(p);
                datosEmpresa.agregarProyecto(p);
                System.out.println("Proyecto creado y asignado al gerente.");
            } else {
                System.out.println("El ID no corresponde a un Gerente.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarProyectos() {
        List<Proyecto> proys = datosEmpresa.getProyectos();
        if (proys.isEmpty()) {
            System.out.println("No hay proyectos.");
            return;
        }
        for (Proyecto p : proys) {
            System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreProyecto() + " | Presupuesto: $" + p.getPresupuesto());
        }
    }

    private static void asignarPresupuesto() {
        System.out.print("Ingrese ID del proyecto: ");
        int idProy = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese ID del gerente: ");
        int idGer = Integer.parseInt(scanner.nextLine());
        System.out.print("Cantidad a asignar: ");
        double cant = Double.parseDouble(scanner.nextLine());

        try {
            Proyecto p = datosEmpresa.buscarProyecto(idProy);
            Empleado emp = datosEmpresa.buscarEmpleado(idGer);
            if (emp instanceof Gerente) {
                Gerente g = (Gerente) emp;
                g.asignarPresupuestoProyecto(p, cant);
                System.out.println("Presupuesto asignado con éxito.");
            } else {
                System.out.println("El empleado no es gerente.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearDepartamento() {
        System.out.print("Nombre del Departamento: ");
        String nom = scanner.nextLine();
        int id = datosEmpresa.generarIdDepartamento();
        Departamento d = new Departamento(id, nom, nom, id);
        datosEmpresa.agregarDepartamento(d);
        System.out.println("Departamento creado con ID: " + id);
    }

    private static void listarDepartamentos() {
        List<Departamento> deps = datosEmpresa.getDepartamentos();
        if (deps.isEmpty()) {
            System.out.println("No hay departamentos.");
            return;
        }
        for (Departamento d : deps) {
            String jefeNombre = (d.getJefe() != null) ? d.getJefe().getNombre() : "Sin asignar";
            System.out.println("ID: " + d.getId() + " | Nombre: " + d.getNombreDep() + " | Jefe: " + jefeNombre);
        }
    }

    private static void calcularNominaTotal() {
        List<Empleado> emps = datosEmpresa.getEmpleados();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate periodo = null;
        try {
            System.out.print("Ingrese el periodo de pago (DD-MM-YYYY): ");
            periodo = LocalDate.parse(scanner.nextLine(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Debe ser DD-MM-YYYY. Operación cancelada.");
            return;
        }
        Nomina nom = new Nomina(emps, periodo);
        nom.mostrarNomina();
    }
}
