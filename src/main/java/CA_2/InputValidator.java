package CA_2;

import java.util.List;
import java.util.Scanner;

/**
 * InputValidator provides utility methods for validating and reading user input.
 * It ensures data integrity by validating all user entries before they are processed.
 * Methods include validation for positive doubles, non-empty strings, manager types, and departments.
 */
public class InputValidator {
    
    /**
     * Reads a positive double value from the scanner, with repeated prompts for invalid input.
     * Validates that the entered value is a valid number and is non-negative.
     *
     * @param scanner The Scanner object to read user input from
     * @return A non-negative double value as entered by the user
     */
    public static double readPositiveDouble(Scanner scanner) {
        while (true) {
            String value = scanner.nextLine().trim();
            try {
                double result = Double.parseDouble(value);
                if (result >= 0) {
                    return result;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.print("Invalid value. Enter a valid salary number: ");
        }
    }

    /**
     * Reads a non-empty string from the scanner, with repeated prompts until valid input is provided.
     * A valid string is one that is not blank (contains non-whitespace characters).
     *
     * @param scanner The Scanner object to read user input from
     * @param fieldName The name of the field being validated (used in error message)
     * @return A non-empty string as entered by the user
     */
    public static String readNonEmptyString(Scanner scanner, String fieldName) {
        while (true) {
            String value = scanner.nextLine().trim();
            if (!value.isBlank()) {
                return value;
            }
            System.out.print(fieldName + " cannot be blank. Please enter again: ");
        }
    }

    /**
     * Reads a valid manager type from the scanner.
     * The user can enter either the enum name or the label of a valid manager type.
     * Invalid entries will prompt repeated attempts.
     *
     * @param scanner The Scanner object to read user input from
     * @return A valid manager type label string
     */
    public static String readManagerType(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (ManagerType.contains(input)) {
                return ManagerType.from(input).getLabel();
            }
            System.out.print("Invalid manager type. Enter an existing manager type: ");
        }
    }

    /**
     * Reads a valid department name from the scanner.
     * The user can enter either the department number (1-based index) or the exact department name.
     * Invalid entries will prompt repeated attempts.
     *
     * @param scanner The Scanner object to read user input from
     * @param validDepartments A list of valid department names
     * @return A valid department name from the provided list
     */
    public static String readDepartment(Scanner scanner, List<String> validDepartments) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("Invalid department. Enter the number or name of an existing department: ");
                continue;
            }

            if (!validDepartments.isEmpty()) {
                if (input.matches("\\d+")) {
                    int index = Integer.parseInt(input) - 1;
                    if (index >= 0 && index < validDepartments.size()) {
                        return validDepartments.get(index);
                    }
                }
                for (String department : validDepartments) {
                    if (department.equalsIgnoreCase(input)) {
                        return department;
                    }
                }
            }

            if (DepartmentType.contains(input)) {
                return DepartmentType.from(input).getLabel();
            }

            if (validDepartments.isEmpty()) {
                return input;
            }

            System.out.print("Invalid department. Enter the number or name of an existing department: ");
        }
    }
}
