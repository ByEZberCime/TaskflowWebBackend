package com.taskfloweb.fx.byezbercime.service;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessTaskFlow;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.GuessTaskFlow;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.exception.GlobalException;
import com.taskfloweb.fx.byezbercime.repositories.WebGuessRepositories;
import com.taskfloweb.fx.byezbercime.service.implementation.WebGuessDataServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebGuessDataService implements WebGuessDataServiceImpl {

    @Autowired
    private WebGuessRepositories guessRepositories;

    @Override
    public EntityCatch<DtoGuess> getWebByGuessData(String email) {

        List<DtoGuessTaskFlow> dtoTaskFlows = new ArrayList<>();
        DtoGuess dtoGuess = new DtoGuess();
        Guess guessData = guessRepositories.getGuessByData(email);

        if (guessData == null) {
            return GlobalException.errorCatch(new IllegalArgumentException("Data is not found"),HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(guessData, dtoGuess);

        for (GuessTaskFlow guessTaskFlow : guessData.getTaskFlowsList()) {
            DtoGuessTaskFlow flow = new DtoGuessTaskFlow();
            flow.setTaskName(guessTaskFlow.getTaskName());
            flow.setTaskStatus(guessTaskFlow.getTaskStatus());

            if (!dtoTaskFlows.contains(flow)) {
                dtoTaskFlows.add(flow);
            }
        }

        dtoGuess.setTaskFlowsList(dtoTaskFlows);
        return EntityCatch.handlerBody(dtoGuess, HttpStatus.OK);
    }

}
