package com.taskfloweb.fx.byezbercime.service;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessTaskFlow;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.GuessTaskFlow;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.exception.GlobalException;
import com.taskfloweb.fx.byezbercime.repositories.WebGuessRepositories;
import com.taskfloweb.fx.byezbercime.repositories.WebTaskflowRepositories;
import com.taskfloweb.fx.byezbercime.service.implementation.WebOfficialServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebOfficialService implements WebOfficialServiceImpl {

    @Autowired
    private WebGuessRepositories guessRepositories;

    @Autowired
    private WebTaskflowRepositories taskFlowRepositories;

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


    @Override
    public EntityCatch<Integer> getGuessTaskFlowLists(String email) {

        Guess guessData = guessRepositories.getGuessByData(email);
        if (guessData == null) {
            return GlobalException.errorCatch(new IllegalArgumentException("Data is not found"),HttpStatus.NOT_FOUND);
        }

        return EntityCatch.handlerBody(guessData.getTaskFlowsList().size(), HttpStatus.OK);
    }

    @Override
    public EntityCatch<DtoGuess> deleteWebGuessData(String email) {

        Guess guessData = guessRepositories.getGuessByData(email);
        DtoGuess dtoGuess = new DtoGuess();
        BeanUtils.copyProperties(guessData, dtoGuess);
        List<DtoGuessTaskFlow> dtoTaskFlows = new ArrayList<>();

        if  (guessData.getTaskFlowsList() != null && !guessData.getTaskFlowsList().isEmpty()) {

            for (GuessTaskFlow flow : guessData.getTaskFlowsList()) {

                taskFlowRepositories.delete(flow);

                DtoGuessTaskFlow dtoFlow = new DtoGuessTaskFlow();
                dtoFlow.setTaskName(flow.getTaskName());
                dtoFlow.setTaskStatus(flow.getTaskStatus());

                if (!dtoTaskFlows.contains(dtoFlow)) {
                    dtoTaskFlows.add(dtoFlow);
                }

            }
            dtoGuess.setTaskFlowsList(dtoTaskFlows);
        } else {
            dtoGuess.setTaskFlowsList(new  ArrayList<>());
        }

        guessRepositories.delete(guessData);

        return EntityCatch.handlerBody(dtoGuess, HttpStatus.OK);
    }

    @Override
    public EntityCatch<DtoGuessTaskFlow> postWebGuessAddTaskflowData(String email,GuessTaskFlow guessTaskFlow) {

        if (taskFlowRepositories.getTaskflow(guessTaskFlow.getTaskUniqueid()) != null) {
            return GlobalException.errorCatch(new NullPointerException("Task already is founded"),HttpStatus.NOT_FOUND);
        }

        Guess guessData = guessRepositories.getGuessByData(email);

        DtoGuess dtoGuess = new DtoGuess();
        DtoGuessTaskFlow dtoGuessTaskFlow = new DtoGuessTaskFlow();
        List<DtoGuessTaskFlow> dtoTaskFlows = new ArrayList<>();

        if (guessData == null) {
            return GlobalException.errorCatch(new IllegalArgumentException("Data is not found"),HttpStatus.NOT_FOUND);
        }

        if (!guessData.getTaskFlowsList().contains(guessTaskFlow)) {
            guessData.getTaskFlowsList().add(guessTaskFlow);
            guessTaskFlow.setOwner(guessData);
        } else {
            return GlobalException.errorCatch(new NullPointerException("Task already is founded"),HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(guessData, dtoGuess);
        BeanUtils.copyProperties(guessTaskFlow, dtoGuessTaskFlow);

        dtoGuessTaskFlow.setTaskName(guessTaskFlow.getTaskName());
        dtoGuessTaskFlow.setTaskStatus(guessTaskFlow.getTaskStatus());

        if (guessData.getTaskFlowsList() != null && !guessData.getTaskFlowsList().isEmpty()) {
            for (GuessTaskFlow flow : guessData.getTaskFlowsList()) {
                DtoGuessTaskFlow dtoFlow = new DtoGuessTaskFlow();
                dtoFlow.setTaskName(flow.getTaskName());
                dtoFlow.setTaskStatus(flow.getTaskStatus());

                if (!dtoTaskFlows.contains(dtoFlow)) {
                    dtoTaskFlows.add(dtoFlow);
                }

            }
            dtoGuess.setTaskFlowsList(dtoTaskFlows);
        } else {
            dtoGuess.setTaskFlowsList(new  ArrayList<>());
        }

        guessRepositories.save(guessData);
        taskFlowRepositories.save(guessTaskFlow);

        return EntityCatch.handlerBody(dtoGuessTaskFlow, HttpStatus.OK);
    }

    @Override
    public EntityCatch<DtoGuessTaskFlow> postWebGuessRemoveTaskflowData(String email,int taskflowId) {

        Guess guessData = guessRepositories.getGuessByData(email);
        String uniqueid = null;

        if (guessData == null) {
            return GlobalException.errorCatch(new IllegalArgumentException("Data is not found"),HttpStatus.NOT_FOUND);
        }

        if (guessData.getTaskFlowsList() != null && !guessData.getTaskFlowsList().isEmpty()) {
            for (GuessTaskFlow flows : guessData.getTaskFlowsList()) {
                if (flows.getTaskId() == taskflowId) {
                     uniqueid = flows.getTaskUniqueid();
                }
            }
        }

        GuessTaskFlow taskFlow = taskFlowRepositories.getTaskflow(uniqueid);
        DtoGuessTaskFlow dtoGuessTaskFlow = new DtoGuessTaskFlow();
        BeanUtils.copyProperties(taskFlow, dtoGuessTaskFlow);

        if (guessData.getTaskFlowsList().contains(taskFlow))
            guessData.getTaskFlowsList().remove(taskFlow);

        taskFlowRepositories.delete(taskFlow);
        guessRepositories.save(guessData);

        return EntityCatch.handlerBody(dtoGuessTaskFlow, HttpStatus.OK);
    }

    @Override
    public EntityCatch<List<DtoGuess>> getWebAllTasks() {
        List<Guess> guesses = guessRepositories.getAllTasks();
        List<DtoGuess> dtoGuesses = new ArrayList<>();

        if (guesses == null || guesses.isEmpty()) {
            return EntityCatch.handlerBody(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        for (Guess guess : guesses) {

            DtoGuess dtoGuess = new DtoGuess();
            BeanUtils.copyProperties(guess, dtoGuess);

            if (guess.getTaskFlowsList() != null && !guess.getTaskFlowsList().isEmpty()) {

                List<DtoGuessTaskFlow> dtoTaskFlowsList = new ArrayList<>();

                for (GuessTaskFlow taskFlows : guess.getTaskFlowsList()) {

                    DtoGuessTaskFlow flow = new DtoGuessTaskFlow();
                    flow.setTaskName(taskFlows.getTaskName());
                    flow.setTaskStatus(taskFlows.getTaskStatus());

                    if (!dtoTaskFlowsList.contains(flow)) {
                        dtoTaskFlowsList.add(flow);
                    }

                }

                dtoGuess.setTaskFlowsList(dtoTaskFlowsList);
            } else {
                dtoGuess.setTaskFlowsList(new ArrayList<>());
            }

            if (!dtoGuesses.contains(dtoGuess)) {
                dtoGuesses.add(dtoGuess);
            }

        }

        return EntityCatch.handlerBody(dtoGuesses, HttpStatus.OK);
    }
}
