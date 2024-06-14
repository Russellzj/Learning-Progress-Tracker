package tracker.People;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    public void testStudentConstructor() {
        Student student = new Student("Grace Hopper hopperg@nku.edu");
        assertEquals("Grace", student.getFirstName());
        assertEquals("Hopper", student.getLastName());
        assertEquals("hopperg@nku.edu", student.getEmail());
    }
}