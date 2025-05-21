package prt.springbootthymeleafcrudwebapp.service;
import prt.springbootthymeleafcrudwebapp.model.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();
    Staff getStaffById(long id);
    void saveStaff(Staff staff);
    void deleteStaffById(long id);
    Staff getStaffByStaffNumber(String staffNumber);
}
