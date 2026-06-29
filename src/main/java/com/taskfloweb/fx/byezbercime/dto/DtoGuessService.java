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

    @Email(message = "Please write email about!")
    private String email;

    @Size(min = 4,message = "The minimum length is 4 character!")
    @Size(max = 30,message = "The maximum length is 30 charater!")
    @NotEmpty(message = "Please write to your real name!")
    private String realName;

    @Size(min = 4,message = "The minimum length is 4 character!")
    @Size(max = 30,message = "The maximum length is 30 charater!")
    @NotEmpty(message = "Please write to your sour name!")
    private String sourName;

    @Size(min = 4,message = "The minimum length is 4 character!")
    @Size(max = 30,message = "The maximum length is 30 charater!")
    @NotEmpty(message = "Please write to username!")
    private String username;

    @NotEmpty(message = "The password is doesn't stay on avoid")
    private String password;

    @NotEmpty(message = "The password is doesn't stay on avoid")
    private String passwordConfirm;

    @NotEmpty(message = "The birthday right format on 'dd.MM.yyyy'")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.(19|20)\\d{2}$",
            message = "The birthday date is format 'dd.MM.yyyy'.")
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
