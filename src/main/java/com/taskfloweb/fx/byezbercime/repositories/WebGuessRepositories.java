package com.taskfloweb.fx.byezbercime.repositories;

import com.taskfloweb.fx.byezbercime.entity.Guess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WebGuessRepositories extends JpaRepository<Guess,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM taskflow_base_data WHERE email=?1;")
    Guess getGuessByData(String email);

}
