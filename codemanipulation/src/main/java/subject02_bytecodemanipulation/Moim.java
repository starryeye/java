package subject02_bytecodemanipulation;

public class Moim {

    int maxNumberOfAttendees; // 모임 최대 인원
    int numberOfEnrollment; // 현재 신청 인원

    public boolean isEnrollmentFull() {

        if(maxNumberOfAttendees == 0) {
            return true;
        }

        if(numberOfEnrollment >= maxNumberOfAttendees) {
            return true;
        }

        return false;
    }
}
