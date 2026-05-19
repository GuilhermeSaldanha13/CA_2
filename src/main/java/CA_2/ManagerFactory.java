package CA_2;

/**
 * ManagerFactory creates concrete Manager objects based on the manager type.
 */
public class ManagerFactory {

    public static Manager create(String managerName, ManagerType managerType) {
        if (managerType == null) {
            return new DepartmentManager(managerName, ManagerType.MANAGER);
        }
        return switch (managerType) {
            case STORE_MANAGER -> new StoreManager(managerName, managerType);
            case DEPARTMENT_MANAGER -> new DepartmentManager(managerName, managerType);
            case FLOOR_SUPERVISOR -> new FloorSupervisor(managerName, managerType);
            case SENIOR_MANAGER, TEAM_LEAD, ASSISTANT_MANAGER, HEAD_MANAGER, MANAGER,
                 INTERN, JUNIOR, CONTRACT, MIDDLE -> new DepartmentManager(managerName, managerType);
            default -> new DepartmentManager(managerName, managerType);
        };
    }
}
