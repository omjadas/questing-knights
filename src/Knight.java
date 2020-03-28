/**
 * @author Omja Das <835780>
 */

public class Knight extends Thread {
    private int id;
    private Agenda agendaNew;
    private Agenda agendaComplete;
    private Hall greatHall;
    private Quest quest;

    public Knight(
            int id,
            Agenda agendaNew,
            Agenda agendaComplete,
            Hall greatHall) {
        this.id = id;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
        this.greatHall = greatHall;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(Params.getQuestingTime());
                greatHall.enter(this);
                sleep(Params.getMinglingTime());

                sleep(Params.getMinglingTime());
                greatHall.exit(this);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public String toString() {
        return "Knight " + this.id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Knight other = (Knight) obj;
        if (this.id != other.id)
            return false;
        return true;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
