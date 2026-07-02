package com.taskfloweb.fx.byezbercime.dto;

import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.exception.GlobalException;
import com.taskfloweb.fx.byezbercime.service.WebUserService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Validated
@Data
@NoArgsConstructor
public class DtoGuessService {

    private String email;

    private String realName;

    private String sourName;

    private String username;

    @Getter(AccessLevel.NONE)
    private String password;

    @Getter(AccessLevel.NONE)
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

    public boolean isCorrectPassword(WebUserService userService) {
        boolean result = false;

        if (password != null && !password.isEmpty() && passwordConfirm != null && !passwordConfirm.isEmpty()) {
            if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.UPPERCASE,6,password)) {
                if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.LOWERCASE,6,password)) {
                    if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.NUMBERS,6,password)) {
                        if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.KEYCASE,1,password)) {
                            if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.UPPERCASE,6,passwordConfirm)) {
                                if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.LOWERCASE,6,passwordConfirm)) {
                                    if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.NUMBERS,6,passwordConfirm)) {
                                        if (userService.isCharacterAuthenticate(WebUserService.PasswordRules.KEYCASE,1,passwordConfirm)) {

                                            if (password.equals(passwordConfirm)) {
                                                result = true;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

}
