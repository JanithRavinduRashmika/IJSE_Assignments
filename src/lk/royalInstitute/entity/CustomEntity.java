package lk.royalInstitute.entity;

public class CustomEntity{
    private String studentId;
    private String studentName;
    private int regNo;

    public CustomEntity() {
    }

    public CustomEntity(String studentId, String studentName, int regNo) {
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
