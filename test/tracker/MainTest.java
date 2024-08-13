package tracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tracker.school.SchoolClass;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    //Tests StudentInputChecker to make sure there are no false inputs
    @ParameterizedTest
    @CsvSource({"n, sam, same@store.com", "J--ohn, Doe, doe@gmail.com", "J-'ohn, doe, jDoe@gmail.com"})
    void testStudentInputCheckerFalse(String firstName, String lastName, String email) {
        assertFalse(Main.studentInputChecker(firstName, lastName, email));
    }

    //Tests StudentInputChecker to make sure accepted inputs are accepted
    @ParameterizedTest
    @CsvSource({"name, s-u, ii@ii.ii", "J-ohn, Da, ts@gmail.com", "Jo'hn, D-brand, D-brand@gmail.com", "John, Doe Dear, doe.j@gmail.com"})
    void testStudentInputCheckerTrue(String firstName, String lastName, String email) {
        assertTrue(Main.studentInputChecker(firstName, lastName, email));
    }

    @ParameterizedTest
    @ValueSource (strings = {"name su ii@ii.com"})
    void testNewTest(String userInput){
        String[] separateInput = Main.studentInputSeparator(userInput);
        assertTrue(Main.studentInputChecker(separateInput[0], separateInput[1], separateInput[2]));
    }

    @Test
    @DisplayName("Test Add Points Function")
    void testAddPoints(){
        Main.addStudent("John Doe JDoe@gmai.com");
        Main.addStudent("Jane Doe jDoe2@gmail.com");
        Main.addPoints("1000 1 2 3 4");
        Main.addPoints("1001 5 6 7 0");
        Main.printStudentList();
    }

    @Test
    @DisplayName("Test Print Best Students")
    void testBestStudents() {
        testAddPoints();
        Main.getTopStudents("Java");
    }

    @Test
    @DisplayName("SchoolClass Statistics ")
        void testMostPopular() {
            SchoolClass myClasses = new SchoolClass();
            myClasses.addPoint(new int[]{1, 2, 3, 4});
            myClasses.addPoint(new int[]{5, 6, 7, 0});
            myClasses.printStatistics();
        }

    /*
    @Test
    public void testStudentInputChecker() {
        String[] falseInputs = {"n sam same@store.com", "J--ohn Doe doe@gmail.com", "J-'ohn doe jDoe@gmail.com"};
        for (String studentInfo : falseInputs) {
            String[] currentStudent = Main.studentInputSeparator(studentInfo);
            assertFalse(Main.studentInputChecker(currentStudent[0], currentStudent[1], currentStudent[2]));
        }
    }

     */
}
