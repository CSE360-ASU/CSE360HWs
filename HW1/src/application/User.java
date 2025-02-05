package application;

import java.util.Set;  // Import the Set and HashSet for user roles
import java.util.HashSet;  

/**
 * The User class represents a user entity in the system.
 * It contains the user's details such as userName, password, and roles.
 */
public class User {
    private String userName;
    private String password;
    private Set<String> roles; // user roles are a Set, to accommodate multiple roles

    // Constructor to initialize a new User object
    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.roles = new HashSet<>();
        if (role != null && !role.isEmpty()) {
        this.addRole(role); // allows for the creation of new users with default "user" role
        }
    }

    // Adds a role to the user's roles.
    public void addRole(String role) {
        this.roles.add(role);
    }

    // Removes a role from the user's roles.
    public void removeRole(String role) {
        this.roles.remove(role);
    }

    public Set<String> getRole() { return roles; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
}