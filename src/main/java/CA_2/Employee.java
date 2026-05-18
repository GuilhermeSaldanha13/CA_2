package CA_2;

/**
 * Employee class represents an individual employee in the Department Store organization.
 * Employees are compared primarily by last name (alphabetically), then by first name.
 * This class implements Comparable to support sorting operations like Merge Sort.
 * 
 * Attributes:
 * - firstName: The employee's first name
 * - lastName: The employee's last name
 * - gender: The employee's gender
 * - email: The employee's contact email
 * - salary: The employee's salary amount
 * - department: The department name where the employee works
 * - managerType: The management level or type (e.g., Manager, Team Lead, etc.)
 * - jobTitle: The specific job title/position
 * - company: The company or organization name
 */
import java.util.Locale;

public class Employee implements Comparable<Employee> {
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String email;
    private final double salary;
    private final String department;
    private final String managerType;
    private final String jobTitle;
    private final String company;

    public Employee(String firstName, String lastName, String gender, String email, double salary,
                    String department, String managerType, String jobTitle, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.managerType = ManagerType.from(managerType).getLabel();
        this.jobTitle = jobTitle;
        this.company = company;
    }

    public static Employee create(String firstName, String lastName, String gender, String email, double salary,
                                  String department, String managerType, String jobTitle, String company) {
        String normalizedManagerType = ManagerType.from(managerType).getLabel();
        String normalized = normalizedManagerType.toLowerCase(Locale.ROOT);
        if (normalized.contains("contract")) {
            return new ContractEmployee(firstName, lastName, gender, email, salary, department, normalizedManagerType, jobTitle, company);
        }
        if (normalized.contains("intern")) {
            return new InternEmployee(firstName, lastName, gender, email, salary, department, normalizedManagerType, jobTitle, company);
        }
        if (normalized.contains("junior") && !normalized.contains("senior")) {
            return new JuniorEmployee(firstName, lastName, gender, email, salary, department, normalizedManagerType, jobTitle, company);
        }
        if (normalized.contains("senior")) {
            return new SeniorEmployee(firstName, lastName, gender, email, salary, department, normalizedManagerType, jobTitle, company);
        }
        return new Employee(firstName, lastName, gender, email, salary, department, normalizedManagerType, jobTitle, company);
    }

    /**
     * Gets the employee's first name.
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the employee's last name.
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the employee's gender.
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the employee's email address.
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the employee's salary.
     * @return The salary amount
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Gets the department name where the employee works.
     * @return The department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Gets the employee's manager type or management level.
     * @return The manager type
     */
    public String getManagerType() {
        return managerType;
    }

    /**
     * Gets the employee's job title or position.
     * @return The job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Gets the company or organization name.
     * @return The company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Gets the employee's full name (first name + last name).
     * @return Full name in format "FirstName LastName"
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getSortKey() {
        return lastName + " " + firstName;
    }

    public String getEmployeeCategory() {
        return "Employee";
    }

    /**
     * Gets a brief information string about the employee for display purposes.
     * Format: "FullName | Email | ManagerType | Department | JobTitle"
     * @return Brief info string
     */
    public String briefInfo() {
        return String.format("%s | %s | %s | %s | %s", getFullName(), email, managerType, department, jobTitle);
    }

    /**
     * Compares two employees for sorting purposes.
     * Primary comparison: last name (case-insensitive, A-Z)
     * Secondary comparison: first name (case-insensitive, A-Z) if last names are equal
     *
     * @param other The employee to compare with
     * @return Negative if this employee comes before other, positive if after, zero if equal
     */
    @Override
    public int compareTo(Employee other) {
        int last = lastName.compareToIgnoreCase(other.lastName);
        if (last != 0) {
            return last;
        }
        return firstName.compareToIgnoreCase(other.firstName);
    }

    /**
     * Returns a CSV-formatted string representation of the employee.
     * Format: firstName,lastName,gender,email,salary,department,managerType,jobTitle,company
     * Used for exporting/saving employee data.
     *
     * @return CSV format string
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%.2f,%s,%s,%s,%s",
                firstName, lastName, gender, email, salary, department, managerType, jobTitle, company);
    }
}

