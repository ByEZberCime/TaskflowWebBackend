package com.taskfloweb.fx.byezbercime.controller;

import com.taskfloweb.fx.byezbercime.controller.implementation.WebGuessDataControllerImpl;
import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessTaskFlow;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.GuessTaskFlow;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.exception.GlobalException;
import com.taskfloweb.fx.byezbercime.service.implementation.WebGuessDataServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping(path = "/account/users")
public class WebGuessDataController implements WebGuessDataControllerImpl {

    @Autowired
    private WebGuessDataServiceImpl guessDataService;

    @GetMapping(path = "/get")
    @Override
    public EntityCatch<DtoGuess> getWebByGuessData(@RequestParam(name = "email") String email) {
        return guessDataService.getWebByGuessData(email);
    }

    @PostMapping(path = "/put")
    @Override
    public EntityCatch<DtoGuess> saveWebGuessData(@RequestBody() Guess guess) {
        return guessDataService.saveWebGuessData(guess);
    }

    @GetMapping(path = "/tasklistsize")
    @Override
    public EntityCatch<Integer> getGuessTaskFlowLists(@RequestParam(name = "email") String email) {
        return guessDataService.getGuessTaskFlowLists(email);
    }

    @PutMapping(path = "/addtask")
    @Override
    public EntityCatch<DtoGuessTaskFlow> postWebGuessAddTaskflowData(@RequestParam(name = "email") String email, @RequestBody GuessTaskFlow guessTaskFlow) {
        try {
            return guessDataService.postWebGuessAddTaskflowData(email,guessTaskFlow);
        } catch (IllegalArgumentException exception) {
            return GlobalException.errorCatch(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/removetask")
    @Override
    public EntityCatch<DtoGuessTaskFlow> postWebGuessRemoveTaskflowData(@RequestParam(name = "email") String email,@RequestParam(name = "taskId") int taskflowId) {
        return guessDataService.postWebGuessRemoveTaskflowData(email,taskflowId);
    }

    @DeleteMapping(path = "/delete")
    @Override
    public EntityCatch<DtoGuess> deleteWebGuessData(@RequestParam(name = "email") String email) {
        try {
            return guessDataService.deleteWebGuessData(email);
        } catch (IllegalArgumentException exception) {
            return GlobalException.errorCatch(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/listguess")
    @Override
    public EntityCatch<List<DtoGuess>> getWebAllTasks() {
        return guessDataService.getWebAllTasks();
    }



}