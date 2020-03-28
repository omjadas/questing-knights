import java.util.HashSet;
import java.util.Set;

/**
 * @author Omja Das <835780>
 */

public class Hall {
    private String name;
    private Agenda agendaNew;
    private Agenda agendaComplete;
    private Set<Knight> knights = new HashSet<>();
    private King king;
    private Table table;

    public Hall(String name, Agenda agendaNew, Agenda agendaComplete) {
        this.name = name;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
        this.table = new Table("Round Table");
    }

    public synchronized void enter(Knight knight) {
        while (this.king == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        knights.add(knight);
        System.out.println(
            String.format("%s enters the %s.", knight.toString(), this.name));
    }

    public synchronized void exit(Knight knight) {
        while (this.king != null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        knights.remove(knight);
        System.out.println(
            String.format("%s exits from %s.", knight.toString(), this.name));
    }

    public synchronized void enter(King kingArthur) {
        this.king = kingArthur;
        System.out.println(
            String.format(
                "%s enters the %s.",
                kingArthur.toString(),
                this.name));
        notifyAll();
    }

    public synchronized void exit(King kingArthur) {
        this.king = null;
        System.out.println(
            String.format(
                "%s exits the %s.",
                kingArthur.toString(),
                this.name));
        notifyAll();
    }

    public Table getTable() {
        return this.table;
    }
}
