package prt.springbootthymeleafcrudwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prt.springbootthymeleafcrudwebapp.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByStaffNumber(String staffNumber);
}
