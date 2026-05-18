package CA_2;

/**
 * Main is the entry point for the Department Store Employee Management System application.
 * 
 * This class simply instantiates the App class and calls its run() method to start
 * the interactive command-line interface for managing employees and organization hierarchy.
 */
public class Main {
    /**
     * Main method - application entry point.
     * Creates a new App instance and starts the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        new App().run();
    }
}
