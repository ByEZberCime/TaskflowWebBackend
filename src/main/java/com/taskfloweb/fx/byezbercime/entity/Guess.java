package com.taskfloweb.fx.byezbercime.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "table_name")
public class Guess {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "taskflow_base_username",nullable = true)
    private String username;

    @Column(name = "taskflow_base_password",nullable = true)
    private String password;

    @Column(name = "taskflow_base_confirmpassword",nullable = true)
    private String confirmPassword;


    @Column(name = "taskflow_base_firstname",nullable = true)
    private String firstName;

    @Column(name = "taskflow_base_sourname",nullable = true)
    private String sourName;

    @Column(name = "taskflow_base_birthday",nullable = true)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private String birthdayDate;

    @Column(name = "taskflow_base_register",nullable = true)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private String registerDate;

    @Column(name = "taskflow_base_login",nullable = true)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private String loginDate;

    @Column(name = "taskflow_base_certificatecode",nullable = false)
    private String certificatedPrimaryOfficialCode = "user";

    /*
    * logim result is 0 is false, 1 is true
    * */

    @Column(name = "taskflow_base_logined",nullable = true)
    private int loginResult = 1;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "taskflow_taskflowlist_id")
    private List<GuessTaskFlow> taskFlowsList;

}
