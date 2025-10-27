package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class SchoolController {

    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Agregue los atributos (relaciones) necesarios para satisfacer los
     * requerimientos.
     */
    public static final int PISOS = 5;
    public static final int COMPU = 10;
    public static final int HORAMAXSOPORTE = 100;

    private Computer[][] computerMatrix;

    public SchoolController() {
        this.computerMatrix = new Computer[PISOS][COMPU];

    }

    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Los siguientes metodos estan incompletos.
     * Añada los metodos que considere hagan falta para satisfacer los
     * requerimientos.
     * Para cada metodo:
     * Agregue los parametros y retorno que sean pertinentes.
     * Agregue la logica necesaria (instrucciones) para satisfacer los
     * requerimientos.
     */
    public int[] findFirstAvailablePosition() {
        for (int i = 0; i < PISOS; i++) {
            for (int j = 0; j < COMPU; j++) {
                if (computerMatrix[i][j] == null) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public boolean checkSerialNumberExists(String serialNumber) {
        for (int i = 0; i < PISOS; i++) {
            for (int j = 0; j < COMPU; j++) {
                if (computerMatrix[i][j] != null && computerMatrix[i][j].getSerialNumber().equals(serialNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] agregarComputador(String serialNumber, boolean nextWindow) {
        if (checkSerialNumberExists(serialNumber)) {
            return null;
        }

        int[] position = findFirstAvailablePosition();
        if (position != null) {
            int row = position[0];
            int col = position[1];
            
            computerMatrix[row][col] = new Computer(serialNumber, nextWindow);
            return position;
        }
        return null;
    }

    }

    public boolean agregarIncidenteEnComputador(String serialNumber, int piso, int column, LocalDate dateReport, String description) {
        if (piso < 0 || piso >= PISOS || column < 0 || column >= COMPU) {
            return false;
        }
        
        Computer computer = computerMatrix[piso][column];
        
        if (computer != null && computer.getSerialNumber().equals(serialNumber)) {
            computer.addIncident(dateReport, description);
            return true;
        }
        return false;
    }


    public ArrayList<Computer> getComputerList() {
        ArrayList<Computer> list = new ArrayList<>();
        for (int i = 0; i < PISOS; i++) {
            for (int j = 0; j < COMPU; j++) {
                if (computerMatrix[i][j] != null) {
                    list.add(computerMatrix[i][j]);
                }
            }
        }
        return list;
    }

    public int[] findComputerPosition(String serialNumber) {
        for (int i = 0; i < PISOS; i++) {
            for (int j = 0; j < COMPU; j++) {
                if (computerMatrix[i][j] != null && computerMatrix[i][j].getSerialNumber().equals(serialNumber)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
