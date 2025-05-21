package prt.springbootthymeleafcrudwebapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 2537336
 */
@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "staff_id")
    private int staffId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
