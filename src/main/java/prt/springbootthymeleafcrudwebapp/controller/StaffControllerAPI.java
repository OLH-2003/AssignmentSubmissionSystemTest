package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.model.Staff;
import prt.springbootthymeleafcrudwebapp.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffControllerAPI {

    @Autowired
    private StaffService staffService;

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable long id) {
        return staffService.getStaffById(id);
    }

    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        staffService.saveStaff(staff);
        return staff;
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable long id, @RequestBody Staff staff) {
        Staff existingStaff = staffService.getStaffById(id);
        existingStaff.setFullName(staff.getFullName());
        existingStaff.setEmail(staff.getEmail());
        existingStaff.setPasswordHash(staff.getPasswordHash());
        existingStaff.setStaffNumber(staff.getStaffNumber());
        staffService.saveStaff(existingStaff);
        return existingStaff;
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable long id) {
        staffService.deleteStaffById(id);
    }
}