public abstract class Book {
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private boolean available;
    private int dueDate;

    public Book(String title, String author, String genre, String isbn, boolean available, int dueDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.available = available;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    // Phương thức trừu tượng cho in thông tin sách (sẽ được định nghĩa lại trong các lớp con)
    public abstract void displayBookDetails();
}
