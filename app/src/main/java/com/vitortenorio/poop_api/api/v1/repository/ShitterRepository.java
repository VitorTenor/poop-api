package com.vitortenorio.poop_api.api.v1.repository;

import com.vitortenorio.poop_api.api.v1.model.ShitterModel;
import com.vitortenorio.poop_api.api.v1.model.mapper.ShitterRankingMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShitterRepository extends JpaRepository<ShitterModel, Long> {

        @Query(
                "SELECT new com.vitortenorio.poop_api.api.v1.model.mapper.ShitterRankingMapper(s.name, " +
                        "ROW_NUMBER() OVER (ORDER BY COUNT(se.id) DESC), " +
                        "s.imageBase64, COUNT(se.id)) " +
                        "FROM ShitterModel s " +
                        "JOIN ShitEventModel se ON se.shitter.id = s.id " +
                        "GROUP BY s.id, s.name, s.imageBase64 " +
                        "ORDER BY COUNT(se.id) DESC LIMIT 3"
        )
        List<ShitterRankingMapper> findTop3ShittersWithMostEvents();

        Optional<ShitterModel> findByName(String s);
}
