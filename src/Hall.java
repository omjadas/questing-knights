import java.util.HashSet;
import java.util.Set;

/**
 * A Hall is where meetings take place, {@link Knight}s and {@link King}s can
 * enter and exit the Hall. A Hall contains a {@link Table} where
 * {@link Knight}s sit during meetings.
 *
 * @author Omja Das <835780>
 */
public class Hall {
    /**
     * Name of the Hall
     */
    private final String name;

    /**
     * Agenda containing new {@link Quest}s
     */
    private final Agenda agendaNew;

    /**
     * Agenda containing completed {@link Quest}s
     */
    private final Agenda agendaComplete;

    /**
     * {@link Knight}s present in the Hall
     */
    private final Set<Knight> knights = new HashSet<>();

    /**
     * {@link Table} that the Hall contains
     */
    private final Table table;

    /**
     * {@link King} of the Hall, null if the {@link King} is not present
     */
    private volatile King king;

    /**
     * True if a meeting is in progress, false otherwise
     */
    private volatile boolean meetingInProgress = false;

    /**
     * Create a Hall with a given name, {@link Agenda} for new {@link Quest}s,
     * {@link Agenda} for completed {@link Quest}s, and a {@link Table}
     *
     * @param name           name of the Hall
     * @param agendaNew      {@link Agenda} containing new {@link Quest}s
     * @param agendaComplete {@link Agenda} containing completed {@link Quest}s
     * @param table          {@link Table} in the Hall
     */
    public Hall(
            String name,
            Agenda agendaNew,
            Agenda agendaComplete,
            Table table) {
        this.name = name;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
        this.table = table;
    }

    /**
     * Called when a {@link Knight} enters the Hall
     *
     * @param knight the {@link Knight} entering the Hall
     */
    public synchronized void enter(Knight knight) {
        // Wait until the King is not present
        while (king != null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        knights.add(knight);
        System.out.println(
            String.format("%s enters the %s.", knight.toString(), name));
    }

    /**
     * Called when a {@link Knight} exits the Hall
     *
     * @param knight the {@link Knight} exiting the Hall
     */
    public synchronized void exit(Knight knight) {
        // Wait until the King is not present
        while (king != null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        knights.remove(knight);
        System.out.println(
            String.format("%s exits from %s.", knight.toString(), name));
        notifyAll();
    }

    /**
     * Called when the {@link King} enters the Hall
     *
     * @param king the {@link King} entering the Hall
     */
    public synchronized void enter(King king) {
        // Wait until all Knights from previous meeting have left
        while (!knights.stream().allMatch(knight -> {
            Quest quest = knight.getQuest();
            if (quest != null) {
                return quest.getCompleted() == true;
            }
            return true;
        })) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.king = king;
        System.out.println(
            String.format("%s enters the %s.", king.toString(), name));
        notifyAll();
    }

    /**
     * Called when the {@link King} exits the Hall
     *
     * @param king the {@link King} exiting the Hall
     */
    public synchronized void exit(King king) {
        this.king = null;
        System.out.println(
            String.format("%s exits the %s.", king.toString(), name));
        notifyAll();
    }

    /**
     * Called when a {@link Knight} sits down
     *
     * @param knight the {@link Knight} sitting down
     */
    public synchronized void sit(Knight knight) {
        table.sit(knight);
        notifyAll();
    }

    /**
     * Called when a {@link Knight} stands up
     *
     * @param knight the {@link Knight} standing up
     */
    public synchronized void stand(Knight knight) {
        table.stand(knight);
        notifyAll();
    }

    /**
     * Called at the start of the meeting
     */
    public synchronized void startMeeting() {
        // Wait until all of the Knights are sitting at the Table
        while (table.numSitting() < knights.size()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        meetingInProgress = true;
        System.out.println("Meeting begins!");
        notifyAll();
    }

    /**
     * Perform the actions required of a {@link Knight} during a meeting
     *
     * @param knight {@link Knight} to perform actions
     */
    public synchronized void meeting(Knight knight) {
        // Wait until the meeting is in progress
        while (!meetingInProgress) {
            try {
                wait();
            } catch (InterruptedException e) {}
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
        // Wait until no one is sitting
        while (table.numSitting() > 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        meetingInProgress = false;
        System.out.println("Meeting ends!");
    }
}
