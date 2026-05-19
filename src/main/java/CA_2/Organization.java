package CA_2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Organization class represents the Department Store organization structure.
 * It manages employees, departments, and the binary tree hierarchy.
 */
public class Organization {
    private final String name;
    private List<Employee> employees = new ArrayList<>();
    private final Map<String, Department> departments = new LinkedHashMap<>();
    private BinaryTree hierarchy;

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void initializeDepartments() {
        departments.clear();
        for (Employee employee : employees) {
            String departmentName = standardizeDepartmentName(employee.getDepartment());
            departments.computeIfAbsent(departmentName, Department::new).addEmployee(employee);
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        String departmentName = standardizeDepartmentName(employee.getDepartment());
        departments.computeIfAbsent(departmentName, Department::new).addEmployee(employee);
    }

    public void buildHierarchy() {
        hierarchy = new BinaryTree();
        for (Employee employee : employees) {
            hierarchy.insertLevelOrder(employee);
        }
    }

    public BinaryTree getHierarchy() {
        return hierarchy;
    }

    public void printFirstNEmployees(int n) {
        for (int i = 0; i < Math.min(n, employees.size()); i++) {
            System.out.printf("%d. %s%n", i + 1, employees.get(i).getFullName());
        }
    }

    public void printDepartmentNames() {
        if (departments.isEmpty()) {
            System.out.println("No departments registered.");
            return;
        }
        departments.keySet().forEach(departmentName -> System.out.println("- " + departmentName));
    }

    public List<String> getDepartmentNames() {
        return new ArrayList<>(departments.keySet());
    }

    private String standardizeDepartmentName(String departmentName) {
        DepartmentType type = DepartmentType.from(departmentName);
        return type != null ? type.getLabel() : departmentName;
    }

    @Override
    public String toString() {
        return String.format("%s [%d employees]", name, employees.size());
    }
}
