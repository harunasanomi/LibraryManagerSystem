
public abstract class User {
    String username;
    String password;
    String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Phương thức đăng nhập
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
