/**
 * @author Omja Das <835780>
 */

import java.util.HashSet;
import java.util.Set;

public class Table {
    private String name;
    private volatile Set<Knight> knights = new HashSet<>();

    Table(String name) {
        this.name = name;
    }

    public synchronized void sit(Knight knight) {
        knights.add(knight);
        System.out.println(
            String.format("%s sits at the %s.", knight.toString(), name));
    }

    public synchronized void stand(Knight knight) {
        knights.remove(knight);
        System.out.println(
            String.format("%s stands from the %s", knight.toString(), name));
    }

    public synchronized int numSitting() {
        return knights.size();
    }
}
