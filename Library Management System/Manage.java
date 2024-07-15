package Medium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Manage {
    public static void registerUser(Users user) {
        String sql = "INSERT INTO users(name, email, password) VALUES(?, ?, ?)";

        try (Connection conn = DATABASE.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Users loginUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        Users user = null;

        try (Connection conn = DATABASE.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new Users(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public static List<Book> recommendBooks(Users user) {
        String sql = "SELECT * FROM books WHERE available = 1";
        List<Book> recommendedBooks = new ArrayList<>();

        try (Connection conn = DATABASE.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getInt("available") == 1);
                book.setId(rs.getInt("id"));
                recommendedBooks.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return recommendedBooks;
    }
}
