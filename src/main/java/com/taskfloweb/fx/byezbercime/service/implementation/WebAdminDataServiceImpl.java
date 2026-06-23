package com.taskfloweb.fx.byezbercime.service.implementation;

import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import org.springframework.stereotype.Service;

public interface WebAdminDataServiceImpl {

    EntityCatch<PrimaryOfficial> createOfficialCertifica(PrimaryOfficial primaryOfficial);

}
