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

    /**
     * Create a new vessel with a given identifier
     *
     * @param id id of the Quest
     */
    private Quest(int id) {
        this.id = id;
        this.completed = false;
    }

    /**
     * Get a new Quest instance with a unique identifier
     * 
     * @return new Quest
     */
    public static Quest getNewQuest() {
        return new Quest(nextId++);
    }

    /**
     * Retrieve completed
     *
     * @return true if the Quest has been completed, false otherwise
     */
    public boolean getCompleted() {
        return completed;
    }

    /**
     * Set the Quest as completed
     */
    public void setCompleted() {
        completed = true;
    }

    /**
     * Produce an identifying string for the Quest
     *
     * @return identifying string for the Quest
     */
    @Override
    public String toString() {
        return "Quest " + id;
    }

    /**
     * Compare the Quest with an Object
     * 
     * @param obj object to compare the quest to
     * @return true if the Quest equals obj, false otherwise
     */
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
