/**
 * @author Omja Das <835780>
 */

public class Hall {
    private String name;
    private Agenda agendaNew;
    private Agenda agendaComplete;

    public Hall(String name, Agenda agendaNew, Agenda agendaComplete) {
        this.name = name;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
    }
}
