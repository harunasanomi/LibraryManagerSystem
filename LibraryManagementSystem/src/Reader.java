
import java.util.ArrayList;
import java.util.Scanner;


public class Reader extends User {

    public Reader(String username, String password) {
        super(username, password, "Reader");
    }

    public void showReaderMenu(Scanner scanner, ArrayList<Book> books, DataManager dataManager) {
        int choice;
        do {
            System.out.println("\n===== READER MENU =====");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. List Books");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    borrowBook(scanner, books, dataManager);
                    break;
                case 2:
                    returnBook(scanner, books, dataManager);
                    break;
                case 3:
                    listBooks(books);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public void borrowBook(Scanner scanner, ArrayList<Book> books, DataManager dataManager) {
        System.out.print("Enter ISBN to borrow: ");
        String isbn = scanner.nextLine();

        Book toBorrow = null;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn) && b.isAvailable()) {
                toBorrow = b;
                break;
            }
        }

        if (toBorrow != null) {
            toBorrow.setAvailable(false);  // Mark the book as borrowed
            dataManager.writeBooks(books);
            System.out.println("Book borrowed successfully.");
            dataManager.writeTransaction(this.getUsername(), toBorrow.getTitle(), "borrowed", java.time.LocalDate.now().toString());
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Scanner scanner, ArrayList<Book> books, DataManager dataManager) {
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();

        Book toReturn = null;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn) && !b.isAvailable()) {
                toReturn = b;
                break;
            }
        }

        if (toReturn != null) {
            toReturn.setAvailable(true);  // Mark the book as returned
            dataManager.writeBooks(books);
            System.out.println("Book returned successfully.");
            dataManager.writeTransaction(this.getUsername(), toReturn.getTitle(), "returned", java.time.LocalDate.now().toString());
        } else {
            System.out.println("Book not found or not borrowed.");
        }
    }

    public void listBooks(ArrayList<Book> books) {
        System.out.println("=== BOOK LIST ===");
        for (Book b : books) {
            System.out.println("- " + b.getTitle() + " (" + b.getIsbn() + ")");
        }
    }
}
