import java.util.HashSet;
import java.util.Set;

/**
 * @author Omja Das <835780>
 */
public class Hall {
    private String name;
    private Agenda agendaNew;
    private Agenda agendaComplete;
    private volatile Set<Knight> knights = new HashSet<>();
    private King king;
    private Table table;
    private volatile boolean meetingInProgress = false;

    public Hall(String name, Agenda agendaNew, Agenda agendaComplete) {
        this.name = name;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
        this.table = new Table("Round Table");
    }

    public synchronized void enter(Knight knight) {
        while (king != null) {
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
        notifyAll();
    }

    public synchronized void enter(King king) {
        while (!knights.stream().allMatch(knight -> {
            Quest quest = knight.getQuest();
            if (quest != null) {
                return quest.getCompleted() == true;
            }
            return true;
        })) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.king = king;
        System.out.println(
            String.format("%s enters the %s.", king.toString(), name));
        notifyAll();
    }

    public synchronized void exit(King king) {
        this.king = null;
        System.out.println(
            String.format("%s exits the %s.", king.toString(), name));
        notifyAll();
    }

    public synchronized void sit(Knight knight) {
        table.sit(knight);
        notifyAll();
    }

    public synchronized void stand(Knight knight) {
        table.stand(knight);
        notifyAll();
    }

    public Table getTable() {
        return table;
    }

    public King getKing() {
        return king;
    }

    public synchronized void startMeeting() {
        while (table.numSitting() < knights.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        meetingInProgress = true;
        System.out.println("Meeting begins!");
        notifyAll();
    }

    public synchronized void meeting(Knight knight) {
        while (!meetingInProgress) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Quest releasedQuest = knight.releaseQuest();
        if (releasedQuest != null) {
            agendaComplete.addNew(releasedQuest);
        }
        knight.acquireQuest(agendaNew.getQuest());
    }

    public synchronized void endMeeting() {
        while (table.numSitting() > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        meetingInProgress = false;
        System.out.println("Meeting ends!");
    }
}
