package CA_2;

public class StoreManager extends Manager {
    public StoreManager(String managerName, ManagerType managerType) {
        super(managerName, managerType);
    }

    @Override
    public String getRole() {
        return "Store Manager";
    }
}
