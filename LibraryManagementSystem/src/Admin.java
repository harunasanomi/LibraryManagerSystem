
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    public void showAdminMenu(Scanner scanner, ArrayList<User> users, DataManager dataManager) {
        int choice;
        do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. List Users");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    addUser(scanner, users, dataManager);
                    break;
                case 2:
                    removeUser(scanner, users, dataManager);
                    break;
                case 3:
                    listUsers(users);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public void addUser(Scanner scanner, ArrayList<User> users, DataManager dataManager) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Admin, Librarian, Reader): ");
        String role = scanner.nextLine();

        User newUser;
        switch (role.toLowerCase()) {
            case "admin":
                newUser = new Admin(username, password);
                break;
            case "librarian":
                newUser = new Librarian(username, password);
                break;
            case "reader":
                newUser = new Reader(username, password);
                break;
            default:
                System.out.println("Invalid role.");
                return;
        }

        users.add(newUser);
        dataManager.writeUsers(users); // Lưu vào file
        System.out.println("User added successfully.");
    }

    public void removeUser(Scanner scanner, ArrayList<User> users, DataManager dataManager) {
        System.out.print("Enter username to remove: ");
        String username = scanner.nextLine();

        User toRemove = null;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                toRemove = u;
                break;
            }
        }

        if (toRemove != null) {
            users.remove(toRemove);
            dataManager.writeUsers(users); // Ghi lại danh sách mới
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void listUsers(ArrayList<User> users) {
        System.out.println("=== USER LIST ===");
        for (User u : users) {
            System.out.println("- " + u.getUsername() + " (" + u.getRole() + ")");
        }
    }
}
