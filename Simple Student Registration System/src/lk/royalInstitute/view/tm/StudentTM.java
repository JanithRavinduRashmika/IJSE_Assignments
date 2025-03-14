package lk.royalInstitute.view.tm;

public class StudentTM {
    private String id;
    private String studentName;
    private String address;
    private String contact;
    private String date;
    private String gender;

    public StudentTM() {
    }

    public StudentTM(String id, String studentName, String address, String contact, String date, String gender) {
        this.id = id;
        this.studentName = studentName;
        this.address = address;
        this.contact = contact;
        this.date = date;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "StudentTM{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
