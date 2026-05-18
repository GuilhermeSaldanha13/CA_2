package CA_2;

import java.util.Arrays;

/**
 * MenuOption is an enumeration of all available menu options in the Department Store application.
 * Each option has a unique number and descriptive text displayed to the user.
 */
public enum MenuOption {
    LOAD_FILE(1, "Load Applicants File"),
    SORT(2, "Sort Applicant List"),
    SEARCH(3, "Search Employee"),
    ADD_RECORD(4, "Add Employee"),
    CREATE_TREE(5, "Create Employee Hierarchy"),
    DISPLAY_TREE_INFO(6, "Display Tree Info"),
    EXIT(7, "Exit");

    private final int number;
    private final String description;

    MenuOption(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromInput(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }
        try {
            int option = Integer.parseInt(input.trim());
            return Arrays.stream(values())
                    .filter(value -> value.number == option)
                    .findFirst()
                    .orElse(null);
        } catch (NumberFormatException ignore) {
            return Arrays.stream(values())
                    .filter(value -> value.name().equalsIgnoreCase(input.trim()))
                    .findFirst()
                    .orElse(null);
        }
    }
}
