package com.taskfloweb.fx.byezbercime.service;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessService;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.exception.GlobalException;
import com.taskfloweb.fx.byezbercime.repositories.WebGuessRepositories;
import com.taskfloweb.fx.byezbercime.service.implementation.WebUserServiceImpl;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebUserService implements WebUserServiceImpl {

    @Autowired
    private WebGuessRepositories guessRepositories;

    @Override
    public EntityCatch<DtoGuess> verifyRegister(@NonNull DtoGuessService dtoGuessService) {

        if (dtoGuessService.getGuess() != null) {
            if (!isEmailAuthentication(dtoGuessService.getEmail())) {
                return GlobalException.errorCatch(new IllegalArgumentException("The email is invalid!"), HttpStatus.BAD_REQUEST);
            } else if ( dtoGuessService.getRealName() != null && !dtoGuessService.getRealName().isEmpty() && !isIllegalCharacter(dtoGuessService.getRealName()) ) {
                return GlobalException.errorCatch(new IllegalArgumentException("The realname is invalid!"), HttpStatus.BAD_REQUEST);
            } else if ( dtoGuessService.getSourName() != null && !dtoGuessService.getSourName().isEmpty() && !isIllegalCharacter(dtoGuessService.getSourName()) ) {
                return GlobalException.errorCatch(new IllegalArgumentException("The sourname is invalid!"), HttpStatus.BAD_REQUEST);
            } else if ( !isUsernameExists("@",dtoGuessService.getUsername())) {
                return GlobalException.errorCatch(new IllegalArgumentException("The username is invalid!"), HttpStatus.BAD_REQUEST);
            } else if ( !dtoGuessService.isCorrectPassword(this)) {
                return GlobalException.errorCatch(new IllegalArgumentException("The password is invalid!"), HttpStatus.BAD_REQUEST);
            }
        }

        Guess guess = dtoGuessService.getGuess();
        DtoGuess dtoGuess = new DtoGuess();

        if (!guessRepositories.getAllTasks().contains(guess) && guess != null) {

            guessRepositories.save(guess);

            BeanUtils.copyProperties(guess, dtoGuess);
            dtoGuess.setTaskFlowsList(new ArrayList<>());

        }

            return EntityCatch.handlerBody(dtoGuess,HttpStatus.OK);
    }

    public boolean isUsernameExists(String tag,String username) {
        boolean result = false;

        if (!username.isEmpty()) {

            int a = username.length() - 1;
            String p = username.substring(0,username.length() - a);
            String b = username.substring(1);


            if (tag.equals(p) && isIllegalCharacter(b)) {
                result = true;
            }
        }

        return result;
    }

    public boolean isOldLimit(String birthdayDateText,int oldLimit) {
        boolean result = false;

        if (!String.valueOf(oldLimit).isEmpty() && isBirthdayDateAuthentication(birthdayDateText)) {

            Date dt = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            String nowYear = simpleDateFormat.format(dt);
            String[] birthdayDateTextSplits = birthdayDateText.split("/");
            int year = Integer.parseInt(birthdayDateTextSplits[2]);

            int oldYear = Integer.parseInt(nowYear) - year;

            if (oldLimit <= oldYear) {
                result = true;
            }

        }
        return result;
    }

    public boolean isIllegalCharacter(String infoValue) {
        boolean result = false;

        StringBuilder textInfo = new StringBuilder();
        String legalCharacters = "zxcvbnmilkjhgfdsaqwertyuop0_987654321ZXCVBNMLKJHGFDSAQWERTYUIOP";
        String[] legalCharactersSplits = legalCharacters.split("");
        List<String> illegals = Arrays.stream(legalCharactersSplits).collect(Collectors.toList());

        String[] textSplits = infoValue.split("");

        for (int s = 0; s < textSplits.length; s++) {
            String value =  textSplits[s];
            if (isCharacter(illegals,value)) {
                textInfo.append(value);
            } else {
                break;
            }
        }

        String infoStr =  textInfo.toString();

        if (infoStr.equals(infoValue)) {
            result = true;
        }
        return result;
    }

    boolean isCharacter(List<String> s,String value) {
        boolean result = false;

        if (!s.isEmpty() && !value.isEmpty()) {
            for (String v : s) {
                if (v.equals(value)) {
                    result = true;
                }
            }
        }

        return result;
    }

    public boolean isEmailAuthentication(String email) {
        boolean result = false;

        if (!email.isEmpty()) {
            String[] emailAdressSplits = email.split("@");
            String emailName = emailAdressSplits[0];
            String mailsLinks = emailAdressSplits[1];

            if (isIllegalCharacter(emailName) && isTextLength(emailName,244) && mailsLinks.equals("gmail.com")) {
                result = true;
            }
        }
        return result;
    }

    public enum PasswordRules {

        UPPERCASE(List.of("Z","X","C","V","B","N","M","L","K","J","H","G","F","D","S","A","Q","W","E","R","T","Y","U","I","O","P")),
        LOWERCASE(List.of("z","x","c","v","b","n","m","l","k","h","g","f","d","s","a","q","w","e","r","t","y","u","i","o","p")),
        KEYCASE(List.of("*","_",".","=","+","-",",")),
        NUMBERS(List.of("0","1","2","3","4","5","6","7","8","9"));

        List<String> data;

        PasswordRules(List<String> data) {
            this.data = data;
        }

        public List<String> getInfo() {
            return data;
        }

    }

    private boolean isTypeTextAuthentication(List<String> d, String value) {
        boolean result = false;

        if (!d.isEmpty() && !value.isEmpty()) {

            for (String s : d) {
                if (value.equals(s)) {
                    result = true;
                }
            }

        }

        return result;
    }

    public boolean isCharacterAuthenticate(PasswordRules information, int typeLength, String value) {
        boolean result = false;

        int characters = 0;
        String[] values = value.split("");

        for (int i = 0; i < values.length; i++) {
            String v = values[i];
            if (isTypeTextAuthentication(information.getInfo(),v)) {
                characters++;
            }
        }

        if (characters >= typeLength) {
            result = true;
        }

        return result;
    }

    public boolean isBirthdayDateAuthentication(String birthdayDateText) {
        boolean result = false;
        if (!birthdayDateText.isEmpty()) {

            String[] birthdayDateTextSplits = birthdayDateText.split("/");

            String day = birthdayDateTextSplits[0];
            String month = birthdayDateTextSplits[1];
            String year = birthdayDateTextSplits[2];

            if (!day.isEmpty() && !month.isEmpty() && !year.isEmpty()) {

                if ( (month.equals("02") && Integer.parseInt(day) <= 28) || (month.equals("02") &&  Integer.parseInt(day) <= 29) ) {
                    result = true;
                }
                if (month.equals("01") && Integer.parseInt(day) <= 31) {
                    result = true;
                } else if (month.equals("03") && Integer.parseInt(day) <=31) {
                    result = true;
                } else if (month.equals("04") && Integer.parseInt(day) <=30) {
                    result = true;
                } else if (month.equals("05") && Integer.parseInt(day) <=31) {
                    result = true;
                } else if (month.equals("06") && Integer.parseInt(day) <=30) {
                    result = true;
                } else if (month.equals("07") && Integer.parseInt(day) <=31) {
                    result = true;
                } else if (month.equals("08") && Integer.parseInt(day) <=31) {
                    result = true;
                } else if (month.equals("09") && Integer.parseInt(day) <=30) {
                    result = true;
                } else if (month.equals("10") && Integer.parseInt(day) <=31) {
                    result = true;
                } else if (month.equals("11") && Integer.parseInt(day) <=30) {
                    result = true;
                } else if (month.equals("12") && Integer.parseInt(day) <=31) {
                    result = true;
                }

            }
        }
        return result;
    }

    public boolean isTextLength(String text,int length) {
        boolean result = false;
        if (!text.isEmpty() && text.length() < length) {
            result = true;
        }
        return result;
    }

    public boolean isTextMinLength(String text,int length) {
        boolean result = false;
        if (!text.isEmpty() && text.length() > length) {
            result = true;
        }
        return result;
    }

}
