package UserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tests {

    public static void main(String[] args) {
        try(Connection conn = DbUtils.getConnection()){
            String query = "INSERT INTO Users (email, username, password) VALUES ('magic@gmail.com', 'MagicMike', 'haslo123')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
