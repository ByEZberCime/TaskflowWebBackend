package com.taskfloweb.fx.byezbercime.controller.implementation;

import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;

import java.util.List;

public interface WebAdminDataControllerImpl {

    EntityCatch<PrimaryOfficial> createOfficialCertifica(PrimaryOfficial primaryOfficial);

    EntityCatch<PrimaryOfficial> getByOfficialCertifica(String certificate);

}
