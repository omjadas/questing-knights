import java.util.HashSet;
import java.util.Set;

/**
 * @author Omja Das <835780>
 */

public class Hall {
    private String name;
    private Agenda agendaNew;
    private Agenda agendaComplete;
    private Set<Knight> knights = new HashSet<>();
    private KingArthur kingArthur;

    public Hall(String name, Agenda agendaNew, Agenda agendaComplete) {
        this.name = name;
        this.agendaNew = agendaNew;
        this.agendaComplete = agendaComplete;
    }

    public void enterKnight(Knight knight) {
        knights.add(knight);
        System.out.println(String.format("%s enters the %s.", knight.toString(), name));
    }

    public void leaveKnight(Knight knight) {
        knights.remove(knight);
        System.out.println(String.format("%s exits from %s.", knight.toString(), name));
    }

    public void enterKing(KingArthur kingArthur) {
        this.kingArthur = kingArthur;
        System.out.println(String.format("%s enters the %s.", kingArthur.toString(), name));
    }
    
    public void leaveKing(KingArthur kingArthur) {
        this.kingArthur = null;
        System.out.println(kingArthur.toString() + "enters " + name);
        System.out.println(String.format("%s exits the %s.", kingArthur.toString(), name));
    }
}
