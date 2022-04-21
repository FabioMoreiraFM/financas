package com.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.Terceiro;

@Repository
public interface TerceiroRepository extends JpaRepository<Terceiro, Long>{

}
