package com.taskfloweb.fx.byezbercime.service;

import com.taskfloweb.fx.byezbercime.configuration.JWTConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Autowired
    private JWTConfiguration jwtConfiguration;

    public String generateToken() {


        return null;
    }

}
