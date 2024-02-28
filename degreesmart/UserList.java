package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private ArrayList<User> users;
    private HashMap<UUID, User> usersById;
    private HashMap<String, UUID> uuidsByUsername;
    private static UserList userList;

    private UserList() {
        users = DataLoader.getUsers();
    }

    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		
		return userList;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private User getUser(UUID uuid) {
        return users.get(0);
    }

    public User getUser(String username) {
        return users.get(0);
    }

    public void createUser(User user) {

    }

    //just kinda messing around with this...it might be wrong but hopefully gives us good starting point
    public void deleteUser(User user) {
        UUID uuid = uuidsByUsername.get(user.getUsername());
        usersById.remove(uuid);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.remove(i);
                break;
            }
        }
        uuidsByUsername.remove(user.getUsername());
    }

    public void modifyUser(User modifiedUser) {

    }

}
