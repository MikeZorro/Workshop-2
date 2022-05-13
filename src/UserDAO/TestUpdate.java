package UserDAO;

public class TestUpdate {

    public static void main(String[] args) {
    User userUpdated = new User("MagicMike", "magic2@gmail.com", "haslo123" );
    userUpdated.setId(1);
    UserDAO updateDAO = new UserDAO();
    updateDAO.update(userUpdated);
    }
}
