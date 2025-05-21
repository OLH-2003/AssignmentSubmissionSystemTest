package prt.springbootthymeleafcrudwebapp.model;

public class UserDTO {

    private String fullName;
    private String email;
    private String role;
    private String password;
    private long matriculationNumber;
    private long staffNumber;

    public UserDTO(String fullName, String email, String role, String password) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public UserDTO(String fullName, String email, String role, String password, long matriculationNumber) {
        this(fullName, email, role, password);
        this.matriculationNumber = matriculationNumber;
    }

    public UserDTO(String fullName, String email, String role, String password, long staffNumber, boolean isStaff) {
        this(fullName, email, role, password);
        this.staffNumber = staffNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(long matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    public long getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(long staffNumber) {
        this.staffNumber = staffNumber;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", matriculationNumber='" + matriculationNumber + '\'' +
                ", staffNumber='" + staffNumber + '\'' +
                '}';
    }
}
