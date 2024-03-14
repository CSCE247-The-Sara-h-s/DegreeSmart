package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private ArrayList<User> users;
    private HashMap<UUID, User> usersByUuid;
    private HashMap<String, User> usersByUsername;
    private static UserList userList;

    private UserList() {
        users = DataLoader.getUsers();
        usersByUuid = new HashMap<UUID, User>();
        usersByUsername = new HashMap<String, User>();

        for (User user : users) {
        	usersByUuid.put(user.getUuid(), user);
        	usersByUsername.put(user.getUsername(), user);
        }
    }

    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		
		return userList;
    }

    private UUID getNextUuid() {
		UUID uuid;
		do {
			uuid = UUID.randomUUID();
		} while (usersByUuid.containsKey(uuid));
		return uuid;
	}

    public ArrayList<User> getUsers() {
        return users;
    }

    private User getUser(UUID uuid) {
        return usersByUuid.get(uuid);
    }

    public User getUser(String username) {
        return usersByUsername.get(username);
    }

    public boolean createUser(User user) {
        if(!usersByUuid.containsKey(user.getUuid())){
			usersByUsername.put(user.getUsername() , user);
			usersByUuid.put(user.getUuid(), user);
			return true;
		} else{
			return false;
		}
    }


    public boolean deleteUser(User user) {
        if(users.remove(user)){
			usersByUuid.remove(user.getUuid());
			usersByUsername.remove(user.getUsername());
			return true;
		} else {
			return false;
		}
    }

    public boolean modifyUser(User modifiedUser) {
        User original = usersByUuid.get(modifiedUser.getUsername());

		if(original == null){
			return false;
		} else {
			usersByUuid.remove(original.getUuid());
			usersByUuid.put(modifiedUser.getUuid(), modifiedUser);
			usersByUsername.remove(original.getUsername());
			usersByUsername.put(modifiedUser.getUsername(), modifiedUser);
			return true;
		}
    }

}
