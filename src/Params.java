import java.util.Random;
import java.lang.Math;

/**
 * Parameters for the questing knights simulator.
 *
 * @author Omja Das <835780>
 */
class Params {
    static Random rnd = new Random();

    /**
     * Number of {@link Knight}s in the simulator
     */
    static final int NUM_KNIGHTS = 4;

    /**
     * Average duration that {@link Knight}s spend mingling before and after
     * meetings
     */
    static final int MEAN_MINGLING_TIME = 200;

    /**
     * Average duration that {@link Knight}s spend completing a quest
     */
    static final int MEAN_QUESTING_TIME = 1200;

    /**
     * Average interval between the {@link King} leaving and re-entering the
     * {@link Hall}
     */
    static final int MEAN_KING_WAITING_TIME = 800;

    /**
     * Duration between new {@link Quest}s being added
     */
    static final int QUEST_ADDITION_TIME = 20;

    /**
     * Duration between completed {@link Quest}s being removed
     */
    static final int QUEST_REMOVAL_TIME = 20;

    /**
     * The number of {@link Quest}s an {@link Agenda} can contain
     */
    static final int AGENDA_SIZE = 1;

    /**
     * Generate a random mingling duration
     *
     * @return random mingling duration
     */
    static int getMinglingTime() {
        return (int) Math.max(
            0.0,
            rnd.nextGaussian() * MEAN_MINGLING_TIME / 6 + MEAN_MINGLING_TIME);
    }

    /**
     * Generate a random questing duration
     *
     * @return random questing duration
     */
    static int getQuestingTime() {
        return (int) Math.max(
            0.0,
            rnd.nextGaussian() * MEAN_QUESTING_TIME / 6 + MEAN_QUESTING_TIME);
    }

    /**
     * Generate a random interval for the {@link King} to be away
     *
     * @return random interval for the {@link King} to be away
     */
    static int getKingWaitingTime() {
        return (int) Math.max(
            0.0,
            (rnd.nextGaussian() * MEAN_KING_WAITING_TIME / 8) +
                MEAN_KING_WAITING_TIME);
    }
}
