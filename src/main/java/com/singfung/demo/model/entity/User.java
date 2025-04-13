package com.singfung.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.singfung.demo.model.dto.UserDTO;
import com.singfung.demo.model.enumeration.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author sing-fung
 * @since 1/22/2023
 */

@Entity
@Table(name = "demo_user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    String username;

    @Column(name = "password", nullable = false, length = 256)
    String password;

    @Column(name = "email", nullable = false, unique = true, length = 80)
    String email;

    @Enumerated(EnumType.STRING)
    UserStatus status;

    @Column(name = "create_time", nullable = false, length = 50)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;

    @Column(name = "ts", nullable = false, length = 50)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date ts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Address> addressList;

    public User(UserDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
