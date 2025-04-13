package com.singfung.demo.model.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class AddressDTO {
    @NotBlank(message = "address cannot be empty", groups = {AddressDTO.Insert.class, AddressDTO.Update.class})
    String address;

    @NotBlank(message = "city cannot be empty", groups = {AddressDTO.Insert.class,  AddressDTO.Update.class})
    String city;

    @NotBlank(message = "province cannot be empty", groups = {AddressDTO.Insert.class,  AddressDTO.Update.class})
    String province;

    @NotBlank(message = "postal code cannot be empty", groups = {AddressDTO.Insert.class,  AddressDTO.Update.class})
    String postalCode;

    public interface Update {}

    public interface Insert {}
}