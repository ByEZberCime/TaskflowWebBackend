package com.taskfloweb.fx.byezbercime.controller.implementation;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;

public interface WebGuessDataControllerImpl {

    EntityCatch<DtoGuess> getWebByGuessData(String email);

}
