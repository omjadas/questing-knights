/**
 * @author Omja Das <835780>
 */
public class King extends Thread {
    private final String name;
    private final Hall hall;

    /**
     * Create a King with a given name and hall
     *
     * @param name name of the King
     * @param hall hall of the King
     */
    public King(String name, Hall hall) {
        this.name = name;
        this.hall = hall;
    }

    /**
     * Perform the actions of the King
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                hall.enter(this);
                hall.startMeeting();
                hall.endMeeting();
                hall.exit(this);
                sleep(Params.getKingWaitingTime());
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Returns the King's name
     *
     * @return the name of the King
     */
    @Override
    public String toString() {
        return name;
    }
}
