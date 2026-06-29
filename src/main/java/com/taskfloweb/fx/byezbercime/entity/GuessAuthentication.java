package com.taskfloweb.fx.byezbercime.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "taskflow_base_authentication")
public class GuessAuthentication {

    @Id
    @Column(name = "taskflow_base_authentication_email", nullable = false)
    private  String email;

    @Column(name = "taskflow_base_authentication_password", nullable = false)
    private String password;

    @Column(name = "taskflow_base_authentication_key", nullable = false)
    private String keys;

}
