import java.util.LinkedList;

/**
 * @author Omja Das <835780>
 */
public class Agenda {
    private LinkedList<Quest> quests = new LinkedList<>();
    private String name;

    /**
     * Create an Agenda with a given name
     *
     * @param name name of the Agenda
     */
    public Agenda(String name) {
        this.name = name;
    }

    /**
     * Returns a list of completed quests in the Agenda
     *
     * @return list of completed quests in the Agenda
     */
    public LinkedList<Quest> getCompletedQuests() {
        LinkedList<Quest> completedQuests = new LinkedList<>();
        for (Quest quest : quests) {
            if (quest.getCompleted()) {
                completedQuests.add(quest);
            }
        }
        return completedQuests;
    }

    /**
     * Removes a single completed test from the Agenda
     *
     * @return quest that has been removed
     */
    public synchronized Quest removeComplete() {
        while (getCompletedQuests().size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Quest quest = getCompletedQuests().remove();
        quests.remove(quest);
        notifyAll();
        return quest;
    }

    /**
     * Adds a new quest to the Agenda
     *
     * @param quest quest to add to the Agenda
     */
    public synchronized void addNew(Quest quest) {
        while (quests.size() >= Params.AGENDA_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        quests.add(quest);
        notifyAll();
    }

    /**
     * Retrieves a single quest from the Agenda
     *
     * @return a quest
     */
    public synchronized Quest getQuest() {
        while (quests.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
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
