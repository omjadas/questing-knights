/**
 * @author Omja Das <835780>
 */

public class KingArthur extends Thread {
    private Hall greatHall;

    public KingArthur(Hall greatHall) {
        this.greatHall = greatHall;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(Params.getKingWaitingTime());
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public String toString() {
        return "King Arthur";
    }
}
