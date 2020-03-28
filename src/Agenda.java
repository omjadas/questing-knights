import java.util.LinkedList;

/**
 * @author Omja Das <835780>
 */
public class Agenda {
    private LinkedList<Quest> quests = new LinkedList<>();
    private String name;

    public Agenda(String name) {
        this.name = name;
    }

    public LinkedList<Quest> getCompletedQuests() {
        LinkedList<Quest> completedQuests = new LinkedList<>();
        for (Quest quest : quests) {
            if (quest.getCompleted()) {
                completedQuests.add(quest);
            }
        }
        return completedQuests;
    }

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

    public synchronized Quest getQuest() {
        while (quests.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Quest quest = quests.get(0);
        quests.remove(0);
        notifyAll();
        return quest;
    }

    public String getName() {
        return name;
    }
}
