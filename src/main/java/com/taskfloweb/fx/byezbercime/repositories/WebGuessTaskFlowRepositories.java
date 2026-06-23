package com.taskfloweb.fx.byezbercime.repositories;

import com.taskfloweb.fx.byezbercime.entity.GuessTaskFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WebGuessTaskFlowRepositories extends JpaRepository<GuessTaskFlow, UUID> {

    @Query(nativeQuery = true,value = "SELECT * FROM taskflow_tasks_base WHERE taskflow_uniqueid=?1")
    GuessTaskFlow getTaskflow(String uniqueid);

}
