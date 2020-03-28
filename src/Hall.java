import java.util.HashSet;
import java.util.Set;

/**
 * @author Omja Das <835780>
 */
public class Hall {
    private final String name;
    private final Agenda agendaNew;
    private final Agenda agendaComplete;
    private final Set<Knight> knights = new HashSet<>();
    private final Table table;
    private volatile King king;
    private volatile boolean meetingInProgress = false;

    public Hall(String name, Agenda agendaNew, Agenda agendaComplete, Table table) {
        this.name = name;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
        this.table = table;
    }

    /**
     * Called when a knight enters the hall
     *
     * @param knight the knight entering the hall
     */
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

    /**
     * Called when a knight exits the hall
     *
     * @param knight the knight exiting the hall
     */
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

    /**
     * Called when the king enters the hall
     *
     * @param king the king entering the hall
     */
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

    /**
     * Called when the king exits the hall
     *
     * @param king the king exiting the hall
     */
    public synchronized void exit(King king) {
        this.king = null;
        System.out.println(
            String.format("%s exits the %s.", king.toString(), name));
        notifyAll();
    }

    /**
     * Called when a knight sits down
     *
     * @param knight the knight sitting down
     */
    public synchronized void sit(Knight knight) {
        table.sit(knight);
        notifyAll();
    }

    /**
     * Called when a knight stands up
     *
     * @param knight the knight standing up
     */
    public synchronized void stand(Knight knight) {
        table.stand(knight);
        notifyAll();
    }

    /**
     * Called at the start of the meeting
     */
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

    /**
     * Perform the actions required of a knight during a meeting
     *
     * @param knight knight to perform actions
     */
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

    /**
     * Called at the end of the meeting
     */
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
