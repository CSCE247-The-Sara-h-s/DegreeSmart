package degreesmart;
import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private ArrayList<User> users;
    private HashMap<String, User> usersById;
    private HashMap<String, String> idsByUsername;
    private UserList userList;

    // FIGURE OUT
    private static UserList() {

    }

    public UserList getInstance() {
        return new UserList();
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<User>[];
    }

    private User getUser(String id) {
        return new User();
    }

    public User getUser(String username) {
        return new User();
    }

    public void createUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public void modifyUser(User modfieidUser) {

    }

}
