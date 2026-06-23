package com.taskfloweb.fx.byezbercime.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "taskflow_tasks_base")
public class GuessTaskFlow {

    @Id
    @Column(name = "taskflow_uniqueid")
    private UUID taskUniqueid;

    @Column(name = "taskflow_id")
    private int taskId;

    @Column(name = "taskflow_taskname")
    private String taskName;

    @Column(name = "taskflow_taskstatus")
    private int taskStatus;

    @ManyToOne
    @JoinColumn(name = "taskflow_taskflowlist_id")
    private Guess owner;

}
