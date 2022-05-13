package UserDAO;

public class TestDelete {
    public static void main(String[] args) {
        User user1 = new User("Wiola", "Z@wp.pl", "superhaslo" );
        UserDAO test = new UserDAO();

       // test.create(user1);
        test.delete(3);
    }

}
