public class Ebook extends Book {
    private String fileFormat;

    public Ebook(String title, String author, String genre, String isbn, boolean available, int dueDate, String fileFormat) {
        super(title, author, genre, isbn, available, dueDate);
        this.fileFormat = fileFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void displayBookDetails() {
        System.out.println("Ebook: ");
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Genre: " + getGenre());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("File Format: " + fileFormat);
        System.out.println("Available: " + (isAvailable() ? "Yes" : "No"));
    }
}
