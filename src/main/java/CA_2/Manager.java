package CA_2;

/**
 * Base Manager class for all manager roles in the organization.
 */
public abstract class Manager {
    private final String managerName;
    private final ManagerType managerType;

    public Manager(String managerName, ManagerType managerType) {
        this.managerName = managerName;
        this.managerType = managerType;
    }

    public String getManagerName() {
        return managerName;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", managerName, managerType.getLabel(), getRole());
    }
}
