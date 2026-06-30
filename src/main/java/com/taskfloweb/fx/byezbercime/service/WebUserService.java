package com.taskfloweb.fx.byezbercime.service;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessService;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.repositories.WebGuessRepositories;
import com.taskfloweb.fx.byezbercime.service.implementation.WebUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebUserService implements WebUserServiceImpl {

    @Autowired
    private WebGuessRepositories guessRepositories;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private EmailService emailService;

    @Override
    public EntityCatch<DtoGuess> verifyRegister(DtoGuessService dtoGuessService) {



        return null;
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
