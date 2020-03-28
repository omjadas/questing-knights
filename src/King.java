/**
 * @author Omja Das <835780>
 */

public class King extends Thread {
    private String name;
    private Hall greatHall;

    public King(String name, Hall greatHall) {
        this.name = name;
        this.greatHall = greatHall;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(Params.getKingWaitingTime());
                greatHall.enter(this);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
