package lk.royalInstitute.entity;

import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String id;
    private String studentName;
    private String address;
    private String contact;
    private String date;
    private String gender;

    @OneToMany(mappedBy = "student")
    private List<Registration> registrations;

    public Student() {
    }

    public Student(String id, String studentName, String address, String contact, String date, String gender, List<Registration> registrations) {
        this.id = id;
        this.studentName = studentName;
        this.address = address;
        this.contact = contact;
        this.date = date;
        this.gender = gender;
        this.registrations = registrations;
    }

    public Student(String id, String studentName, String address, String contact, String date, String gender) {
        this.id = id;
        this.studentName = studentName;
        this.address = address;
        this.contact = contact;
        this.date = date;
        this.gender = gender;
    }

    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
