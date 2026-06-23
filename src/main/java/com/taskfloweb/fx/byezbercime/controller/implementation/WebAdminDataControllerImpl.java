package com.taskfloweb.fx.byezbercime.controller.implementation;

import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;

public interface WebAdminDataControllerImpl {

    EntityCatch<PrimaryOfficial> createOfficialCertifica(PrimaryOfficial primaryOfficial);

}
