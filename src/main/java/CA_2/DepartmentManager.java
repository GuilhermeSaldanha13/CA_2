package CA_2;

public class DepartmentManager extends Manager {
    public DepartmentManager(String managerName, ManagerType managerType) {
        super(managerName, managerType);
    }

    @Override
    public String getRole() {
        return "Department Manager";
    }
}
