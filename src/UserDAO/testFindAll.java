package UserDAO;

import java.util.Arrays;

public class testFindAll {

    public static void main(String[] args) {
        UserDAO test = new UserDAO();
        System.out.println(Arrays.toString(test.findAll()));
    }
}
