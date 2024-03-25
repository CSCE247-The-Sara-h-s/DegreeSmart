package degreesmart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

class CourseTest {

    private Course course;
    private ArrayList<Course> courses;
    
    @BeforeEach
    public void setup() {
        courses = DataLoader.getCourses();

        Random rand = new Random();
        int index = rand.nextInt(5000);
        course = courses.get(index);
    }

    @AfterEach
    public void tearDown() {
        courses.clear();
    }

    @Test
    void testCreateValidCourse() {
        Course courseNew = new Course(UUID.randomUUID(), Subject.CSCE, "220");
        assertNotNull(courseNew);
    }

    @Test
    void testCreateInvalidCourse() {
        Course courseNew = new Course(UUID.randomUUID(), Subject.MUSC, "-1895");
        assertNull(courseNew);
    }

    @Test
    void testModifyCourse() {
        String original = course.toString();

        course.setSubject(Subject.AERO);
        course.setName("Modern Flying");
        course.setNumber("340");
        course.setDescription("Explore the modern fly-by-wire control systems.");

        String modified = course.toString();

        assertNotEquals(original, modified);
    }

    @Test
    void testAddPrerequisite() {
        CourseRequirement courseReq = new CourseRequirement(1, Grade.A);
        course.addPrerequisite(courseReq);
        assertTrue(course.getPrerequisites().contains(courseReq));
    }

    @Test
    void testRemovePrerequisite() {
        CourseRequirement courseReq = new CourseRequirement(1, Grade.A);
        course.addPrerequisite(courseReq);
        course.removePrerequisite(courseReq);
        assertFalse(course.getPrerequisites().contains(courseReq));
    }

    @Test
    void testAddCorequisite() {
        Course courseCoreq = new Course(UUID.randomUUID(), Subject.CSCE, "220");
        course.addCorequisite(courseCoreq);
        assertTrue(course.getCorequisites().contains(courseCoreq));
    }

    @Test
    void testAddInvalidCorequisite() {
        Course courseCoreq = new Course(UUID.randomUUID(), Subject.CSCE, "-1924");
        course.addCorequisite(courseCoreq);
        assertFalse(course.getCorequisites().contains(courseCoreq));
    }

    @Test
    void testRemoveCorequisite() {
        Course courseCoreq = new Course(UUID.randomUUID(), Subject.CSCE, "220");
        course.addCorequisite(courseCoreq);
        course.removeCorequisite(courseCoreq);
        assertFalse(course.getCorequisites().contains(courseCoreq));
    }

    @Test
    void testAddSemesterOffered() {
        boolean fall, spring, summer;
        fall = spring = summer = false;
        boolean isAdded = false;

        for(int i = 0; i < course.getSemestersOffered().size(); i++) {
            if(course.getSemestersOffered().get(i) == Semester.FALL) {
                fall = true;
            } else if(course.getSemestersOffered().get(i) == Semester.SPRING) {
                spring = true;;
            } else {
                summer = true; 
            }
        }

        if(fall == false) {
            isAdded = course.addSemesterOffered(Semester.FALL);
            assertTrue(isAdded);
        } else if (spring == false) {
            isAdded = course.addSemesterOffered(Semester.SPRING);
            assertTrue(isAdded);
        } else if( summer == false) {
            isAdded = course.addSemesterOffered(Semester.SUMMER);
            assertTrue(isAdded);
        }
    }

    @Test
    void testRemoveSemesterOffered() {
        boolean isRemoved = false;
        for(int i = 0; i < course.getSemestersOffered().size(); i++) {
            if(course.getSemestersOffered().get(i) == Semester.FALL) {
                isRemoved = course.removeSemesterOffered(Semester.FALL);
                assertTrue(isRemoved);
            } else if(course.getSemestersOffered().get(i) == Semester.SPRING) {
                isRemoved = course.removeSemesterOffered(Semester.SPRING);
                assertTrue(isRemoved);
            } else {
                isRemoved = course.removeSemesterOffered(Semester.SUMMER);
                assertTrue(isRemoved);
            }
        }
    }

    @Test
    void testSetValidCreditHours() {
        course.setCreditHours(4);
        assertEquals(course.getCreditHours(), 4.0);
    }

    @Test
    void testSetNoCreditHours() {
        course.setCreditHours(0);
        assertEquals(course.getCreditHours(), 0);
    }

    @Test
    void testSetInvalidCreditHours() {
        course.setCreditHours(-5);
        assertEquals(course.getCreditHours(), 0);
    }
}
