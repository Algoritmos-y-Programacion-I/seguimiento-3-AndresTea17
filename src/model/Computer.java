package model;
import java.util.ArrayList;

public class Computer {
    private String serialNumber;
    private boolean NextWindow;
    private ArrayList<Incident> incidents;
    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Agregue los atributos (relaciones) necesarios para satisfacer los
     * requerimientos.
     */

    public Computer(String Numero, boolean Window) {
        this.serialNumber = Numero;
        this.NextWindow = Window;
        this.incidents = new ArrayList<>();
    }

    public void addIncident(LocalDate Hora, String Descripcion) {
        incidents.add(new Incident())
    }

}
