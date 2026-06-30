package com.taskfloweb.fx.byezbercime.dto;

import com.taskfloweb.fx.byezbercime.entity.Guess;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Validated
@Setter
public class DtoGuessService {

    private String email;

    private String realName;

    private String sourName;

    private String username;

    private String password;

    private String passwordConfirm;

    private String birthdayDate;

    public Guess getGuess() {

        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        return new Guess(
                email,
                username,
                password,
                passwordConfirm,
                realName,
                sourName,
                birthdayDate,
                sdf.format(nowDate),
                sdf.format(nowDate),
                "users",1,new ArrayList<>());
    }

}
