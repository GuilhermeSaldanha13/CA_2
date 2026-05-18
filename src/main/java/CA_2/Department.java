package CA_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Department class represents a department within the organization.
 * Each department has a name and maintains a list of employees assigned to that department.
 * 
 * This class is used to organize and group employees by their department affiliation,
 * supporting the organizational structure required by the Department Store management system.
 * 
 * Attributes:
 * - name: The unique name of the department (e.g., "Sales", "IT", "HR")
 * - employees: A list of all employees assigned to this department
 */
public class Department {
    private final String name;
    private final DepartmentType type;
    private final List<Employee> employees = new ArrayList<>();
    private String depHead = "N/A";

    public Department(String name) {
        this.name = name;
        DepartmentType resolvedType = DepartmentType.from(name);
        this.type = resolvedType != null ? resolvedType : DepartmentType.OTHER;
    }

    public Department(String name, String depHead) {
        this(name);
        this.depHead = depHead;
    }

    public String getName() {
        return name;
    }

    public DepartmentType getDepType() {
        return type;
    }

    public String getDepHead() {
        return depHead;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public String toString() {
        return String.format("Department{name='%s', type=%s, depHead='%s', employees=%d}",
                name, type, depHead, employees.size());
    }
}

