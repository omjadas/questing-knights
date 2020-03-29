/**
 * Produces new {@link Quest}s for the {@link Knight}s to complete.
 *
 * @author Omja Das <835780>
 */
public class Producer extends Thread {
    /**
     * The Agenda the Producer creates {@link Quest}s for
     */
    private final Agenda agenda;

    /**
     * Create a new Producer
     *
     * @param agenda agenda to produce the {@link Quest}s for
     */
    Producer(Agenda agenda) {
        this.agenda = agenda;
    }

    /**
     * Create {@link Quest}s and add them to {@link #Agenda}
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                // create a new Quest and send it to the Agenda.
                Quest quest = Quest.getNewQuest();
                agenda.addNew(quest);
                System.out.println(
                    String.format(
                        "%s added to %s.",
                        quest.toString(),
                        agenda.getName()));

                // let some time pass before the next Quest arrives
                sleep(Params.QUEST_ADDITION_TIME);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
