package com.singfung.demo.service;

import com.singfung.demo.model.dto.AddressDTO;
import com.singfung.demo.model.dto.UserDTO;
import com.singfung.demo.model.entity.Address;
import com.singfung.demo.model.entity.User;
import com.singfung.demo.model.enumeration.UserStatus;
import com.singfung.demo.repository.AddressRepository;
import com.singfung.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author sing-fung
 * @since 1/22/2023
 */

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public User addUser(UserDTO dto) {
        if(userRepository.findByUsername(dto.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username has been registered");
        }

        if(userRepository.findByEmail(dto.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email has been registered");
        }

        User user = new User(dto);

        user.setStatus(UserStatus.enabled);
        user.setCreateTime(new Date());
        user.setTs(new Date());

        user = userRepository.save(user);

        List<AddressDTO> addressDTOList = dto.getAddressList();
        saveAddress(addressDTOList, user);

//        if (1+1==2) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "I am an exception");
//        }

        return user;
    }

    public void saveAddress(List<AddressDTO> addressDTOList, User user) {
        List<Address> addressList = new ArrayList<>();
        for (AddressDTO addressDTO : addressDTOList) {
            Address address = new Address();
            BeanUtils.copyProperties(addressDTO, address);
            address.setCreateTime(new Date());
            address.setTs(new Date());
            address.setUser(user);
            addressList.add(address);
        }

        if (addressList.size() > 0) {
            addressRepository.saveAll(addressList);
        }

//        if (1+1==2) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "I am an exception");
//        }
    }

    public List<User> listAllUsers() {
        return userRepository.findByOrderByIdDesc();
    }

    public User getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return userOptional.get();
    }

    public User updateUser(Integer id, UserDTO dto) {
        if(dto.getUsername() != null && userRepository.findByUsernameAndIdNot(dto.getUsername(), id) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username has been registered");
        }

        if(dto.getEmail() != null && userRepository.findByEmailAndIdNot(dto.getEmail(), id) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email has been registered");
        }

        User user = getUserById(id);
        dto.setPassword(user.getPassword());
        BeanUtils.copyProperties(dto, user);
        user.setTs(new Date());

        return userRepository.save(user);
    }

    public User updateUserStatus(Integer id, UserStatus status) {
        User user = getUserById(id);

        if(!user.getStatus().equals(status)) {
            user.setStatus(status);
            user.setTs(new Date());
            userRepository.save(user);
        }

        return user;
    }
}
