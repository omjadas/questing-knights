import java.util.LinkedList;

/**
 * An Agenda is a container for {@link Quest}s, the max number of {@link Quest}s
 * an Agenda can contain is defined at {@link Params#AGENDA_SIZE}.
 *
 * @author Omja Das <835780>
 */
public class Agenda {
    /**
     * {@link Quest}s contained within the Agenda
     */
    private final LinkedList<Quest> quests = new LinkedList<>();

    /**
     * Name of the Agenda
     */
    private final String name;

    /**
     * Create an Agenda with a given name
     *
     * @param name name of the Agenda
     */
    public Agenda(String name) {
        this.name = name;
    }

    /**
     * Returns a list of completed {@link Quest}s in the Agenda
     *
     * @return list of completed {@link Quest}s in the Agenda
     */
    public synchronized LinkedList<Quest> getCompletedQuests() {
        LinkedList<Quest> completedQuests = new LinkedList<>();
        for (Quest quest : quests) {
            if (quest.getCompleted()) {
                completedQuests.add(quest);
            }
        }
        return completedQuests;
    }

    /**
     * Removes a single completed {@link Quest} from the Agenda
     *
     * @return {@link Quest} that has been removed
     */
    public synchronized Quest removeComplete() {
        // Wait until the Agenda contains Quests
        while (getCompletedQuests().size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        Quest quest = getCompletedQuests().remove();
        quests.remove(quest);
        notifyAll();
        return quest;
    }

    /**
     * Adds a new {@link Quest} to the Agenda
     *
     * @param quest {@link Quest} to add to the Agenda
     */
    public synchronized void addNew(Quest quest) {
        // Wait while the Agenda contains too many Quests
        while (quests.size() >= Params.AGENDA_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        quests.add(quest);
        notifyAll();
    }

    /**
     * Retrieves a single {@link Quest} from the Agenda
     *
     * @return a {@link Quest}
     */
    public synchronized Quest getQuest() {
        // Wait while the Agenda does not contain any Quests
        while (quests.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        Quest quest = quests.remove();
        notifyAll();
        return quest;
    }

    /**
     * Returns the name of the Agenda
     *
     * @return the name of the Agenda
     */
    public String getName() {
        return name;
    }
}
