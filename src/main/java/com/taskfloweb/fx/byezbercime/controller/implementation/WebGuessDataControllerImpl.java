package com.taskfloweb.fx.byezbercime.controller.implementation;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessTaskFlow;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.GuessTaskFlow;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;

import java.util.List;

public interface WebGuessDataControllerImpl {

    EntityCatch<DtoGuess> getWebByGuessData(String email);

    EntityCatch<DtoGuess> saveWebGuessData(Guess guess);

    EntityCatch<DtoGuess> deleteWebGuessData(String email);

    EntityCatch<Integer> getGuessTaskFlowLists(String email);

    EntityCatch<DtoGuessTaskFlow> postWebGuessAddTaskflowData(String email, GuessTaskFlow guessTaskFlow);

    EntityCatch<DtoGuessTaskFlow> postWebGuessRemoveTaskflowData(String email,int taskflowId);

    EntityCatch<List<DtoGuess>> getWebAllTasks();

}
