package UserDAO;

public class TestRead {

    public static void main(String[] args) {
        UserDAO testingRead = new UserDAO();
        User user2 = testingRead.read(1);
        System.out.println(user2.getId() + user2.getEmail() + user2.getUserName() + user2.getPassword());

        User userNull = testingRead.read(17);
        System.out.println(userNull.getUserName());

    }
}
