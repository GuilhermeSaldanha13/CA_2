package CA_2;

public class InternEmployee extends Employee {
    public InternEmployee(String firstName, String lastName, String gender, String email, double salary,
                          String department, String managerType, String jobTitle, String company) {
        super(firstName, lastName, gender, email, salary, department, managerType, jobTitle, company);
    }

    @Override
    public String getEmployeeCategory() {
        return "Intern";
    }
}
