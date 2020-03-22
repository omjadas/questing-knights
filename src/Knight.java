/**
 * @author Omja Das <835780>
 */

public class Knight extends Thread {
    private int id;
    private Agenda agendaNew;
    private Agenda agendaComplete;
    private Hall greatHall;

    public Knight(
            int id,
            Agenda agendaNew,
            Agenda agendaComplete,
            Hall greatHall) {
        this.id = id;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
        this.greatHall = greatHall;
    }

    public void run() {
        while (!isInterrupted()) {
        }
    }
}
