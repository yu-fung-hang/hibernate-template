package com.singfung.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author sing-fung
 * @since 1/22/2023
 */

@Data
public class UserDTO
{
    @NotBlank(message = "username cannot be empty", groups = {Insert.class, Update.class})
    String username;

    @NotBlank(message = "password cannot be empty", groups = {Insert.class})
    String password;

    @Email(message = "invalid email format", groups = {Insert.class, Update.class})
    @NotBlank(message = "email cannot be empty", groups = {Insert.class, Update.class})
    String email;

    List<AddressDTO> addressList;

    public interface Update {}

    public interface Insert {}
}
