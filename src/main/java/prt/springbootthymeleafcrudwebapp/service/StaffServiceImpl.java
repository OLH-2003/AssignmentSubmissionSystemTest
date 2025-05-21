package prt.springbootthymeleafcrudwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.springbootthymeleafcrudwebapp.model.Staff;
import prt.springbootthymeleafcrudwebapp.repository.StaffRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        return staff.orElse(null);
    }

    @Override
    public void saveStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaffById(long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Staff getStaffByStaffNumber(String staffNumber) {
        return staffRepository.findByStaffNumber(staffNumber);
    }
}
