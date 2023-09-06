package subject02_bytecodemanipulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoimTest {

    @Test
    public void isFull() {
        Moim moim = new Moim();
        moim.maxNumberOfAttendees = 100;
        moim.numberOfEnrollment = 10;

        Assertions.assertFalse(moim.isEnrollmentFull());
    }

}