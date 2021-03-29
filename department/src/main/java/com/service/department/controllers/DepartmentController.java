package com.service.department.controllers;

import com.service.department.entities.Department;
import com.service.department.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDeparment(@RequestBody Department department) {
        log.info("Inside saveDepartment inside of DepartmentController", department);
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside findDepartmentById inside of DepartmentController", departmentId);
        return departmentService.findDepartmentById(departmentId);
    }

}
