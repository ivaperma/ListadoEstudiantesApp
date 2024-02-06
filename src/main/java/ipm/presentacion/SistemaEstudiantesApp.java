package ipm.presentacion;

import ipm.dao.EstudianteDao;
import ipm.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        var estudianteDao = new EstudianteDao();
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            } catch (Exception e) {
                System.out.println("Ocurrió un error al ejecutar la operacion: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.print("""
                *** Sistema de estudiantes ***
                1. Listar estudiantes
                2. Buscar estudiante
                3. Agregar estudiante
                4. Modificar estudiante
                5. Eliminar estudiante
                6. Salir
                Elige una opción:
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDao estudianteDao) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("Listado de estudiantes:");
                var estudiantes = estudianteDao.listarEstudiante();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Introduce el id del estudiante");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarPorId(estudiante);
                if (encontrado)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("Estudiante no encontrado: " + estudiante);
            }
            case 3 -> {
                System.out.println("Agregar estudiante: ");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Teléfono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                // Creamos el objeto estudiante, sin el id que es autoincrementable
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDao.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Estudiante agregado: " + estudiante);
                else
                    System.out.println("Estudiante no agregado: " + estudiante);
            }
            case 4 -> {
                System.out.println("Modificar estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Teléfono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                // Creamos objeto estudiante a modificar
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDao.modificar(estudiante);
                if (modificado)
                    System.out.println("Estudiante modificado: " + estudiante);
                else
                    System.out.println("Estudiante no modificado: " + estudiante);

            }
            case 5 -> {
                System.out.println("Eliminar estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDao.eliminar(estudiante);
                if (eliminado)
                    System.out.println("Estudiante eliminado: " + estudiante);
                else
                    System.out.println("Estudiante no eliminado: " + estudiante);
            }
            case 6 -> {
                System.out.println("Hasta pronto");
                salir = true;
            }
            default -> System.out.println("Opción no reconocida");
        }
        return salir;
    }
}