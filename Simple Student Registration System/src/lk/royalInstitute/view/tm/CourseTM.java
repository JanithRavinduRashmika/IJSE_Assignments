package lk.royalInstitute.view.tm;

public class CourseTM {
    private String code;
    private String courseName;
    private double courseFee;
    private String duration;

    public CourseTM() {
    }

    public CourseTM(String code, String courseName, double courseFee, String duration) {
        this.code = code;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.duration = duration;
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
}
