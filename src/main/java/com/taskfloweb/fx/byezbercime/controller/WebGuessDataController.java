package com.taskfloweb.fx.byezbercime.controller;
import com.taskfloweb.fx.byezbercime.controller.implementation.WebGuessDataControllerImpl;
import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.service.implementation.WebGuessDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/base")
public class WebGuessDataController implements WebGuessDataControllerImpl {

    @Autowired
    private WebGuessDataServiceImpl guessDataService;

    @GetMapping(path = "/dGs")
    @Override
    public EntityCatch<DtoGuess> getWebByGuessData(@RequestParam(name = "email") String email) {
        return guessDataService.getWebByGuessData(email);
    }

}