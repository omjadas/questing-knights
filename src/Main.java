/**
 * The top-level component of the questing knights simulator.
 *
 * It is responsible for:
 * - creating all the components of the system;
 * - starting all of the processes;
 *
 * @author Omja Das <835780>
 */
public class Main {
    /**
     * Run the simulation
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        // generate the Hall and Agendas
        Agenda agendaNew = new Agenda("New Agenda");
        Agenda agendaComplete = new Agenda("Complete Agenda");
        Table roundTable = new Table("Round Table");
        Hall greatHall = new Hall(
            "Great Hall",
            agendaNew,
            agendaComplete,
            roundTable);

        // generate the Producer, Consumer and King processes
        Producer producer = new Producer(agendaNew);
        Consumer consumer = new Consumer(agendaComplete);
        King kingArthur = new King("King Arthur", greatHall);

        // create an array of Knights
        Knight[] knights = new Knight[Params.NUM_KNIGHTS];

        // generate and start the individual Knight processes
        for (int i = 0; i < Params.NUM_KNIGHTS; i++) {
            knights[i] = new Knight(i + 1, greatHall);
            knights[i].start();
        }

        // start the remaining processes
        producer.start();
        consumer.start();
        kingArthur.start();

        // join all processes
        for (int i = 0; i < Params.NUM_KNIGHTS; i++) {
            knights[i].join();
        }
        producer.join();
        consumer.join();
        kingArthur.join();
    }
}
