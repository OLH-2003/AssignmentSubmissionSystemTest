package prt.springbootthymeleafcrudwebapp.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "student_id")
    private long studentId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentID) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return super.getFullName();
    }

    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPasswordHash() {
        return super.getPasswordHash();
    }

    public void setPasswordHash(String passwordHash) {
        super.setPasswordHash(passwordHash);
    }
}