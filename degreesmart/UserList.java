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
        ArrayList<User> users = new ArrayList<User>();
        HashMap<UUID, User> userbyId = new HashMap<UUID, User>();
        HashMap<String, UUID> uuidsByUsername = new HashMap<String, UUID>();
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
        return usersById.get(uuid);
    }

    public User getUser(String username) {
        return getUser(uuidsByUsername.get(username));
    }

    public boolean createUser(User user) {
        if(!usersById.containsKey(user.getUuid())){
			uuidsByUsername.put(user.getUsername() , user.getUuid());
			usersById.put(user.getUuid(), user);
			return true;
		} else{
			return false;
		}
    }


    public boolean deleteUser(User user) {
        if(users.remove(user)){
			usersById.remove(user.getUsername());
			uuidsByUsername.remove(user.getUuid());
			return true;
		} else {
			return false;
		}
    }

    public boolean modifyUser(User modifiedUser) {
        User original = usersById.get(modifiedUser.getUsername());

		if(original == null){
			return false;
		} else {
			usersById.remove(original.getUuid());
			usersById.put(modifiedUser.getUuid(), modifiedUser);
			uuidsByUsername.remove(original.getUsername());
			uuidsByUsername.put(modifiedUser.getUsername(), modifiedUser.getUuid());
			return true;
		}
    }

}
