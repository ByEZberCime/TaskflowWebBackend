package com.taskfloweb.fx.byezbercime.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class DtoGuess {

    private String email;

    private String username;

    private String firstName;

    private String sourName;

    private String birthdayDate;

    private int loginResult;

    private List<DtoGuessTaskFlow> taskFlowsList;

}

