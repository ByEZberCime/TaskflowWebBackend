package com.taskfloweb.fx.byezbercime.controller.implementation;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessService;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;

public interface WebUserControllerImpl {

    EntityCatch<DtoGuess> register(DtoGuessService guessService);

    EntityCatch<DtoGuess> login(Guess guess);

}
