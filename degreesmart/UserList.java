package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private ArrayList<User> users;
    private HashMap<UUID, User> usersById;
    private HashMap<String, UUID> uuidsByUsername;
    private static UserList userList;

    private UserList() {}

    public UserList getInstance() {
        return new UserList();
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<User>();
    }

    private User getUser(UUID uuid) {
        return users.get(0);
    }

    public User getUser(String username) {
        return users.get(0);
    }

    public void createUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public void modifyUser(User modifiedUser) {

    }

}
