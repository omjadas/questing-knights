/**
 * A quest, with a unique id, to be assigned to a knight for completion
 * 
 * @author ngeard@unimelb.edu.au
 *
 */

public class Quest {
    // a unique identifier for this quest
    private int id;

    // the next ID to be allocated
    private static int nextId = 1;

    // a flag indicating whether the quest has been completed
    private boolean completed;

    // create a new vessel with a given identifier
    private Quest(int id) {
        this.id = id;
        this.completed = false;
    }

    // get a new Quest instance with a unique identifier
    public static Quest getNewQuest() {
        return new Quest(nextId++);
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }

    // produce an identifying string for the quest
    @Override
    public String toString() {
        return "Quest " + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quest other = (Quest) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
