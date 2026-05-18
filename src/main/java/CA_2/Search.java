package CA_2;

import java.util.List;

/**
 * Search implements a recursive Binary Search algorithm for finding employees by name.
 *
 * Binary Search is an efficient search algorithm that:
 * - Time Complexity: O(log n)
 * - Space Complexity: O(log n) due to recursive call stack
 * - Requirement: Input list MUST be sorted by employee name
 */
public class Search {

    public static Employee search(List<Employee> sortedEmployees, String term) {
        if (sortedEmployees == null || sortedEmployees.isEmpty() || term == null || term.isBlank()) {
            return null;
        }
        String normalizedTerm = normalizeSearchKey(term);
        return binarySearch(sortedEmployees, normalizedTerm, 0, sortedEmployees.size() - 1);
    }

    private static Employee binarySearch(List<Employee> list, String normalizedTerm, int left, int right) {
        if (left > right) {
            return null;
        }

        int midpoint = left + (right - left) / 2;
        Employee current = list.get(midpoint);
        int comparison = current.getSortKey().compareToIgnoreCase(normalizedTerm);

        if (comparison == 0) {
            return current;
        }
        if (comparison < 0) {
            return binarySearch(list, normalizedTerm, midpoint + 1, right);
        }
        return binarySearch(list, normalizedTerm, left, midpoint - 1);
    }

    private static String normalizeSearchKey(String term) {
        String[] parts = term.trim().split("\\s+");
        if (parts.length == 0) {
            return "";
        }
        if (parts.length == 1) {
            return parts[0].toLowerCase();
        }
        String lastName = parts[parts.length - 1];
        String firstName = String.join(" ", java.util.Arrays.copyOf(parts, parts.length - 1));
        return (lastName + " " + firstName).toLowerCase();
    }
}

