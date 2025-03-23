package com.singfung.demo.service;

import com.singfung.demo.model.dto.DepartmentDTO;
import com.singfung.demo.model.entity.Department;
import com.singfung.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department addDepartment(DepartmentDTO dto) {
        Department department = new Department(dto);
        department.setCreateTime(new Date());
        department = departmentRepository.save(department);
        return department;
    }
}
