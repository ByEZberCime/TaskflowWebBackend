package com.taskfloweb.fx.byezbercime.service.implementation;

import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WebAdminDataServiceImpl {

    EntityCatch<PrimaryOfficial> createOfficialCertifica(PrimaryOfficial primaryOfficial);

    EntityCatch<PrimaryOfficial> getByOfficialCertifica(String certificate);

}
