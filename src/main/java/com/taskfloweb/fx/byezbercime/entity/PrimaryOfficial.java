package com.taskfloweb.fx.byezbercime.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "taskflow_primaryofficials")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PrimaryOfficial {

    @Id
    @Column(name = "taskflow_certificatedcode")
    private String certificatedPrimaryOfficialCode;

    @Column(name = "taskflow_primary_username")
    private String primaryUsername;

    @Column(name = "taskflow_primary_firstname")
    private String primaryFirstname;

    @Column(name = "taskflow_primary_sourname")
    private String primarySourname;

    @Column(name = "taskflow_primary_role")
    private String role;

}
