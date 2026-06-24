package com.taskfloweb.fx.byezbercime.repositories;

import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebAdminRepositories extends JpaRepository<PrimaryOfficial,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM taskflow_primaryofficials WHERE taskflow_certificatedcode=?1;")
    PrimaryOfficial getOfficialByCertificatedCode(String certificatedCode);

    @Query(nativeQuery = true,value = "SELECT * FROM taskflow_primaryofficials;")
    List<PrimaryOfficial> getOfficialsByCertificate();

}
