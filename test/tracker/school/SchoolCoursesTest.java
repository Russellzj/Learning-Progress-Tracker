package tracker.school;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolCoursesTest {
    @Test
    @DisplayName("SchoolClass Statistics ")
    void testMostPopular() {
        SchoolCourses myClasses = new SchoolCourses();
        myClasses.addPoint(new int[]{1, 2, 3, 4});
        myClasses.addPoint(new int[]{5, 6, 7, 0});
        myClasses.addStudent(true, true, true,true);
        myClasses.addStudent(true, true, true,false);
        myClasses.printStatistics();
    }
}
