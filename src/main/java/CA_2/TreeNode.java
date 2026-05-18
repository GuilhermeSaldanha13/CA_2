package CA_2;

/**
 * TreeNode represents a single node in a binary tree structure.
 * Each node contains an Employee and references to its left and right children.
 * 
 * This class is used as the building block for the BinaryTree class,
 * enabling level-order insertion and traversal of employees in a hierarchical structure.
 * 
 * Attributes:
 * - data: The Employee object stored in this node
 * - left: Reference to the left child node (null if no left child)
 * - right: Reference to the right child node (null if no right child)
 */
public class TreeNode {
    private final Employee data;
    TreeNode left;    // Left child node
    TreeNode right;   // Right child node

    /**
     * Constructs a TreeNode with the specified Employee data.
     * The left and right child references are initialized as null.
     *
     * @param data The Employee object to store in this node
     */
    public TreeNode(Employee data) {
        this.data = data;
    }

    /**
     * Gets the Employee data stored in this node.
     * @return The Employee object
     */
    public Employee getData() {
        return data;
    }
}

