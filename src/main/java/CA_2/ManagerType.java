package CA_2;

import java.util.Arrays;

/**
 * ManagerType is an enumeration of all valid manager types/levels in the organization.
 * Each manager type has a descriptive label for display purposes.
 */
public enum ManagerType {
    STORE_MANAGER("Store Manager"),
    DEPARTMENT_MANAGER("Department Manager"),
    FLOOR_SUPERVISOR("Floor Supervisor"),
    SENIOR_MANAGER("Senior Manager"),
    TEAM_LEAD("Team Lead"),
    ASSISTANT_MANAGER("Assistant Manager"),
    HEAD_MANAGER("Head Manager"),
    MANAGER("Manager"),
    INTERN("Intern"),
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    CONTRACT("Contract"),
    SENIOR("Senior");

    private final String label;

    ManagerType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static boolean contains(String input) {
        if (input == null || input.isBlank()) {
            return false;
        }
        return Arrays.stream(values())
                .anyMatch(value -> value.name().equalsIgnoreCase(input.trim())
                        || value.label.equalsIgnoreCase(input.trim()));
    }

    public static ManagerType from(String input) {
        if (input == null || input.isBlank()) {
            return MANAGER;
        }
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(input.trim())
                        || value.label.equalsIgnoreCase(input.trim()))
                .findFirst()
                .orElse(MANAGER);
    }

    public static void printValues() {
        for (ManagerType value : values()) {
            System.out.println("- " + value.label);
        }
    }
}
