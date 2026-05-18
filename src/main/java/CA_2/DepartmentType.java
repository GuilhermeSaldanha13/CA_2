package CA_2;

import java.util.Arrays;

/**
 * DepartmentType is an enumeration of the main departments in the organization.
 * It supports standardizing department names and validating department input.
 */
public enum DepartmentType {
    IT_DEVELOPMENT("IT Development"),
    SALES("Sales"),
    HR("HR"),
    FINANCE("Finance"),
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    ACCOUNTING("Accounting"),
    CUSTOMER_SERVICE("Customer Service"),
    TECHNICAL_SUPPORT("Technical Support"),
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    GROCERY("Grocery"),
    HOME_AND_GARDEN("Home and Garden"),
    OTHER("Other");

    private final String label;

    DepartmentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static DepartmentType from(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }
        String normalized = input.trim();
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(normalized) || value.label.equalsIgnoreCase(normalized))
                .findFirst()
                .orElse(null);
    }

    public static boolean contains(String input) {
        return from(input) != null;
    }

    public static void printValues() {
        for (DepartmentType value : values()) {
            if (value != OTHER) {
                System.out.println("- " + value.label);
            }
        }
    }
}
