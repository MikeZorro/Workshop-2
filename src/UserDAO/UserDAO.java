package UserDAO;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.sql.PreparedStatement;

public class UserDAO {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }




}
