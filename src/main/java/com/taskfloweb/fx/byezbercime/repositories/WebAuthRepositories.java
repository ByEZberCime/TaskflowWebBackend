package com.taskfloweb.fx.byezbercime.repositories;

import com.taskfloweb.fx.byezbercime.entity.GuessAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebAuthRepositories extends JpaRepository<GuessAuthentication,String> {

}
