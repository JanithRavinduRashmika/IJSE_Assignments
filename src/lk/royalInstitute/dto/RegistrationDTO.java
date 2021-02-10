package lk.royalInstitute.dto;

import lk.royalInstitute.entity.Course;
import lk.royalInstitute.entity.Student;


public class RegistrationDTO implements SuperDTO {
    private int regNo;
    private String regDate;
    private double regFee;
    private String studentId;
    private String courseId;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int regNo, String regDate, double regFee, String studentId, String courseId) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
