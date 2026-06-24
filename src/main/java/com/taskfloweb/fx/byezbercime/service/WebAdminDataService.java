package com.taskfloweb.fx.byezbercime.service;

import com.taskfloweb.fx.byezbercime.configuration.WebConfiguration;
import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.exception.GlobalException;
import com.taskfloweb.fx.byezbercime.repositories.WebAdminRepositories;
import com.taskfloweb.fx.byezbercime.service.implementation.WebAdminDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WebAdminDataService implements WebAdminDataServiceImpl {

    @Autowired
    private WebAdminRepositories adminRepositories;

    @Autowired
    private WebConfiguration webConfiguration;

    @Override
    public EntityCatch<PrimaryOfficial> createOfficialCertifica(PrimaryOfficial primaryOfficial) {

        if (adminRepositories.getOfficialByCertificatedCode(primaryOfficial.getCertificatedPrimaryOfficialCode()) != null) {
            return GlobalException.errorCatch(new NullPointerException("Already certificate is founded"), HttpStatus.FOUND);
        } else {
            adminRepositories.save(primaryOfficial);
        }
        return EntityCatch.handlerBody(primaryOfficial,HttpStatus.CREATED);
    }

    @Override
    public EntityCatch<PrimaryOfficial> getByOfficialCertifica(String certificate) {

        if (adminRepositories.getOfficialByCertificatedCode(certificate) != null) {
            PrimaryOfficial primaryOfficial = adminRepositories.getOfficialByCertificatedCode(certificate);
            return EntityCatch.handlerBody(primaryOfficial,HttpStatus.OK);
        }
        return GlobalException.errorCatch(new NullPointerException("Official is founded"), HttpStatus.NOT_FOUND);
    }

}
