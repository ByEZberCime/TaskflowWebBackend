package com.taskfloweb.fx.byezbercime.service.implementation;

import com.taskfloweb.fx.byezbercime.dto.DtoGuess;
import com.taskfloweb.fx.byezbercime.dto.DtoGuessService;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;

public interface WebUserServiceImpl {

    EntityCatch<DtoGuess> verifyRegister(DtoGuessService  dtoGuessService);

}
