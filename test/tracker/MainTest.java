package tracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    //Tests StudentInputChecker to make sure there are no false inputs
    @ParameterizedTest
    @ValueSource(strings = {"Joe D jd@gmail.com", "John Doe", "John", "J--ohn  Doe doe@gmail.com", "  John  Doe", "J-'ohn doe jDoe@gmail.com"})
    void testStudentInputCheckerFalse(String studentID) {
        assertFalse(Main.studentInputChecker(studentID));
    }

    //Tests StudentInputChecker to make sure accepted inputs are accepted
    @ParameterizedTest
    @ValueSource(strings = {"na'me s-ue ii@ii.ii", "J-ohn Da ts@gmail.com", "Jo D-brand D-brand@gmail.com", "John  Doe Dear   doe.j@gmail.com"})
    void testStudentInputCheckerTrue(String studentID) {
        assertTrue(Main.studentInputChecker(studentID));
    }


/*
    @Test
    public void testStudentInputChecker() {
        boolean testResults = true;
        String[] falseInputs = {"John Doe", "John", "John  Doe"};
        for (String studentInfo : falseInputs) {
            if(!Main.studentInputChecker(studentInfo)){
                System.out.println(studentInfo + ": resulted in false positive.");
                testResults = false;
            }
        }
        assertTrue(testResults);
    }

 */
}
