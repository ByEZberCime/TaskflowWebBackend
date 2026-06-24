package com.taskfloweb.fx.byezbercime.controller;

import com.taskfloweb.fx.byezbercime.controller.implementation.WebAdminDataControllerImpl;
import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import com.taskfloweb.fx.byezbercime.exception.EntityCatch;
import com.taskfloweb.fx.byezbercime.service.implementation.WebAdminDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('admin')")
@RequestMapping(path = "/admin/certificate")
public class WebAdminDataController implements WebAdminDataControllerImpl {

    @Autowired
    private WebAdminDataServiceImpl adminDataService;

//    A1jd56aZX1vB61DJ0a90BnV4A1xD3

    @PostMapping(path = "/create")
    @Override
    public EntityCatch<PrimaryOfficial> createOfficialCertifica(@RequestBody PrimaryOfficial primaryOfficial) {
        return adminDataService.createOfficialCertifica(primaryOfficial);
    }

    @GetMapping(path = "/search")
    @Override
    public EntityCatch<PrimaryOfficial> getByOfficialCertifica(@RequestParam(name = "certificate") String certificate) {
        return adminDataService.getByOfficialCertifica(certificate);
    }

}
