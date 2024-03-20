package degreesmart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
class UserListTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();

    @BeforeEach
    public void setup() {
        users.clear();

        // cannot instantiate the type User
//        users.add(new User("12345", "Ejohnson", "lemonFlower123", "ejohnson@email.sc.edu", "Emily", "Johnson"));
        userList.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
        userList.saveUsers();
    }

    @Test
    void testCreateUserValidUser() {
        boolean validUser = userList.createUser(null);  // fix what's in parameter
        assertTrue(validUser);
    }

    @Test
    void testCreateUserNullUser() {
        boolean nullUser = userList.createUser(null);
        assertFalse(nullUser);
    }

    @Test
    void testCreateUserContainsUser() {
        boolean containsUser = userList.createUser(null);
        assertFalse(containsUser);
    }

    @Test
    void testDeleteUserNullUser() {
        boolean nullUser = userList.deleteUser(null);
        assertFalse(nullUser);
    }
}