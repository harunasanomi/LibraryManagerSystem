import java.io.*;
import java.util.ArrayList;


public class DataManager {

    // Ghi danh sách người dùng vào tệp
    public void writeUsers(ArrayList<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt"))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc danh sách người dùng từ tệp
    public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                User user = null;
                switch (data[2].toLowerCase()) {
                    case "admin":
                        user = new Admin(data[0], data[1]);
                        break;
                    case "librarian":
                        user = new Librarian(data[0], data[1]);
                        break;
                    case "reader":
                        user = new Reader(data[0], data[1]);
                        break;
                }
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Ghi danh sách sách vào tệp
    public void writeBooks(ArrayList<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/books.txt"))) {
            for (Book book : books) {
                String bookData = book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + ","
                        + book.getIsbn() + "," + book.isAvailable() + "," + book.getDueDate();
                if (book instanceof PrintedBook) {
                    bookData += "," + ((PrintedBook) book).getPages();
                } else if (book instanceof Ebook) {
                    bookData += "," + ((Ebook) book).getFileFormat();
                }
                writer.write(bookData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc danh sách sách từ tệp
    public ArrayList<Book> readBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Book book = null;
                if (data.length == 6) {
                    book = new PrintedBook(data[0], data[1], data[2], data[3], Boolean.parseBoolean(data[4]),
                            Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                } else if (data.length == 7) {
                    book = new Ebook(data[0], data[1], data[2], data[3], Boolean.parseBoolean(data[4]),
                            Integer.parseInt(data[5]), data[6]);
                }
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
    // Ghi một giao dịch vào transactions.txt
    public void writeTransaction(String username, String bookTitle, String action, String date) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/transactions.txt", true))) {
            String line = username + "," + bookTitle + "," + action + "," + date;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc danh sách các giao dịch
    public ArrayList<String> readTransactions() {
        ArrayList<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
