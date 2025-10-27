package ui;


import java.util.Scanner;
import model.SchoolController;
import model.Computer;
import java.time.LocalDate;

public class SchoolApp {

    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Agregue los atributos (relaciones) necesarios para conectar esta clase con el
     * modelo.
     */

    private Scanner input;
    private SchoolController schoolController;

    public static void main(String[] args) {

        SchoolApp ui = new SchoolApp();
        ui.menu();

    }

    public SchoolApp() {
        input = new Scanner(System.in);
        schoolController = new SchoolController();
    }

    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * El siguiente metodo esta incompleto.
     * Agregue la logica necesaria (instrucciones) para satisfacer los
     * requerimientos
     */

    public void menu() {

        System.out.println("Bienvenido a Computaricemos");

        int option = 0;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("--------------------------------------------------------");
            System.out.println("Digite alguna de las siguientes opciones");
            System.out.println("1) Registrar computador");
            System.out.println("2) Registrar incidente en computador");
            System.out.println("3) Consultar el computador con más incidentes");
            System.out.println("0) Salir del sistema");

            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("\nEntrada inválida. Intente nuevamente.");
                input.nextLine();
                option = -1;
            }

            switch (option) {
                case 1:
                    registrarComputador();
                    break;
                case 2:
                    registrarIncidenteEnComputador();
                    break;
                case 3:
                    consultarComputadorConMasIncidentes();
                    break;
                case 0:
                    System.out.println("\nGracias por usar nuestros servicios. Adios!");
                    break;
                default:
                    if(option != -1) {
                        System.out.println("\nOpcion invalida. Intente nuevamente.");
                    }
                    break;
            }

        } while (option != 0);

    }

    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Los siguientes metodos estan incompletos.
     * Agregue la logica necesaria (instrucciones) para satisfacer los
     * requerimientos
     */

    public void registrarComputador() {
        System.out.println("\n--- REGISTRAR COMPUTADOR ---");
        System.out.print("Ingrese el número serial (único): ");
        String serial = input.nextLine();

        System.out.print("¿Tiene próxima ventana de mantenimiento? (true/false): ");
        boolean nextWindow = input.nextBoolean();
        input.nextLine();

        int[] position = schoolController.agregarComputador(serial, nextWindow);

        if (position != null) {
            System.out.println("Computador registrado exitosamente.");
            System.out.println("Ubicado en Piso: " + (position[0] + 1) + ", Columna: " + (position[1] + 1));
        } else {
            if (schoolController.checkSerialNumberExists(serial)) {
                System.out.println("Error: El número serial ya existe.");
            } else {
                System.out.println("Error: No hay espacio disponible en el edificio.");
            }
        }
    }

    public void registrarIncidenteEnComputador() {
        System.out.println("\n--- REGISTRAR INCIDENTE ---");

        System.out.print("Ingrese el número serial del computador: ");
        String serial = input.nextLine();

        System.out.print("Ingrese el piso (1 a 5): ");
        int piso = input.nextInt() - 1;
        
        System.out.print("Ingrese la columna (1 a 10): ");
        int columna = input.nextInt() - 1;
        input.nextLine();

        System.out.print("Ingrese la descripción del incidente: ");
        String description = input.nextLine();

        LocalDate dateReport = LocalDate.now();

        boolean result = schoolController.agregarIncidenteEnComputador(serial, piso, columna, dateReport, description);

        if (result) {
            System.out.println("Incidente registrado exitosamente en el computador (" + (piso + 1) + ", " + (columna + 1) + ").");
        } else {
            System.out.println("Error: No se pudo registrar el incidente.");
            System.out.println("Verifique el número serial y la ubicación (Piso/Columna).");
        }
    }

    public void consultarComputadorConMasIncidentes() {
        System.out.println("\n--- CONSULTAR COMPUTADOR CON MÁS INCIDENTES ---");
        
        Computer computerMax = null;
        int maxIncidents = -1;

        for (Computer computer : schoolController.getComputerList()) {
            int currentIncidents = computer.getIncidents().size();
            if (currentIncidents > maxIncidents) {
                maxIncidents = currentIncidents;
                computerMax = computer;
            }
        }

        if (computerMax != null && maxIncidents > 0) {
            int[] position = schoolController.findComputerPosition(computerMax.getSerialNumber());
            
            System.out.println("El computador con más incidentes es:");
            System.out.println("   Serial: " + computerMax.getSerialNumber());
            System.out.println("   Total de Incidentes: " + maxIncidents);
            
            if (position != null) {
                System.out.println("   Ubicación (Piso, Columna): (" + (position[0] + 1) + ", " + (position[1] + 1) + ")");
            } 
        } else if (maxIncidents == 0) {
            System.out.println("No se han registrado incidentes en ningún computador.");
        } 
        else {
            System.out.println("Aún no hay computadores registrados.");
        }
    }
}