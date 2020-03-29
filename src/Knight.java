/**
 * A Knight obtains {@link Quest}s from the {@link King} during meetings.
 *
 * @author Omja Das <835780>
 */
public class Knight extends Thread {
    /**
     * Numeric ID of the Knight
     */
    private final int id;

    /**
     * {@link Hall} that the Knight reports to
     */
    private final Hall hall;

    /**
     * The Knight's current {@link Quest}
     */
    private volatile Quest quest;

    /**
     * Create a Knight with a given id and {@link Hall}
     *
     * @param id   id of the Knight
     * @param hall {@link Hall} of the Knight
     */
    public Knight(int id, Hall hall) {
        this.id = id;
        this.hall = hall;
    }

    /**
     * Perform the actions of a Knight
     */
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
            } catch (InterruptedException e) {}
        }
    }

    /**
     * Returns a unique string identifying the Knight
     *
     * @return unique string identifying the Knight
     */
    @Override
    public String toString() {
        return String.format("Knight %d", id);
    }

    /**
     * Generate a hash for the Knight
     *
     * @return the hash of the Knight
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * Compare the Knight with an Object
     *
     * @param obj object to compare the Knight to
     * @return true if the Knight equals obj, false otherwise
     */
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

    /**
     * Retrieve the Knight's current {@link Quest}
     *
     * @return the current {@link Quest} assigned to the {@link Knight}
     */
    public Quest getQuest() {
        return quest;
    }

    /**
     * Release the Knight's current {@link Quest}
     *
     * @return the {@link Quest} being released
     */
    public Quest releaseQuest() {
        Quest quest = this.quest;
        if (quest != null) {
            this.quest = null;
            System.out.println(
                String.format("%s releases %s.", toString(), quest.toString()));
        }
        return quest;
    }

    /**
     * Assign a {@link Quest} to the Knight
     *
     * @param quest the {@link Quest} to assign to the Knight
     */
    public void acquireQuest(Quest quest) {
        this.quest = quest;
        System.out.println(
            String.format("%s acquires %s.", toString(), quest.toString()));
    }
}
