/**
 * @author Omja Das <835780>
 */

public class Knight extends Thread {
    private int id;
    private Agenda agendaNew;
    private Agenda agendaComplete;
    private Hall hall;
    private Quest quest;

    public Knight(int id, Agenda agendaNew, Agenda agendaComplete, Hall hall) {
        this.id = id;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
        this.hall = hall;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                hall.enter(this);
                sleep(Params.getMinglingTime());
                hall.sit(this);
                releaseQuest();
                assignQuest();
                hall.stand(this);
                sleep(Params.getMinglingTime());
                hall.exit(this);
                System.out.println(
                    String.format(
                        "%s sets off to complete %s!",
                        toString(),
                        quest.toString()));
                sleep(Params.getQuestingTime());
                quest.setCompleted();
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public String toString() {
        return "Knight " + id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Knight other = (Knight) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public Quest getQuest() {
        return quest;
    }

    private synchronized void releaseQuest() {
        if (quest != null) {
            agendaComplete.addNew(quest);
        }
    }

    private synchronized void assignQuest() {
        quest = agendaNew.getQuest();
        hall.getTable().myNotifyAll();
    }
}
