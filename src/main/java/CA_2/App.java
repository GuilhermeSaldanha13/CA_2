package CA_2;

import java.util.List;
import java.util.Scanner;

/**
 * App is the main application controller for the Department Store Employee Management System.
 *
 * Responsibilities:
 * - Initialize the system and load employee data from file
 * - Display interactive menu and process user selections in English
 * - Manage user interactions for loading, sorting, searching, adding employees and tree visualization
 * - Validate all user inputs using InputValidator
 * - Coordinate between algorithms (Sort, Search) and data structures
 * - Maintain Organization state throughout session
 */
public class App {
    private static final String DEFAULT_DATA_FILE = "Applicants_Form.txt";
    private final Scanner scanner = new Scanner(System.in);
    private final Organization organization = new Organization("Department Store");

    public void run() {
        System.out.println("=== Employee Management System ===\n");
        loadDataFile(DEFAULT_DATA_FILE);

        boolean running = true;
        while (running) {
            printMenu();
            MenuOption option = readMenuOption();
            switch (option) {
                case LOAD_FILE -> handleLoadFile();
                case SORT -> performSort();
                case SEARCH -> performSearch();
                case ADD_RECORD -> performAddRecord();
                case CREATE_TREE -> performCreateTree();
                case DISPLAY_TREE_INFO -> performDisplayTreeInfo();
                case EXIT -> {
                    running = false;
                    System.out.println("Exiting the program. Thank you!");
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("Choose an option:");
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%d - %s\n", option.getNumber(), option.getDescription());
        }
        System.out.print("Option: ");
    }

    private MenuOption readMenuOption() {
        while (true) {
            String line = scanner.nextLine().trim();
            MenuOption option = MenuOption.fromInput(line);
            if (option != null) {
                return option;
            }
            System.out.print("Invalid option. Please enter a valid option: ");
        }
    }

    private void handleLoadFile() {
        System.out.println("\nLoad applicants file");
        String path = readDataFilePath();
        loadDataFile(path);
    }

    private String readDataFilePath() {
        System.out.print("Enter the file path (press Enter to use " + DEFAULT_DATA_FILE + "): ");
        String path = scanner.nextLine().trim();
        return path.isEmpty() ? DEFAULT_DATA_FILE : path;
    }

    private void loadDataFile(String filePath) {
        List<Employee> loadedEmployees = DataLoader.loadEmployees(filePath);
        organization.setEmployees(loadedEmployees);
        organization.initializeDepartments();
        organization.buildHierarchy();

        if (loadedEmployees.isEmpty()) {
            System.out.println("No records found in file: " + filePath);
            System.out.println("Please check the file and try again.");
        } else {
            System.out.println("File loaded successfully: " + loadedEmployees.size() + " records.");
        }
        System.out.println();
    }

    private boolean hasEmployees() {
        return !organization.getEmployees().isEmpty();
    }

    private void performSort() {
        if (!hasEmployees()) {
            System.out.println("No employees available. Load the file first.\n");
            return;
        }

        System.out.println("\nSorting the applicant list...");
        organization.setEmployees(Sort.sort(organization.getEmployees()));
        System.out.println("First 20 sorted names:");
        organization.printFirstNEmployees(20);
        System.out.println();
    }

    private void performSearch() {
        if (!hasEmployees()) {
            System.out.println("No employees available. Load the file first.\n");
            return;
        }

        System.out.println("\nSearching for employee in the sorted list...");
        organization.setEmployees(Sort.sort(organization.getEmployees()));
        System.out.print("Enter the full name to search: ");
        String searchTerm = InputValidator.readNonEmptyString(scanner, "Full name");
        Employee found = Search.search(organization.getEmployees(), searchTerm);
        if (found != null) {
            System.out.println("Record found:");
            System.out.println(found.briefInfo());
        } else {
            System.out.println("Name not found. Make sure the full name is correct.");
        }
        System.out.println();
    }

    private void performAddRecord() {
        System.out.println("\nAdd new employee");
        String firstName = InputValidator.readNonEmptyString(scanner, "First name");
        String lastName = InputValidator.readNonEmptyString(scanner, "Last name");
        String gender = InputValidator.readNonEmptyString(scanner, "Gender");
        String email = InputValidator.readNonEmptyString(scanner, "Email");
        System.out.print("Salary: ");
        double salary = InputValidator.readPositiveDouble(scanner);

        System.out.println("Available manager types:");
        ManagerType.printValues();
        String managerType = InputValidator.readManagerType(scanner);

        System.out.println("Available departments:");
        List<String> departments = organization.getDepartmentNames();
        if (departments.isEmpty()) {
            DepartmentType.printValues();
            System.out.println("If the department is not listed, type the full department name.");
        } else {
            for (int i = 0; i < departments.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, departments.get(i));
            }
        }
        String department = InputValidator.readDepartment(scanner, departments);

        String jobTitle = InputValidator.readNonEmptyString(scanner, "Job title");
        String company = InputValidator.readNonEmptyString(scanner, "Company");

        Employee employee = Employee.create(firstName, lastName, gender, email, salary, department, managerType, jobTitle, company);
        organization.addEmployee(employee);
        organization.buildHierarchy();

        System.out.println("New record added:");
        System.out.println(employee.briefInfo());
        System.out.println();
    }

    private void performCreateTree() {
        if (!hasEmployees()) {
            System.out.println("No employees available. Load the file first.\n");
            return;
        }

        System.out.println("\nBuilding employee hierarchy in a binary tree...");
        organization.buildHierarchy();
        System.out.println("Hierarchy created with " + organization.getHierarchy().getNodeCount() + " nodes.");
        System.out.println();
    }

    private void performDisplayTreeInfo() {
        if (organization.getHierarchy() == null || organization.getHierarchy().isEmpty()) {
            System.out.println("The hierarchy has not been created yet. Select Create Employee Hierarchy first.");
        } else {
            System.out.println("\nHierarchy traversal (level order):");
            organization.getHierarchy().printLevelOrder();
            System.out.println("Tree height: " + organization.getHierarchy().getHeight());
            System.out.println("Total nodes: " + organization.getHierarchy().getNodeCount());
        }
        System.out.println();
    }
}
