package degreesmart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;
class UserListTest {
    private ArrayList<Course> courses = DataLoader.getCourses();
//    private ArrayList<Course> courses = new ArrayList<>();      // do I pull these from the data loader? or call their constructors?
    private ArrayList<RequirementSet> requirementSets = DataLoader.getRequirementSets(courses);
//    private ArrayList<RequirementSet> requirementSets = new ArrayList<>();
    private UserList userList = UserList.getInstance(courses, requirementSets);
    private ArrayList<User> users = DataLoader.getUsers(courses, requirementSets);
//    private ArrayList<User> users = userList.getUsers();

    @BeforeEach
    public void setup() {
        users.clear();

        // cannot instantiate the type User
        users.add(new Administrator(UUID.randomUUID(), "Ejohnson", "lemonFlower123", "ejohnson@email.sc.edu", "Emily", "Johnson"));
        users.add(new Advisor(UUID.randomUUID(), "Bsmith", "purplePenguin21", "bsmith@email.sc.edu", "Brian", "Smith"));
        users.add(new Student(UUID.randomUUID(), "Ereynolds", "password101", "ereynolds@email.sc.edu", "Ethan", "Reynolds"));
        users.add(new Parent(UUID.randomUUID(), "Ocarter", "qwerty123", "ocarter@gmail.com", "Olivia", "Carter"));
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
        User userObject = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
        boolean validUser = userList.createUser(userObject);
        assertTrue(validUser);
    }

    @Test
    void testCreateUserNullUser() {
        boolean nullUser = userList.createUser(null);
        assertFalse(nullUser);
    }

    @Test
    void testCreateUserContainsUser() {
        User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
        users.add(userObject);
        boolean containsUser = userList.createUser(userObject);
        assertTrue(containsUser);   // should this be true or false?
    }

    @Test
    void testDeleteUserValidUser() {
        User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
        users.add(userObject);
        boolean validUser = userList.deleteUser(userObject);
        assertTrue(validUser);
    }

    @Test
    void testDeleteUserNullUser() {
        boolean nullUser = userList.deleteUser(null);
        assertFalse(nullUser);
    }

    @Test
    void testChangeUsernameValidUser() {
        User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
        users.add(userObject);
        boolean validUser = userList.changeUsername(userObject, "JuliaDavis");
        assertTrue(validUser);
    }

    @Test
    void testChangeUsernameContainsUser() {
        User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
        users.add(userObject);
        boolean containsUser = userList.changeUsername(userObject, "LiamRoberts");
        assertTrue(containsUser);
    }

    @Test
    void testChangeUsernameRepeatUsername() {
        User lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
        User jdavis = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
        users.add(lthomson);
        users.add(jdavis);
        boolean repreatUsername = userList.changeUsername(lthomson, "jdavis");
        assertFalse(repreatUsername);
    }

    @Test
    void testChangeUsernameNullUser() {
        boolean nullUser = userList.changeUscId(null, "username");
        assertFalse(nullUser);
    }

    @Test
    void testChangeUsernameNullUsername() {
        User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
        users.add(userObject);
        boolean nullUsername = userList.changeUsername(userObject, null);
        assertFalse(nullUsername);
    }

    @Test
    void testChangeUsernameEmptyUsername() {
        User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
        users.add(userObject);
        boolean emptyUsername = userList.changeUsername(userObject, "");
        assertFalse(emptyUsername);
    }

    @Test
    void changeUscIdValidUser() {
        Student jdavis = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
        users.add(jdavis);
        boolean validUser = userList.changeUscId(jdavis, "T12345678");
        assertTrue(validUser);
    }

    @Test
    void changeUscIdContainsUser() {
        Student lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
        users.add(lthomson);
        boolean containsUser = userList.changeUscId(lthomson, "A12345678");
        assertTrue(containsUser);
    }
}