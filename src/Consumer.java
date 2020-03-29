/**
 * Consumes completed quests from an agenda.
 *
 * @author ngeard@unimelb.edu.au
 *
 */

public class Consumer extends Thread {
    /**
     * The agenda from which completed quests are removed
     */
    private final Agenda agenda;

    /**
     * Creates a new consumer for the given agenda
     *
     * @param agenda agenda to consume quests from
     */
    Consumer(Agenda agenda) {
        this.agenda = agenda;
    }

    /**
     * Repeatedly collect completed quests from the agenda
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                // remove a quest that is complete
                Quest quest = agenda.removeComplete();
                String.format(
                    "%s removed from %s",
                    quest.toString(),
                    agenda.getName());

                // let some time pass before the next quest is removed
                sleep(Params.QUEST_REMOVAL_TIME);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
