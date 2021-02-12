package lk.royalInstitute.view.tm;

public class CustomEntityTM {
    private String studentId;
    private String studentName;
    private int regNo;

    public CustomEntityTM() {
    }

    public CustomEntityTM(String studentId, String studentName, int regNo) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.regNo = regNo;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }
}
