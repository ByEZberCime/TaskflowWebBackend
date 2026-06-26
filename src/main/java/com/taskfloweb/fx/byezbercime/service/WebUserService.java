package com.taskfloweb.fx.byezbercime.service;

import com.taskfloweb.fx.byezbercime.repositories.WebGuessRepositories;
import com.taskfloweb.fx.byezbercime.service.implementation.WebUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebUserService implements WebUserServiceImpl {

    @Autowired
    private WebGuessRepositories guessRepositories;



}
