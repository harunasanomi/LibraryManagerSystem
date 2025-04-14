
import java.util.ArrayList;
import java.util.Scanner;



public class Librarian extends User {

    public Librarian(String username, String password) {
        super(username, password, "Librarian");
    }

    public void showLibrarianMenu(Scanner scanner, ArrayList<Book> books, DataManager dataManager) {
        int choice;
        do {
            System.out.println("\n===== LIBRARIAN MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. List Books");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    addBook(scanner, books, dataManager);
                    break;
                case 2:
                    removeBook(scanner, books, dataManager);
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

    public void addBook(Scanner scanner, ArrayList<Book> books, DataManager dataManager) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter book type (PrintedBook or Ebook): ");
        String type = scanner.nextLine();

        Book book;
        if ("printedbook".equalsIgnoreCase(type)) {
            System.out.print("Enter number of pages: ");
            int pages = scanner.nextInt();
            book = new PrintedBook(title, author, genre, isbn, true, 0, pages);
        } else if ("ebook".equalsIgnoreCase(type)) {
            System.out.print("Enter file format: ");
            String format = scanner.nextLine();
            book = new Ebook(title, author, genre, isbn, false, 0, format);
        } else {
            System.out.println("Invalid book type.");
            return;
        }

        books.add(book);
        dataManager.writeBooks(books);
        System.out.println("Book added successfully.");
    }

    public void removeBook(Scanner scanner, ArrayList<Book> books, DataManager dataManager) {
        System.out.print("Enter ISBN to remove: ");
        String isbn = scanner.nextLine();

        Book toRemove = null;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                toRemove = b;
                break;
            }
        }

        if (toRemove != null) {
            books.remove(toRemove);
            dataManager.writeBooks(books);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void listBooks(ArrayList<Book> books) {
        System.out.println("=== BOOK LIST ===");
        for (Book b : books) {
            System.out.println("- " + b.getTitle() + " (" + b.getIsbn() + ")");
        }
    }
}
