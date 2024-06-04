package com.vitortenorio.poop_api.api.v1.repository;

import com.vitortenorio.poop_api.api.v1.model.ShitEventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShitEventRepository extends JpaRepository<ShitEventModel, Long> {
}
