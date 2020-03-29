/**
 * A Quest, with a unique ID, to be assigned to a {@link Knight} for completion.
 * 
 * @author Omja Das <835780>
 */
public class Quest {
    /**
     * A unique identifier for this Quest
     */
    private final int id;

    /**
     * The next ID to be allocated
     */
    private static int nextId = 1;

    /**
     * A flag indicating whether the Quest has been completed
     */
    private boolean completed = false;

    /**
     * Create a new vessel with a given identifier
     *
     * @param id id of the Quest
     */
    private Quest(int id) {
        this.id = id;
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
        return String.format("Quest %d", id);
    }

    /**
     * Compare the Quest with an Object
     * 
     * @param obj object to compare the Quest to
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
