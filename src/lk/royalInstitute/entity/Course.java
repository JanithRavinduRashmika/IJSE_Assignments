package lk.royalInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
    @Id
    private String code;
    private String courseName;
    private String courseType;
    private String duration;

    @OneToMany(mappedBy = "course")
    private Registration registration;

    public Course() {
    }

    public Course(String code, String courseName, String courseType, String duration, Registration registration) {
        this.code = code;
        this.courseName = courseName;
        this.courseType = courseType;
        this.duration = duration;
        this.registration = registration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", duration='" + duration + '\'' +
                ", registration=" + registration +
                '}';
    }
}
