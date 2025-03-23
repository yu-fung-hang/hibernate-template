package com.singfung.demo.model.entity;

import com.singfung.demo.model.dto.DepartmentDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false, length = 30)
    String name;

    public Department(DepartmentDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
