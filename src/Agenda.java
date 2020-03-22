/**
 * @author Omja Das <835780>
 */

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Quest> quests = new ArrayList<>();
    private String name;

    public Agenda(String name) {
        this.name = name;
    }

    public void removeComplete() {
        for (int i = 0; i < quests.size(); i++) {
            Quest quest = quests.get(i);
            if (quest.completed) {
                quests.remove(i);
                System.out.println(
                    String.format(
                        "%s removed from %s",
                        quest.toString(),
                        this.name));
                return;
            }
        }
    }

    public void addNew(Quest quest) {
        quests.add(quest);
        System.out.println(
            String.format("%s added to %s", quest.toString(), this.name));
    }
}
