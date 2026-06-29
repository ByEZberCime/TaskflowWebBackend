package com.taskfloweb.fx.byezbercime.controller;

import com.taskfloweb.fx.byezbercime.controller.implementation.WebUserControllerImpl;
import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessService;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebUserDataController implements WebUserControllerImpl {

    @PostMapping(path = "/register")
    @Override
    public EntityCatch<DtoGuess> register(@Valid() @RequestBody DtoGuessService guessService) {
        return null;
    }

    @PostMapping(path = "/login")
    @Override
    public EntityCatch<DtoGuess> login(@RequestBody Guess guess) {
        return null;
    }

}
