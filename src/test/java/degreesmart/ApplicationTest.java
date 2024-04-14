package degreesmart;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import degreesmart.model.*;

public class ApplicationTest {

    private Application application;
    private static ArrayList<User> initialState;

    @Before
    public void setUp() {
        application = Application.getInstance();
        initialState = new ArrayList<>(UserList.getInstance().getUsers());
        application.createAccount(Role.ADMINISTRATOR, "ADMIN", "password", "test@example.com", "DJ", "Khaled");
        application.createAccount(Role.ADMINISTRATOR, "ADMIN2", "password", "test1@example.com", "DJ", "Khaled");
        application.createAccount(Role.STUDENT, "STUDENT_1", "password", "test2@example.com", "Alexander", "Hamilton");
        application.createAccount(Role.STUDENT, "STUDENT_2", "password", "test3@example.com", "Arron", "Burr");
        application.createAccount(Role.STUDENT, "STUDENT_3", "password", "test4@example.com", "Sam", "Odell");
        application.createAccount(Role.STUDENT, "STUDENT_4", "password", "test5@example.com", "Micky", "Mouse");
        application.createAccount(Role.PARENT, "PARENT", "password", "test6@example.com", "Rhaenyra", "Targaryen");
        application.createAccount(Role.ADVISOR, "ADVISOR", "password", "test7@example.com", "Alicent", "Hightower");
        application.createAccount(Role.ADVISOR, "ADVISOR2", "password", "test8@example.com", "Captin", "Crunch");
    }

    @After
    public void tearDown() {
        application = null;
        UserList.getInstance().clear();
        for (User u : initialState) {
            UserList.getInstance().createUser(u);
        }
        UserList.getInstance().saveUsers();
    }


    @Test
    public void testActiveUser() {
        application.logIn("STUDENT_1", "password");
        System.out.println(application.getActiveUser());
        assertEquals("STUDENT_1", application.getActiveUser().getUsername());
    }
    @Test
    public void testCreateVaildStudentAccount() {
        application.logOut();
        assertTrue(application.createAccount(Role.STUDENT, "TESTSTUDENT3", "password", "test@example.com", "John", "Doe"));
        application.logIn("TESTSTUDENT3", "password");
        User userToDelete = application.getActiveUser();
        application.logOut();
        application.logIn("ADMINSSS", "password");
        System.out.println(application.getActiveUser());
        application.deleteAccount(userToDelete);
        application.logOut();
    }
//same username
    @Test
    public void testCreateinvaildStudentAccount1() {
        application.logOut();
        assertFalse(application.createAccount(Role.STUDENT, "Student", "password", "test@example.com", "John", "Doe"));
    }
//invalid username
    @Test
    public void testCreateinvaildStudentAccount2() {
        application.logOut();
        assertFalse(application.createAccount(Role.STUDENT, "test User", "password", "test@example.com", "John", "Doe"));
    }
// invalid passwrod
    @Test
    public void testCreateinvaildStudentAccount3() {
        application.logOut();
        assertFalse(application.createAccount(Role.STUDENT, "BadTest3", "pass word", "test@example.com", "John", "Doe"));
    }
//invalid email
    @Test
    public void testCreateinvaildStudentAccount4() {
        application.logOut();
        assertFalse(application.createAccount(Role.STUDENT, "BadTest4", "password", "testexample.com", "John", "Doe"));
    }
// invalid first name
    @Test
    public void testCreateinvaildStudentAccount5() {
        application.logOut();
        assertFalse(application.createAccount(Role.STUDENT, "BadTest5", "password", "test@example.com", "222", "Doe"));
    }
// invalid last name
    @Test
    public void testCreateinvaildStudentAccount6() {
        application.logOut();
        assertFalse(application.createAccount(Role.STUDENT, "BadTest6", "password", "test@example.com", "John", "222"));
    }
// valid advisor creation 
    @Test
    public void testCreateVaildAdvisorAccount() {
        application.logOut();
        assertTrue(application.createAccount(Role.ADVISOR, "Advisor", "password", "test@example.com", "John", "Doe"));
    }
//same username 
    @Test
    public void testCreateinvaildAdvisorAccount1() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADVISOR, "Advisor", "password", "test@example.com", "John", "Doe"));
    }
//invalid username
    @Test
    public void testCreateinvaildAdvisorAccount2() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADVISOR, "Advisor 2", "password", "test@example.com", "John", "Doe"));
    }
//invalid password
    @Test
    public void testCreateinvaildAdvisorAccount3() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADVISOR, "Advisor3", "pass word", "test@example.com", "John", "Doe"));
    }
//invalid email
    @Test
    public void testCreateinvaildAdvisorAccount4() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADVISOR, "Advisor4", "password", "testexample.com", "John", "Doe"));
    }
//invlad first name
    @Test
    public void testCreateinvaildAdvisorAccount5() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADVISOR, "Advisor5", "password", "test@example.com", "222", "Doe"));
    }
// invalid last name
    @Test
    public void testCreateinvaildAdvisorAccount6() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADVISOR, "Advisor6", "password", "test@example.com", "John", "!@#$%^&"));
    }

    @Test
    public void testCreateVaildAdminAccount() {
        application.logOut();
        assertTrue(application.createAccount(Role.ADMINISTRATOR, "AdminValid", "password", "test@example.com", "John", "Doe"));
    }
//repeat username
    @Test
    public void testCreateInvaildAdminAccount() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADMINISTRATOR, "Admin", "password", "test@example.com", "David", "Henry"));
    }
// invalid username
    @Test
    public void testCreateInvaildAdminAccount1() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADMINISTRATOR, "Admin 1", "password", "test@example.com", "Alex", "David"));
    }
// invalid password
    @Test
    public void testCreateInvaildAdimAccount2() {
        assertFalse(application.createAccount(Role.ADMINISTRATOR, "Admin2", "pass word", "test@example.com", "John", "Doe"));
    }
// invalid email
    @Ignore
    public void testCreateInvaildAdimAccount3() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADMINISTRATOR, "Admin3", "password", "testexample.com", "John", "Doe"));
    }
// invalid first name
    @Test
    public void testCreateInvaildAdimAccount4() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADMINISTRATOR, "admin4", "password", "test@example.com", "222", "Doe"));
    }
// invalid last name
    @Test
    public void testCreateInaildAdimAccount5() {
        application.logOut();
        assertFalse(application.createAccount(Role.ADMINISTRATOR, "Admin5", "password", "test@example.com", "Jack", "23456432"));
    }
    
    @Test
    public void testCreateVaildParentAccount() {
        application.logOut();
        assertTrue(application.createAccount(Role.PARENT, "Parent", "password", "test@example.com", "John", "Doe"));
    }
//repeat username
    @Test
    public void testCreateInvaildParentAccount() {
        application.logOut();
        assertFalse(application.createAccount(Role.PARENT, "Parent", "password", "test@example.com", "David", "Henry"));
    }
// invalid username
    @Test
    public void testCreateInaildParentAccount1() {
        application.logOut();
        assertFalse(application.createAccount(Role.PARENT, "Parent 1", "password", "test@example.com", "Alex", "David"));
    }
// invalid password
    @Test
    public void testCreateInvaildParentAccount2() {
        application.logOut();
        assertFalse(application.createAccount(Role.PARENT, "Parent2", "pass word", "test@example.com", "Mark", "Fake"));
    }
 // invalid email
    @Test
    public void testCreateInvaildParentAccount3() {
        application.logOut();
        assertFalse(application.createAccount(Role.PARENT, "Parent3", "password", "testexample.com", "John", "Doe"));
    }
// invalid first name
    @Test
    public void testCreateInvaildParentAccount4() {
        application.logOut();
        assertFalse(application.createAccount(Role.PARENT, "Parent4", "password", "test@example.com", "234664", "Doe"));
    }
// invalid last name
    @Test
    public void testCreateInaildParentAccount5() {
        application.logOut();
        assertFalse(application.createAccount(Role.PARENT, "Parent5", "password", "test@example.com", "Jack", "3436463"));
    }
    

    @Ignore
    public void testDeleteAccount() {
        application.logOut();
        application.logIn("STUDENT_1", "password");
        User userToDelete = application.getActiveUser();
        System.out.println(userToDelete);
        application.logOut();
        application.logIn("ADMIN", "password");
        application.deleteAccount(userToDelete);
        assertFalse(application.logIn("STUDENT_1", "password"));
        application.logOut();
    }

    @Test
    public void testNonAdminDeleteAccount() {
        application.logOut();
        application.logIn("PARENT", "password");
        User userToDelete = application.getActiveUser();
        application.logOut();
        System.out.println(userToDelete);
        application.logIn("STUDENT_1", "password");
        assertFalse(application.deleteAccount(userToDelete));
        application.logOut();
    }
    
    // test to see if the login works with correct info, and make sure it fails with inccorect info
    @Test
    public void testLogIn() {
        application.logOut();
        assertTrue(application.logIn("ADMIN", "password"));
        application.logOut();
    }
// different capitilatation in username
    @Test
    public void testInvalidLogIn() {
        application.logOut();
        assertFalse(application.logIn("ADmin", "password"));
    }

// different capitilatation in password
    @Test
    public void testInvalidLogIn1() {
        application.logOut();
        assertFalse(application.logIn("ADMIN", "PasswOrd"));
    }

// completely wrong username
    @Test
    public void testInvalidLogIn2() {
        application.logOut();
        assertFalse(application.logIn("tacobell", "password"));
    }

// completely wrong password
    @Test
    public void testInvalidLogIn3() {
        application.logOut();
        assertFalse(application.logIn("ADMIN", "tacobell"));
    }

    // test admin logout 
    @Test
    public void testLogOutADMIN() {
        application.logOut();
        application.logIn("ADMIN", "password");
        application.logOut();
        assertEquals(Role.GUEST, application.getActiveUser().getRole());
    }

    // test student logout 
    @Test
    public void testLogOutSTUDENT() {
        application.logOut();
        application.logIn("STUDENT_1", "password");
        application.logOut();
        assertEquals(Role.GUEST, application.getActiveUser().getRole());
    }

    // test parent logout 
    @Test
    public void testLogOutPARENT() {
        application.logOut();
        application.logIn("PARENT", "password");
        application.logOut();
        assertEquals(Role.GUEST, application.getActiveUser().getRole());
    }

    // test advisor logout 
    @Test
    public void testLogOutADVISOR() {
        application.logOut();
        application.logIn("ADVISOR", "password");
        application.logOut();
        assertEquals(Role.GUEST, application.getActiveUser().getRole());
    }

    @Test
    public void testChangeUsername() {
        application.logOut();
        application.logIn("STUDENT_2", "password");
        System.out.println(application.getActiveUser());
        application.changeUsername("newSTUDENT");
        assertEquals("newSTUDENT", application.getActiveUser().getUsername());
        application.logOut();
    }

// chaning to already existing username
    @Test
    public void testChangeUsername1() {
        application.logOut();
        application.logIn("ADMIN", "password");
        application.changeUsername("ADVISOR");
        assertNotEquals("ADVISOR", application.getActiveUser().getUsername());
        application.logOut();
    }

    @Test
    public void testChangeUscId() {
        application.logOut();
        application.logIn("STUDENT_2", "password");
        System.out.println(application.getActiveUser());
        application.changeUscId("103859648");
        application.logOut();
    }

// changing to an invalid USCID 
    @Test
    public void testChangeUscId1() {
        application.logOut();
        application.createAccount(Role.STUDENT, "ChangeUsc1", "password", "test@example.com", "John", "Doe");
        application.logIn("ChangeUsc1", "password");
        assertFalse(application.changeUscId("taco"));
        application.logOut();
    }

// changing to a vaild password
    @Test
    public void testChangePassword() {
        application.logOut();
        application.logIn("ADMIN", "password");
        assertTrue(application.changePassword("admin123"));
        application.logOut();
    }

// changing admin passwrod to invalid password
    @Test
    public void testInvaildChangePassword() {
        application.logOut();
        application.logIn("ADMIN", "admin123");
        assertFalse(application.changePassword("Invalid Password"));
        application.logOut();
    }

    @Test
    public void testChangeFirstName() {
        application.logOut();
        application.logIn("STUDENT_3", "password");
        assertTrue(application.changeFirstName("Lauren"));
        application.logOut();
    }

    @Test
    public void testInvalidChangeFirstName() {
        application.logOut();
        application.logIn("STUDENT_3", "password");
        System.out.println(application.getActiveUser());
        assertFalse(application.changeFirstName("12345"));
        

        application.logOut();
    }

    @Test
    public void testInvalidChangeFirstName1() {
        application.logOut();
        application.logIn("STUDENT_3", "password");
        assertFalse(application.changeFirstName("S*m"));
        application.logOut();
    }

    @Test
    public void testChangeLastName() {
        application.logOut();
        application.logIn("STUDENT_4", "password");
        assertTrue(application.changeLastName("Smith"));
    }

    @Test
    public void testInvalidChangeLastName() {
        application.logOut();
        application.logIn("STUDENT_4", "password");
        assertFalse(application.changeLastName("12345"));
    }

    @Test
    public void testChangePreferredName() {
        application.logIn("STUDENT_4", "password");
        assertTrue(application.changePreferredName("Johnny"));
    }

    @Test
    public void testInvalidChangePreferredName() {
        application.logIn("STUDENT_4", "password");
        assertFalse(application.changePreferredName("1234567"));
    }

    @Test
    public void testChangeEmailAddress() {
        application.logOut();
        application.logIn("ADMIN2", "password");
        assertTrue(application.changeEmailAddress("newemail@example.com"));
        application.logOut();
    }

    @Test
    public void testInvalidChangeEmailAddress() {
        application.logOut();
        application.logIn("ADMIN2", "password");
        assertFalse(application.changeEmailAddress("newemailexample.com"));
        application.logOut();
    }

    @Test
    public void testInvalidChangeEmailAddress1() {
        application.logOut();
        application.logIn("ADMIN2", "password");
        assertFalse(application.changeEmailAddress("test@example.com"));
        application.logOut();
    }

    //add a null case maybe 
    @Test
    public void testGetUsers() {
        application.logOut();
        application.logIn("ADMIN2", "password");
        assertNull(application.getUsers());
    }

    //add null case 
    @Test
    public void testGetStudents() {
        application.logOut();
        application.createAccount(Role.ADMINISTRATOR, "admin", "admin123", "admin@example.com", "Admin", "User");
        assertNotNull("Test case: Getting students as an administrator: Failed", application.getStudents());
    }

    //do the wrong username 
    @Test
    public void testGetStudentByUsername() {
        //Getting a student by username as an administrator
        application.createAccount(Role.ADMINISTRATOR, "admin", "admin123", "admin@example.com", "Admin", "User");
        application.createAccount(Role.STUDENT, "testUser", "password", "test@example.com", "John", "Doe");
        assertNotNull("Test case: Getting a student by username as an administrator: Failed", application.getStudentByUsername("testUser"));
    }

    //fix this test
     @Test
    public void testApproveAdvisor() {
        application.logIn("ADVISOR", "password");
        application.logOut();
        application.logIn("ADMIN2", "password");
    }

    //try to assign a admin, a non existing student and ask grace if we can assign a student that already has a advisor
    @Test
    public void testAssignStudent() {
        application.logOut();
        application.logIn("ADVISOR", "password");
        assertTrue(application.assignStudent(application.getStudentByUsername("STUDENT_1")));
        application.logOut();
    }

    //try to remove a nonexisting student
    @Test
    public void testRemoveAssignedStudent() {
        application.logIn("ADVISOR2", "password");
        application.assignStudent(application.getStudentByUsername("testStudent"));
        assertFalse(application.removeAssignedStuent(application.getStudentByUsername("STUDENT_1")));
    }

    //ask grace about this one, create a course and make sure that it is returning the correct stuff 
    @Test
    public void testGetCourses() {
        
        application.createAccount(Role.STUDENT, "student", "password", "student@example.com", "Student", "User");
        assertNotNull("Test case: Getting courses when user is logged in: Failed", application.getCourses());
    }

    @Test
    public void testGetRequirementSets() {
        application.createAccount(Role.STUDENT, "student", "password", "student@example.com", "Student", "User");
        assertNotNull("Test case: Getting requirement sets when user is logged in: Failed", application.getRequirementSets());
    }

    @Test
    public void testGetMajors() {
        application.createAccount(Role.STUDENT, "student", "password", "student@example.com", "Student", "User");
        assertNotNull("Test case: Getting majors when user is logged in: Failed", application.getMajors());
    }

    @Test
    public void testGetMinors() {
        application.createAccount(Role.STUDENT, "student", "password", "student@example.com", "Student", "User");
        assertNotNull(application.getMinors());
    }

    @Test
    public void testGetApplicationAreas() {
        application.createAccount(Role.STUDENT, "student", "password", "student@example.com", "Student", "User");
        assertNotNull(application.getApplicationAreas());
    }

    @Test
    public void testAddAdvisingNote() {
        application.logOut();
        application.logIn("ADVISOR2", "password");
        Student student = application.getStudentByUsername("STUDENT_1");
        System.out.println(student);
        assertNotNull("Test case: Retrieving student to add advising note: Failed", student);
        ArrayList<AdvisingNote> advisingNotes = application.addAdvisingNote(student, "Test advising note");
        assertNotNull("Test case: Adding advising note: Failed", advisingNotes);
        assertFalse("Test case: Adding advising note: Failed - Advising notes list is empty", advisingNotes.isEmpty());
        assertEquals("Test case: Adding advising note: Failed - Advising note message mismatch", "Test advising note", advisingNotes.get(0).getNote());
    }

}

