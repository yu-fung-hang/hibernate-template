package com.singfung.demo.model.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DepartmentDTO {
    @NotBlank(message = "name cannot be empty", groups = {DepartmentDTO.Insert.class, DepartmentDTO.Update.class})
    String name;

    public interface Update {}

    public interface Insert {}
}
