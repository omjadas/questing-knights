/**
 * @author Omja Das <835780>
 */

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Quest> quests = new ArrayList<>();
    private String name;

    public Agenda(String name) {
        this.name = name;
    }

    public List<Quest> getCompletedQuests() {
        List<Quest> completedQuests = new ArrayList<>();
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
        Quest quest = getCompletedQuests().get(0);
        quests.remove(quest);
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

    public String getName() {
        return name;
    }
}
