package com.taskfloweb.fx.byezbercime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private WebUserService userService;

    public boolean isEmailAuthentication(String email) {
        boolean result = false;

        if (!email.isEmpty()) {
            String[] emailAdressSplits = email.split("@");
            String emailName = emailAdressSplits[0];
            String mailsLinks = emailAdressSplits[1];

            if (userService.isIllegalCharacter(emailName) && userService.isTextLength(emailName,244) && mailsLinks.equals("gmail.com")) {
                result = true;
            }
        }
        return result;
    }

}
