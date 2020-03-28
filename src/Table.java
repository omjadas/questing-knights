import java.util.HashSet;
import java.util.Set;

/**
 * @author Omja Das <835780>
 */
public class Table {
    private String name;
    private volatile Set<Knight> knights = new HashSet<>();

    /**
     * Create a Table with the given name
     *
     * @param name name of the Table
     */
    Table(String name) {
        this.name = name;
    }

    /**
     * Called when a knight sits down
     *
     * @param knight knight sitting down
     */
    public synchronized void sit(Knight knight) {
        knights.add(knight);
        System.out.println(
            String.format("%s sits at the %s.", knight.toString(), name));
    }

    /**
     * Called when a knight stands up
     * 
     * @param knight knight standing up
     */
    public synchronized void stand(Knight knight) {
        knights.remove(knight);
        System.out.println(
            String.format("%s stands from the %s", knight.toString(), name));
    }

    /**
     * Returns the number of knights sitting at the table
     * 
     * @return number of knights sitting at the table
     */
    public synchronized int numSitting() {
        return knights.size();
    }
}
