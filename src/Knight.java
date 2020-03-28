/**
 * @author Omja Das <835780>
 */

public class Knight extends Thread {
    private int id;
    private Hall hall;
    private volatile Quest quest;

    public Knight(int id, Hall hall) {
        this.id = id;
        this.hall = hall;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                hall.enter(this);
                sleep(Params.getMinglingTime());
                hall.sit(this);
                hall.meeting(this);
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
                System.out.println(
                    String.format(
                        "%s completes %s!",
                        toString(),
                        quest.toString()));
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

    public Quest releaseQuest() {
        if (quest != null) {
            System.out.println(
                String.format("%s releases %s.", toString(), quest.toString()));
        }
        return quest;
    }

    public void acquireQuest(Quest quest) {
        this.quest = quest;
        System.out.println(
            String.format("%s acquires %s.", toString(), quest.toString()));
    }
}
