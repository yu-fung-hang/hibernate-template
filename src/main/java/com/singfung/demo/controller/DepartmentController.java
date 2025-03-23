package com.singfung.demo.controller;

import com.singfung.demo.model.dto.DepartmentDTO;
import com.singfung.demo.model.entity.Department;
import com.singfung.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department addDepartment(@RequestBody @Validated(DepartmentDTO.Insert.class) DepartmentDTO dto) {
        return departmentService.addDepartment(dto);
    }

    @PutMapping
    public Department addDepartmentByPut(@RequestBody @Validated(DepartmentDTO.Insert.class) DepartmentDTO dto) {
        return departmentService.addDepartment(dto);
    }
}
