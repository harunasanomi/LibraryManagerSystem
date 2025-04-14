public class PrintedBook extends Book {
    private int pages;

    public PrintedBook(String title, String author, String genre, String isbn, boolean available, int dueDate, int pages) {
        super(title, author, genre, isbn, available, dueDate);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void displayBookDetails() {
        System.out.println("Printed Book: ");
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Genre: " + getGenre());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Pages: " + pages);
        System.out.println("Available: " + (isAvailable() ? "Yes" : "No"));
    }
}
