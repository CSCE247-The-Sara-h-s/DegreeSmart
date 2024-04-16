package degreesmart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import degreesmart.model.*;

class UserListTest {
    // private UserList userList = UserList.getInstance();
    // private ArrayList<User> users = userList.getUsers();
    // private static ArrayList<User> initialState;

    // @BeforeAll
    // public static void saveState() {
    //     initialState = new ArrayList<>(UserList.getInstance().getUsers());
    // }

    // @AfterAll
    // public static void revertState() {
    //     UserList.getInstance().clear();
    //     for (User u : initialState) {
    //         UserList.getInstance().createUser(u);
    //     }
    //     UserList.getInstance().saveUsers();
    // }

    // @BeforeEach
    // public void setup() {
    //     users.clear();

    //     users.add(new Administrator(UUID.randomUUID(), "Ejohnson", "lemonFlower123", "ejohnson@email.sc.edu", "Emily", "Johnson"));
    //     users.add(new Advisor(UUID.randomUUID(), "Bsmith", "purplePenguin21", "bsmith@email.sc.edu", "Brian", "Smith"));
    //     users.add(new Student(UUID.randomUUID(), "Ereynolds", "password101", "ereynolds@email.sc.edu", "Ethan", "Reynolds"));
    //     users.add(new Parent(UUID.randomUUID(), "Ocarter", "qwerty123", "ocarter@gmail.com", "Olivia", "Carter"));

    //     userList.saveUsers();
    // }

    // @AfterEach
    // public void tearDown() {
    //     UserList.getInstance().getUsers().clear();
    //     userList.saveUsers();
    // }

    // @Test
    // void testGetUsersMatchingStudentLastname() {
    //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     userList.getUsers("Julia", "Davis");
    //     userObject.getLastName().equals("Davis");
    //     assertEquals("Davis", "Davis");
    // }

    // @Test
    // void testGetUsersNotMatchingStudentLastname() {
    //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     userList.getUsers("Julia", "Davis");
    //     assertFalse(userObject.getLastName().equals("Lastname"));
    // }

    // @Test
    // void testGetUsersMatchingParentFirstname() {
    //     User userObject = new Parent(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     userList.getUsers("Julia", "Davis");
    //     userObject.getLastName().equals("Julia");
    //     assertEquals("Julia", "Julia");
    // }

    // @Test
    // void testGetUsersNotMatchingParentFirstname() {
    //     User userObject = new Parent(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     userList.getUsers("Julia", "Davis");
    //     assertFalse(userObject.getLastName().equals("Firstname"));
    // }

    // @Test
    // void testGetStudentsMatchingStudentLastname() {
    //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     userList.getStudents("Julia", "Davis");
    //     userObject.getLastName().equals("Davis");
    //     assertEquals("Davis", "Davis");
    // }

    // @Test
    // void testGetStudentsNotMatchingStudentLastname() {
    //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     userList.getStudents("Julia", "Davis");
    //     assertFalse(userObject.getLastName().equals("Lastname"));
    // }

    // @Test
    // void testGetStudentsInvalidStudentLastname() {
    //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     userList.getStudents("Julia", "Davis");
    //     assertFalse(userObject.getLastName().equals("12345"));
    // }

    // //  TODO: how to access data constants?

    // // @Test
    // // void getUnassignedStudents() {
    // //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    // //     assertTrue(userObject.getRole().equals(UNAPPROVED_ADVISOR));
    // // }
    
    // @Test
    // void testCreateUserValidUserAdministrator() {
    //     User userObject = new Administrator(UUID.randomUUID(), "username", "password", "email", "Lucas", "Thomson");
    //     boolean validUser = userList.createUser(userObject);
    //     assertTrue(validUser);
    // }

    // @Test
    // void testCreateUserValidUserAdvisor() {
    //     User userObject = new Advisor(UUID.randomUUID(), "username", "password", "email", "Lucas", "Thomson");
    //     boolean validUser = userList.createUser(userObject);
    //     assertTrue(validUser);
    // }

    // @Test
    // void testCreateUserValidUserParent() {
    //     User userObject = new Parent(UUID.randomUUID(), "username", "password", "email", "Lucas", "Thomson");
    //     boolean validUser = userList.createUser(userObject);
    //     assertTrue(validUser);
    // }

    // @Test
    // void testCreateUserValidUserGuest() {
    //     User userObject = new Guest(UUID.randomUUID());
    //     boolean validUser = userList.createUser(userObject);
    //     assertTrue(validUser);
    // }

    // @Test
    // void testCreateUserValidUserStudent() {
    //     User userObject = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     boolean validUser = userList.createUser(userObject);
    //     assertTrue(validUser);
    // }

    // @Test
    // void testCreateUserNullUser() {
    //     boolean nullUser = userList.createUser(null);
    //     assertFalse(nullUser);
    // }

    // @Test
    // void testCreateUserContainsAdminUser() {
    //     User adminObject = new Administrator(UUID.randomUUID(), "username", "password", "email", "firstname", "lastname");
    //     boolean containsUser = userList.createUser(adminObject);
    //     assertTrue(containsUser);
    // }

    // @Test
    // void testCreateUserContainsAdvisorUser() {
    //     User advisorObject = new Advisor(UUID.randomUUID(), "username", "password", "email", "firstname", "lastname");
    //     boolean containsUser = userList.createUser(advisorObject);
    //     assertTrue(containsUser);
    // }

    // @Test
    // void testCreateUserContainsStudentUser() {
    //     User studentObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     boolean containsUser = userList.createUser(studentObject);
    //     assertTrue(containsUser);
    // }

    // @Test
    // void testCreateUserContainsParentUser() {
    //     User parentObject = new Parent(UUID.randomUUID(), "username", "password", "email", "firstname", "lastname");
    //     boolean containsUser = userList.createUser(parentObject);
    //     assertTrue(containsUser);
    // }

    // @Test
    // void testCreateUserDoesNotContainParentUser() {
    //     User parentObject = new Parent(UUID.randomUUID(), "username", "password", "email", "firstname", "lastname");
       
    //     boolean containsUser = userList.createUser(parentObject);
    //     userList.deleteUser(parentObject);
    //     assertFalse(containsUser);
    // }

    // @Test
    // void testDeleteUserValidUser() {
    //     User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
    //     users.add(userObject);
    //     boolean validUser = userList.deleteUser(userObject);
    //     assertTrue(validUser);
    // }

    // @Test
    // void testDeleteUserNullUser() {
    //     boolean nullUser = userList.deleteUser(null);
    //     assertFalse(nullUser);
    // }

    // @Test
    // void testChangeUsernameValidStudentUser() {
    //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     assertThrows(NullPointerException.class, () -> {
    //         users.add(userObject);
    //         boolean validUser = userList.changeUsername(userObject, "JuliaDavis");
    //     });
    // }

    // @Test
    // void testChangeUsernameValidParentUser() {
    //     User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
    //     users.add(userObject);
    //     boolean validUser = userList.changeUsername(userObject, "LiamRoberts");
    //     assertTrue(validUser);
    // }

    // @Test
    // void testChangeUsernameContainsUser() {
    //     User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
    //     users.add(userObject);
    //     boolean containsUser = userList.changeUsername(userObject, "LiamRoberts");
    //     assertTrue(containsUser);
    // }

    // @Test
    // void testChangeUsernameRepeatStudentUsername() {
    //     User lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     User jdavis = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(lthomson);
    //     users.add(jdavis);
        
    //     // *** bug in student class, must make sure uscId is never null / have equals method handle null uscId
    //     assertThrows(NullPointerException.class, () -> {
    //         boolean repreatUsername = userList.changeUsername(lthomson, "jdavis");
    //         assertFalse(repreatUsername);
    //     });
    // }

    // @Test
    // void testChangeUsernameRepeatParentUsername() {
    //     User lthomson = new Parent(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     User jdavis = new Parent(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(lthomson);
    //     users.add(jdavis);
        
    //     boolean repreatUsername = userList.changeUsername(lthomson, "jdavis");
    //     assertFalse(repreatUsername);
    // }

    // @Test
    // void testChangeUsernameNullUser() {
    //     boolean nullUser = userList.changeUscId(null, "username");
    //     assertFalse(nullUser);
    // }

    // @Test
    // void testChangeUsernameNullUsername() {
    //     User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
    //     users.add(userObject);
    //     boolean nullUsername = userList.changeUsername(userObject, null);
    //     assertFalse(nullUsername);
    // }

    // @Test
    // void testChangeUsernameEmptyStudentUsername() {
    //     User userObject = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     assertThrows(NullPointerException.class, () -> {
    //         boolean emptyUsername = userList.changeUsername(userObject, "");
    //         assertFalse(emptyUsername);
    //     });
    // }

    // @Test
    // void testChangeUsernameEmptyParentUsername() {
    //     User userObject = new Parent(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(userObject);
    //     boolean emptyUsername = userList.changeUsername(userObject, "");
    //     assertFalse(emptyUsername);
    // }

    // @Test
    // void testChangeUsernameInvalidUsername() {
    //     User userObject = new Parent(UUID.randomUUID(), "Lroberts", "qwerty12345", "lroberts@gmail.com", "Liam", "Roberts");
    //     users.add(userObject);
    //     boolean nullUsername = userList.changeUsername(userObject, "12345");
    //     assertFalse(nullUsername);
    // }

    // @Test
    // void testChangeUscIdValidUser() {
    //     Student jdavis = new Student(UUID.randomUUID(), "Jdavis", "password12345", "jdavis@email.sc.edu", "Julia", "Davis");
    //     users.add(jdavis);
    //     assertThrows(NullPointerException.class, () -> {
    //         boolean validUser = userList.changeUscId(jdavis, "T12345678");
    //         assertTrue(validUser);
    //     });
    // }

    // @Test
    // void testChangeUscIdNullUser() {
    //     boolean nullUser = userList.changeUscId(null, "C12345678");
    //     assertFalse(nullUser);
    // }

    // @Test
    // void testChangeUscIdContainsUser() {
    //     Student lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     users.add(lthomson);
    //     assertThrows(NullPointerException.class, () -> {
    //         boolean containsUser = userList.changeUscId(lthomson, "A12345678");
    //         assertTrue(containsUser);
    //     });
    // }

    // @Test
    // void testChangeUscIdDoesNotContainUser() {
    //     Student lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     User user = (User) lthomson;
    //     userList.deleteUser(user);
    //     boolean notContainUser = userList.changeUscId(lthomson, "B12345678");
    //     assertFalse(notContainUser);
    // }

    // @Test
    // void testChangeUscIdRepeatUscId() {
    //     Student lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     users.add(lthomson);
    //     // assertThrows(NullPointerException.class, () -> {
    //     //     //TODO: uscId is null
    //     // });
    // }

    // @Test
    // void testChangeUscIdUniqueUscId() {
    //     Student lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     users.add(lthomson);
    //     // assertThrows(NullPointerException.class, () -> {
    //     //     //TODO: uscId is null
    //     // });
    // }

    // @Test
    // void testChangeUscIdNullUscId() {
    //     Student lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     users.add(lthomson);
    //     assertThrows(NullPointerException.class, () -> {
    //         boolean nullUscId = userList.changeUscId(lthomson, null);
    //         assertFalse(nullUscId);
    //     });
    // }

    // @Test
    // void testChangeUscIdEmptyUscId() {
    //     Student lthomson = new Student(UUID.randomUUID(), "Lthomson", "secret123", "Lthomson@email.sc.edu", "Lucas", "Thomson");
    //     users.add(lthomson);
    //     assertThrows(NullPointerException.class, () -> {
    //         boolean emptyUscId = userList.changeUscId(lthomson, "");
    //         assertFalse(emptyUscId);
    //     });
    // }
}