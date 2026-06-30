package com.taskfloweb.fx.byezbercime.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

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

    boolean isTypeTextAuthentication(List<String> d, String value) {
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

    public boolean isCharacterAuthenticate(PasswordService.PasswordRules information, int typeLength, String value) {
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

}
