import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = new DataManager();
        
        // Đọc danh sách người dùng và sách từ tệp
        ArrayList<User> users = dataManager.readUsers();
        ArrayList<Book> books = dataManager.readBooks();

        System.out.println("===== WELCOME TO LIBRARY MANAGEMENT SYSTEM =====");

        // Nhập tên người dùng và mật khẩu để đăng nhập
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Kiểm tra đăng nhập
        User currentUser = null;
        for (User user : users) {
            if (user.login(username, password)) {
                currentUser = user;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        System.out.println("Login successful! Welcome, " + currentUser.getUsername());

        // Quản lý chức năng của Admin, Librarian, Reader
        if (currentUser instanceof Admin) {
            Admin admin = (Admin) currentUser;
            admin.showAdminMenu(scanner, users, dataManager);
        } else if (currentUser instanceof Librarian) {
            Librarian librarian = (Librarian) currentUser;
            librarian.showLibrarianMenu(scanner, books, dataManager);
        } else if (currentUser instanceof Reader) {
            Reader reader = (Reader) currentUser;
            reader.showReaderMenu(scanner, books, dataManager);
        }

        // Đóng Scanner khi kết thúc
        scanner.close();
    }
}
