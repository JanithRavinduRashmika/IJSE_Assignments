package lk.royalInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {
    @Id
    private String id;
    private String studentName;
    private String address;
    private String contact;
    private String date;
    private String gender;

    @OneToMany(mappedBy = "student")
    private Registration registration;

    public Student() {
    }

    public Student(String id, String studentName, String address, String contact, String date, String gender, Registration registration) {
        this.setId(id);
        this.setStudentName(studentName);
        this.setAddress(address);
        this.setContact(contact);
        this.setDate(date);
        this.setGender(gender);
        this.setRegistration(registration);
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

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
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
                ", registration=" + registration +
                '}';
    }
}
