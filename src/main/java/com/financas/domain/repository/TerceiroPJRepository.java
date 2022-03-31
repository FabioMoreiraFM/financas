package com.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.TerceiroPJ;

@Repository
public interface TerceiroPJRepository extends JpaRepository<TerceiroPJ, Long>{

}
