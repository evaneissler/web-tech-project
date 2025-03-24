package edu.tcu.cs.backend.admin;

import edu.tcu.cs.backend.system.Result;
import edu.tcu.cs.backend.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public Result findAllAdmin() {
        List<Admin> foundAdmins = this.adminService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundAdmins);
    }
}
