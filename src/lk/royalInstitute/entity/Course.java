package lk.royalInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity {
    @Id
    private String code;
    private String courseName;
    private double courseFee;
    private String duration;

    @OneToMany(mappedBy = "course")
    private List<Registration> registrations;

    public Course() {
    }

    public Course(String code, String courseName, double courseFee, String duration, List<Registration> registrations) {
        this.code = code;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.duration = duration;
        this.registrations = registrations;
    }

    public Course(String code, String courseName, double courseFee, String duration) {
        this.code = code;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.duration = duration;
    }

    public Course(String code) {
        this.code = code;
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

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
