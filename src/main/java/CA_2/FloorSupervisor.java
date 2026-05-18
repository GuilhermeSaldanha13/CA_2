package CA_2;

public class FloorSupervisor extends Manager {
    public FloorSupervisor(String managerName, ManagerType managerType) {
        super(managerName, managerType);
    }

    @Override
    public String getRole() {
        return "Floor Supervisor";
    }
}
