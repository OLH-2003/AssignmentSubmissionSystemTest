package prt.springbootthymeleafcrudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prt.springbootthymeleafcrudwebapp.model.Staff;
import prt.springbootthymeleafcrudwebapp.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/api/staff/{staffId}")
    public ResponseEntity<?> getStaffById(@PathVariable Long staffId) {
        try {
            Staff staff = staffService.getStaffById(staffId);

            if (staff == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Staff member not found with id: " + staffId);
            }

            // Return the staff member's full name (or other relevant details)
            return ResponseEntity.ok(staff.getFullName());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching staff member: " + e.getMessage());
        }
    }

    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staff", staffService.getAllStaff());
        return "staff/index";
    }

    @GetMapping("/new")
    public String createStaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/new";
    }

    @PostMapping
    public String saveStaff(@ModelAttribute("staff") Staff staff) {
        staffService.saveStaff(staff);
        return "redirect:/staff";
    }

    @GetMapping("/edit/{id}")
    public String editStaffForm(@PathVariable long id, Model model) {
        model.addAttribute("staff", staffService.getStaffById(id));
        return "staff/edit";
    }

    @PostMapping("/{id}")
    public String updateStaff(@PathVariable long id, @ModelAttribute("staff") Staff updatedStaff) {
        Staff existingStaff = staffService.getStaffById(id);

        existingStaff.setFullName(updatedStaff.getFullName());
        existingStaff.setEmail(updatedStaff.getEmail());
        existingStaff.setPasswordHash(updatedStaff.getPasswordHash());
        existingStaff.setStaffNumber(updatedStaff.getStaffNumber());

        staffService.saveStaff(existingStaff);
        return "redirect:/staff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable long id) {
        staffService.deleteStaffById(id);
        return "redirect:/staff";
    }
}