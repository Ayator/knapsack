import java.util.concurrent.atomic.AtomicBoolean;

/* The SpaceTime class extends Thread and is designed to monitor the execution of a program by:
 *  - Measuring runtime: Tracks how long the program runs.
 *  - Monitoring memory usage: Tracks the peak amount of memory used during execution.
 * It uses Java's Runtime class to access system resources and AtomicBoolean for thread-safe control of its state.
 * By extending Thread, SpaceTime can run concurrently with the main program.
 * This allows it to monitor runtime and memory usage independently - Thread safe.
 */
public class SpaceTime extends Thread{
    // The interval (in milliseconds) between memory usage checks.
    // This is set to 1L, meaning the thread will check memory usage every 1 millisecond.
    private final long intervalTime = 1L;
    // Records the system time (in milliseconds) at the start of the thread's execution. Used to calculate runtime duration.
    private long initialTime;
    // A thread-safe boolean flag indicating whether the thread should continue running. Thread will stop if false.
    private final AtomicBoolean running = new AtomicBoolean(true);
    // Constants for converting bytes to megabytes and gigabytes
    private final long megabyte = 1024L * 1024L;
    private final long gigabyte = megabyte * 1024L;
    // Tracks the peak memory usage observed during the program's execution.
    private long maxMemoryUsed;
    
    // Represents the Java Runtime instance, which provides access to memory-related methods like totalMemory() and freeMemory().
    private Runtime runtime = Runtime.getRuntime();

    // Prints the total runtime of the thread in milliseconds.
    public void printFinalTime(){
        System.out.println("Runtime: " + (System.currentTimeMillis() - initialTime) + "ms");
    }

    // Prints the peak memory usage (in megabytes) observed during the thread's execution.
    public void printMaxMemoryUsed(){
        System.out.println("Max memory: " + bytesToMb(maxMemoryUsed) + "mb");
    }

    // Converts bytes to megabytes by dividing by megabyte.
    private long bytesToMb(long kb){
        return kb / megabyte;
    }

    // Converts bytes to gigabytes by dividing by gigabyte.
    private long bytesToGb(long kb){
        return kb / gigabyte;
    }

    // Main execution logic of the thread. Continuously monitors memory usage while the thread is running.
    @Override
    public void run() {
        // Set running to true and call garbage collector, ensuring clean memory baseline.
        running.set(true);
        runtime.gc();
        // Set maxMemoryUsed to 0 and record the current system time in initialTime.
        maxMemoryUsed = 0;
        initialTime = System.currentTimeMillis();
        // This loop will continue running while running is true
        while(running.get()){
            try {
                // Sleeps for intervalTime milliseconds
                Thread.sleep(intervalTime);
                // Calculates current memory usage
                long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
                // Update maxMemoryUsed
                if(memoryUsed > maxMemoryUsed)
                    maxMemoryUsed = memoryUsed;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    // Stops the thread by setting the running flag to false.
    // This causes the run() method's while loop to exit, effectively ending the thread's execution.
    public void stopThread() {
        running.set(false);
    }

    // Prints runtime and/or peak memory usage based on the input flags.
    public void printData(boolean printTime, boolean printMaxMemory) {
        if(printTime)
            printFinalTime();
        if(printMaxMemory)
            printMaxMemoryUsed();
    }
    
}
