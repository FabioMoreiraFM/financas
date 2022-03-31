package com.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.TerceiroPF;

@Repository
public interface TerceiroPFRepository extends JpaRepository<TerceiroPF, Long>{

}
