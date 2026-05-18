package CA_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * DataLoader handles loading employee data from external text files.
 * 
 * File Format Expected (CSV):
 * - First line: Header (ignored)
 * - Subsequent lines: Employee records in format:
 *   firstName,lastName,gender,email,salary,department,managerType,jobTitle,company
 * - Missing or invalid fields are skipped or set to default values
 * 
 * Features:
 * - Reads file line by line (memory efficient for large files)
 * - Gracefully handles IO errors and missing files (FR1)
 * - Skips empty lines and incomplete records
 * - Safely parses salary values with fallback to 0.0
 * - Returns list of employees ready for organizational processing
 * 
 * Fulfills FR1: System must allow importing initial employee data from .txt file
 */
public class DataLoader {
    
    /**
     * Loads employees from a CSV text file.
     * File must contain employee records with 9 comma-separated fields.
     * 
     * Format per line:
     * firstName,lastName,gender,email,salary,department,managerType,jobTitle,company
     * 
     * Error Handling:
     * - File not found: Prints error message and returns empty list
     * - IO errors: Prints error message and returns empty list
     * - Incomplete records: Skips the record and continues
     * - Invalid salary: Uses 0.0 as default
     *
     * @param fileName The path to the text file containing employee data
     * @return A list of successfully loaded Employee objects (empty list if file not found)
     */
    public static List<Employee> loadEmployees(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try {
            // Read all lines from file
            List<String> lines = Files.readAllLines(Path.of(fileName));
            
            // Skip first line (header) - start from index 1
            for (int index = 1; index < lines.size(); index++) {
                String line = lines.get(index).trim();
                
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                
                // Split by comma - keep empty fields (don't merge consecutive separators)
                String[] parts = line.split(",", -1);
                
                // Require minimum 9 fields
                if (parts.length < 9) {
                    continue;
                }
                
                // Extract and trim each field
                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String gender = parts[2].trim();
                String email = parts[3].trim();
                double salary = parseSalary(parts[4].trim());
                String department = parts[5].trim();
                String managerType = parts[6].trim();
                String jobTitle = parts[7].trim();
                String company = parts[8].trim();

                String normalizedDepartment = department;
                DepartmentType departmentType = DepartmentType.from(department);
                if (departmentType != null) {
                    normalizedDepartment = departmentType.getLabel();
                }

                employees.add(Employee.create(firstName, lastName, gender, email, salary,
                        normalizedDepartment, managerType, jobTitle, company));
            }
        } catch (IOException e) {
            // Print error message and return empty list
            System.out.println("Error reading input file: " + e.getMessage());
        }
        
        return employees;
    }

    /**
     * Safely parses a salary string to double.
     * Returns 0.0 if the string is not a valid number.
     *
     * @param salaryText The string representation of salary
     * @return The parsed double value, or 0.0 if parsing fails
     */
    private static double parseSalary(String salaryText) {
        try {
            return Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            // Return default value for invalid salary
            return 0.0;
        }
    }
}
