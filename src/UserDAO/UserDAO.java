package UserDAO;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.sql.PreparedStatement;

public class UserDAO extends User {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id =(?)";

    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? where id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM users where id = ?";

    private static final String READALL_QUERY =
            "SELECT * FROM users";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        User user = new User();
        try (Connection conn = DbUtils.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(READ_USER_QUERY.replace("(?)", String.valueOf(userId)));
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("username"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = DbUtils.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER_QUERY);
            preparedStatement.setInt(4, user.getId());
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, this.hashPassword(user.getPassword()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtils.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        User [] result = new User[0];
        try (Connection conn = DbUtils.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(READALL_QUERY);
            while ( resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                result = DbUtils.addToArray(user, result);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}


