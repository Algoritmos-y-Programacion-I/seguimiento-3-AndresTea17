package model;
import java.time.LocalDate;

public class Incident {
    private String Description;
    private boolean Solution;
    private int SolutionHours;
    private LocalDate DateReport;

    public Incident(LocalDate Hora, String Descripcion) {
        this.DateReport = Hora;
        this.Description = Descripcion;
    }


    
}
