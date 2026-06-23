package com.taskfloweb.fx.byezbercime.service.implementation;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import org.springframework.http.ResponseEntity;

public interface WebGuessDataServiceImpl {

    EntityCatch<DtoGuess> getWebByGuessData(String email);

}
