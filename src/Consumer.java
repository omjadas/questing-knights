/**
 * Consumes completed {@link Quest}s from an {@link Agenda}.
 *
 * @author ngeard@unimelb.edu.au
 */
public class Consumer extends Thread {
    /**
     * The {@link Agenda} from which completed {@link Quest}s are removed
     */
    private final Agenda agenda;

    /**
     * Creates a new Consumer for the given {@link Agenda}
     *
     * @param agenda {@link Agenda} to consume quests from
     */
    Consumer(Agenda agenda) {
        this.agenda = agenda;
    }

    /**
     * Repeatedly collect completed {@link Quest}s from the {@link Agenda}
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                // remove a Quest that is complete
                Quest quest = agenda.removeComplete();
                String.format(
                    "%s removed from %s",
                    quest.toString(),
                    agenda.getName());

                // let some time pass before the next Quest is removed
                sleep(Params.QUEST_REMOVAL_TIME);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
