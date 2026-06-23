package com.taskfloweb.fx.byezbercime.controller.implementation;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;

import java.util.List;

public interface WebGuessDataControllerImpl {

    EntityCatch<DtoGuess> getWebByGuessData(String email);

    EntityCatch<List<DtoGuess>> getWebAllTasks();

}
