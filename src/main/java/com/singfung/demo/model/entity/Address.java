package com.singfung.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "address", nullable = false, length = 50)
    String address;

    @Column(name = "city", nullable = false, length = 50)
    String city;
	
	@Column(name = "province", nullable = false, length = 20)
    String province;
	
	@Column(name = "postal_code", nullable = false, length = 20)
    String postalCode;

    @Column(name = "create_time", nullable = false, length = 50)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;

    @Column(name = "ts", nullable = false, length = 50)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date ts;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    User user;
}