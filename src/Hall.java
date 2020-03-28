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
        while (king == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        knights.add(knight);
        System.out.println(
            String.format("%s enters the %s.", knight.toString(), name));
    }

    public synchronized void exit(Knight knight) {
        while (king != null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        knights.remove(knight);
        System.out.println(
            String.format("%s exits from %s.", knight.toString(), name));
    }

    public synchronized void enter(King king) {
        this.king = king;
        System.out.println(
            String.format(
                "%s enters the %s.",
                king.toString(),
                name));
        notifyAll();
    }

    public synchronized void exit(King king) {
        this.king = null;
        System.out.println(
            String.format(
                "%s exits the %s.",
                king.toString(),
                name));
        notifyAll();
    }

    public Table getTable() {
        return table;
    }
}
